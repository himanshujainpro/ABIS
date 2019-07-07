package com.example.abis;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class StudentDbHelper extends SQLiteOpenHelper {
    public StudentDbHelper(Context context) {
        super(context, "stud.db", null, 1);
    }
    @Override
    public void onCreate(SQLiteDatabase db)
    {
        db.execSQL("create table gecm(enno TEXT(12) PRIMARY KEY,pass TEXT(20),email TEXT(50))");
    }
    @Override
    public void onUpgrade(SQLiteDatabase db,int oldVersion,int newVersion)
    {
        db.execSQL("DROP TABLE IF EXISTS gecm");
        onCreate(db);
    }
    public boolean checkUser(String id,String pass)
    {
        SQLiteDatabase db=getReadableDatabase();
        String[] columns={"enno","pass"};
        String[] selectionArgs={id,pass};
        Cursor c=db.query("gecm",columns,"enno = ? AND pass = ?",selectionArgs,null,null,null);

        if(c!=null&&c.moveToFirst())
            return true;
        else
            return false;
    }
}
