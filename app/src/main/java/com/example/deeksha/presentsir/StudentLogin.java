package com.example.deeksha.presentsir;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by Deeksha on 7/14/2016.
 */
public class StudentLogin extends AppCompatActivity
{
    Button login;
    DBHandler dbh;
    EditText email,password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dbh=new DBHandler(this);
        setContentView(R.layout.activity_student_login);
        email=(EditText)findViewById(R.id.email);
        password=(EditText)findViewById(R.id.password);
        login = (Button) findViewById(R.id.login);

        login.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user=email.getText().toString();
                String pass=password.getText().toString();
                dbh.openReadable();
                Cursor cursor=dbh.login(user,pass);
                cursor.moveToFirst();
                if(cursor.getCount()>0)
                {
                    String un=cursor.getString(0);
                    String pas=cursor.getString(1);
                    Intent intent=new Intent(StudentLogin.this,Info.class);
                    intent.putExtra("email",un);
                    intent.putExtra("pass",pas);
                    startActivity(intent);
                }
                else
                {
                    Toast.makeText(StudentLogin.this, "Incorrect Email/Password", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
