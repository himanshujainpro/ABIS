package com.example.abis;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.sql.*;
public class Login extends AppCompatActivity {
    StudentDbHelper studentDbHelper=new StudentDbHelper(this);
    EditText et1,et2;
    Button b7,b9,b8;
    String pass,en_no;
    boolean rf;
    MyTask mt=new MyTask();
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        et1=(EditText)findViewById(R.id.et1);
        et2=(EditText)findViewById(R.id.et2);
        b7=(Button)findViewById(R.id.btn7);
        b8=(Button)findViewById(R.id.btn8);
        b9=(Button)findViewById(R.id.btn9);

        b7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                en_no=et1.getText().toString();
                pass=et2.getText().toString();
                new MyTask().execute();
            }
        });

        b9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(Login.this, SignUp.class);
                startActivity(i);
            }
        });
    }

    private class MyTask extends AsyncTask<String,String,String>
    {
        @Override
        protected String doInBackground(String... params)
        {
            boolean f;
            f=studentDbHelper.checkUser(en_no,pass);
            if(f)
                return "Success";
            else
                return "Error";
        }

        @Override
        protected void onPostExecute(String s) {
            Toast.makeText(Login.this,s,Toast.LENGTH_LONG).show();
        }
    }

}
