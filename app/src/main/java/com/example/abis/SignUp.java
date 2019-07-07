package com.example.abis;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.sql.Connection;

public class SignUp extends AppCompatActivity {
    StudentDbHelper studentDbHelper=new StudentDbHelper(this);
    EditText et3,et4,et5;
    Button b10,b11;
    String pass,email,en_no;
    boolean rf;
    MyTask mt=new MyTask();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        et3=(EditText)findViewById(R.id.et3);
        et4=(EditText)findViewById(R.id.et4);
        et5=(EditText)findViewById(R.id.et5);
        b10=(Button)findViewById(R.id.btn10);
        b11=(Button)findViewById(R.id.btn11);

        b10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                en_no=et3.getText().toString();
                pass=et4.getText().toString();
                email=et5.getText().toString();
                new MyTask().execute();
                Intent i=new Intent(SignUp.this, MainActivity.class);
                startActivity(i);
            }
        });

        b11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(SignUp.this, Login.class);
                startActivity(i);
            }
        });

    }

    private class MyTask extends AsyncTask<String,String,String>
    {
        @Override
        protected String doInBackground(String... params)
        {
            SQLiteDatabase db=studentDbHelper.getWritableDatabase();
            ContentValues values=new ContentValues();
            values.put("enno",en_no);
            values.put("pass",pass);
            values.put("email",email);
            long l=db.insert("gecm",null,values);
            if(l==-1)
                return "You AlReady Have A Student Account";
            else
                return "Success";

        }

        @Override
        protected void onPostExecute(String s) {
            Toast.makeText(SignUp.this,s,Toast.LENGTH_LONG).show();
        }
    }
}
