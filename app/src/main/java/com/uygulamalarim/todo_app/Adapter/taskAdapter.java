package com.uygulamalarim.todo_app.Adapter;

import static android.content.ContentValues.TAG;

import android.nfc.Tag;
import android.text.Layout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.PopupMenu;
import androidx.recyclerview.widget.RecyclerView;

import com.uygulamalarim.todo_app.Database.DBHelper;
import com.uygulamalarim.todo_app.Domain.taskDomain;
import com.uygulamalarim.todo_app.R;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class taskAdapter extends RecyclerView.Adapter<taskAdapter.viewHolder> {

    public taskAdapter(ArrayList<taskDomain> tasks) {
        this.tasks = tasks;
    }
    private ArrayList<taskDomain> tasks;
    private TextView threeDotMenu;
    public DBHelper DB;




    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_tasklist,parent,false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {
        Calendar calendar=Calendar.getInstance();
        String currentDate= DateFormat.getDateInstance(DateFormat.FULL).format(calendar.getTime());

        holder.dateTime.setText(currentDate);
        holder.title.setText(tasks.get(position).getTitle());
        holder.description.setText(tasks.get(position).getDescription());
        holder.deadline.setText(tasks.get(position).getDeadLine());

    }

    @Override
    public int getItemCount() {
        return tasks.size();
    }




    public class viewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, PopupMenu.OnMenuItemClickListener {
        TextView dateTime,title,description,deadline;


        public viewHolder(@NonNull View itemView){
            super(itemView);
            dateTime=itemView.findViewById(R.id.datetimeLayout);
            title=itemView.findViewById(R.id.titleLayout);
            description=itemView.findViewById(R.id.descriptionLayout);
            deadline=itemView.findViewById(R.id.deadlineLayout);
            threeDotMenu= itemView.findViewById(R.id.threeDotMenu);
            threeDotMenu.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            showPopupMenu(v);


        }
        private void showPopupMenu(View view){

            PopupMenu popupMenu=new PopupMenu(view.getContext(),view);
            popupMenu.inflate(R.menu.menu);
            popupMenu.setOnMenuItemClickListener(this);
            popupMenu.show();
        }

        @Override
        public boolean onMenuItemClick(MenuItem item) {
            int position=getAdapterPosition();

            switch (item.getItemId()){
                case R.id.Edit:
                    System.out.println("EDİTE TIKLANDI position:"+getAdapterPosition());
                    return true;
                case R.id.Delete:
                    tasks.remove(position);
                    notifyItemRemoved(position);
                    DB=new DBHelper(itemView.getContext());
                    String a=title.getText().toString();
                    DB.deleteRow(a);
                    return true;
                default:
                    return false;
            }


        }
    }

}
