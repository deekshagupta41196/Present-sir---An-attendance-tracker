package com.example.deeksha.presentsir;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/**
 * A login screen that offers login via email/password.
 */
public class FacultyLogin extends AppCompatActivity {


    EditText mEmailView, mPasswordView;
    Button login;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faculty_login);

        mEmailView = (EditText) findViewById(R.id.email);
        mPasswordView = (EditText) findViewById(R.id.password);
        login = (Button) findViewById(R.id.login);

        login.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = mEmailView.getText().toString();
                String password = mPasswordView.getText().toString();
                if(email.equals("faculty")&&password.equals("faculty"))
                {
                    Intent intent=new Intent(FacultyLogin.this,FacultyMenu.class);
                    startActivity(intent);
                }
                else
                {
                    Snackbar.make(v, "Incorrect Email/Password", Snackbar.LENGTH_LONG).show();
                }
            }
        });
    }


}

