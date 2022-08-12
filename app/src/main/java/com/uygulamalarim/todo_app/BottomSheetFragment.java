package com.uygulamalarim.todo_app;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.uygulamalarim.todo_app.Database.DBHelper;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class BottomSheetFragment extends BottomSheetDialogFragment {
    public EditText titleEditText,descriptionEditText,deadLineEditText;
    public Button addToTaskListBtn,closeBottomFragmentBtn;
    DBHelper DB;
    MainActivity main;
    public Calendar calendar=Calendar.getInstance();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.bottom_sheet_fragment,container,false);
        return v;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        titleEditText=view.findViewById(R.id.titleEditText);
        descriptionEditText=view.findViewById(R.id.descriptionEditText);
        deadLineEditText=view.findViewById(R.id.deadLineEditText);
        addToTaskListBtn=view.findViewById(R.id.addToTaskListBtn);
        closeBottomFragmentBtn=view.findViewById(R.id.closeBottomFragmentBtn);


        closeBottomFragmentBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

        addToTaskListBtn.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String currentDate= DateFormat.getDateInstance(DateFormat.FULL).format(calendar.getTime());
                String title=titleEditText.getText().toString();
                String desc=descriptionEditText.getText().toString();
                String deadLine=deadLineEditText.getText().toString();
                DB=new DBHelper(getContext());
                DB.insertData(currentDate,title,desc,deadLine,0);
                Intent intent=new Intent(getContext(),MainActivity.class);
                startActivity(intent);



            }
        }));

    }
}
