package com.example.deeksha.presentsir;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.TextView;

/**
 * Created by Deeksha on 7/17/2016.
 */
public class Info extends Activity
{
    String sid,sname,user,pas,branch,sem,sec,present,total;
    DBHandler dbh;
    TextView txtid,txtname,txtemail,txtpass,txtbranch,txtsec,txtsem,txtpresent,txtday;
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.info);
        txtid = (TextView) findViewById(R.id.txtid);
        txtname=(TextView)findViewById(R.id.txtname);
        txtemail=(TextView)findViewById(R.id.txtemail);
        txtpass=(TextView)findViewById(R.id.txtpass);
        txtpresent=(TextView)findViewById(R.id.txtpresent);
        txtday=(TextView)findViewById(R.id.txtday);
        txtbranch=(TextView)findViewById(R.id.txtbranch);
        txtsec=(TextView)findViewById(R.id.txtsec);
        txtsem=(TextView)findViewById(R.id.txtsem);

        Intent intent=getIntent();
        String email=intent.getStringExtra("email");
        String pass=intent.getStringExtra("pass");

        dbh=new DBHandler(this);
        dbh.openReadable();
        Cursor cursor=dbh.retrieveinfo(email,pass);
        cursor.moveToFirst();
        while (cursor.isAfterLast()==false)
        {
            sid=cursor.getString(0);
            sname=cursor.getString(1);
            user=cursor.getString(2);
            pas=cursor.getString(3);
            branch=cursor.getString(4);
            sem=cursor.getString(5);
            sec=cursor.getString(6);
            cursor.moveToNext();
        }
        Cursor cursor1=dbh.retrieveattendance(sid);
        cursor1.moveToFirst();
        while (cursor1.isAfterLast()==false)
        {
            present=cursor1.getString(0);
            cursor1.moveToNext();
        }
        Cursor cursor2=dbh.retrievetotal(sid);
        cursor2.moveToFirst();
        while (cursor2.isAfterLast()==false)
        {
            total=cursor2.getString(0);
            cursor2.moveToNext();
        }
        txtid.setText(sid);
        txtname.setText(sname);
        txtemail.setText(user);
        txtpass.setText(pas);
        txtbranch.setText(branch);
        txtsem.setText(sem);
        txtsec.setText(sec);
        txtpresent.setText(present);
        txtday.setText(total);

    }
}
