package com.uygulamalarim.todo_app;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.text.DateFormat;
import java.time.Clock;
import java.time.LocalDate;

import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.uygulamalarim.todo_app.Adapter.taskAdapter;
import com.uygulamalarim.todo_app.Database.DBHelper;
import com.uygulamalarim.todo_app.Domain.taskDomain;

import java.util.ArrayList;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
    private RecyclerView todoRecycler;
    private TextView addTaskBtn,date_today;

    private ArrayList<taskDomain> tasks;
    private taskAdapter taskAdapter;

    DBHelper DB;
    private BottomSheetFragment bottomSheetFragment;
    public Calendar calendar=Calendar.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);




        initView();
        fillTask();







        addTaskBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openBottomSheetFragment();
            }
        });

    }

    public void openBottomSheetFragment(){

        addTaskBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bottomSheetFragment.show(getSupportFragmentManager(),"List item");
            }
        });

    }

    public void fillTask() {

        DB=new DBHelper(this);
        String currentDate= DateFormat.getDateInstance(DateFormat.FULL).format(calendar.getTime());

        SQLiteDatabase database=DB.getWritableDatabase();
        Cursor cursor=database.rawQuery("select * from taskInfo",null,null);

        while(cursor.moveToNext()){

            String str1=cursor.getString(1);
            String str2=cursor.getString(2);
            String str3=cursor.getString(3);

            if(str1.isEmpty() && str2.isEmpty() && str3.isEmpty()){
                break;
            }
            else
                tasks.add(new taskDomain(currentDate,str1,str2,str3));
        }
        cursor.close();

    }

    private void initView() {
        todoRecycler=findViewById(R.id.todoRecycler);
        addTaskBtn=findViewById(R.id.addTaskBtn);
        date_today=findViewById(R.id.date_today);
        tasks=new ArrayList<>();
        taskAdapter=new taskAdapter(tasks);
        todoRecycler.setAdapter(taskAdapter);
        todoRecycler.setLayoutManager(new LinearLayoutManager(this));

        bottomSheetFragment=new BottomSheetFragment();

        String currentDate= DateFormat.getDateInstance(DateFormat.FULL).format(calendar.getTime());
        date_today.setText(currentDate);
    }


}