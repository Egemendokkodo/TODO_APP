package com.uygulamalarim.todo_app.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.uygulamalarim.todo_app.Domain.taskDomain;
import com.uygulamalarim.todo_app.MainActivity;

import java.text.DateFormat;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;

public class DBHelper extends SQLiteOpenHelper {

    public static final String DBNAME = "TODO_APP_DB";
    public DBHelper(Context context) {
        super(context, "TODO_APP_DB", null, 1);

    }

    @Override
    public void onCreate(SQLiteDatabase MyDB) {
        MyDB.execSQL("create Table if not exists taskInfo (addingDate VARCHAR,title VARCHAR,description VARCHAR,date DATETIME,isCompleted INT)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase MyDB, int i, int i1) {
        MyDB.execSQL("drop Table if exists taskInfo");
    }

    public Boolean insertData(String addingDate,String title, String description, String date, int isCompleted){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        ContentValues contentValues= new ContentValues();

        contentValues.put("addingDate",addingDate);
        contentValues.put("title", title);
        contentValues.put("description", description);
        contentValues.put("date", date);
        contentValues.put("isCompleted", isCompleted);

        long result = MyDB.insert("taskInfo", null, contentValues);
        if(result==-1) return false;
        else
            return true;

    }

    public void deleteRow(String value)
    {
        String dbname="taskInfo";
        String columnName="title";
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM " + dbname+ " WHERE "+columnName+"='"+value+"'");
        db.close();
    }


    /*public void showAllData(){
        SQLiteDatabase MyDB=this.getReadableDatabase();
        Cursor cursor=MyDB.rawQuery("select * from taskInfo",null,null);
        while(cursor.moveToNext()){
            System.out.println(cursor.getString(0)+cursor.getString(1)+cursor.getString(2));
        }
        cursor.close();

    }*/


    /*public Boolean checkusername(String mail) {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("Select * from users where mail = ?", new String[]{mail});
        if (cursor.getCount() > 0)
            return true;
        else
            return false;
    }*/

    /*public Boolean checkusernamepassword(String mail, String password){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("Select * from users where mail = ? and password = ?", new String[] {mail,password});
        if(cursor.getCount()>0)
            return true;
        else
            return false;
    }*/
}