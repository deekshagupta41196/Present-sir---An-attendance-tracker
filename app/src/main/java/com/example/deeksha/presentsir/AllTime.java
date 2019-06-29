package com.example.deeksha.presentsir;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * Created by Deeksha on 7/17/2016.
 */
public class AllTime extends Activity
{
    DBHandler dbh;
    String branchh,semesterr,sectionn;
    ArrayList<Studentpojo> studentpojos=new ArrayList<>();
    ListView list;
    StudentAdapter adapter;
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.alltime);
        list=(ListView)findViewById(R.id.list);
        adapter=new StudentAdapter(this,studentpojos);


        Intent intent=getIntent();
        String branch= intent.getStringExtra("branch");
        String section=intent.getStringExtra("section");
        String semester=intent.getStringExtra("semester");
        fileList1(branch,semester,section);
        list.setAdapter(adapter);
    }
    void fileList1(String branch,String sem,String section)
    {
        branchh=branch;
        semesterr=sem;
        sectionn=section;
        dbh=new DBHandler(this);
        dbh.openReadable();
        Cursor cursor=dbh.retrieveall(branchh,semesterr,sectionn);
        Cursor cursor1=dbh.retrieveday(branchh,semesterr,sectionn);
        cursor1.moveToFirst();
        cursor.moveToFirst();
        while (cursor.isAfterLast()==false&&cursor1.isAfterLast()==false)
        {
            Log.d("alltime", "fileList: "+cursor.getString(1)+"-"+cursor.getString(0)+"-"+cursor.getString(2)+"="+cursor1.getString(0));
            studentpojos.add(new Studentpojo(cursor.getString(1),cursor.getString(0),cursor.getString(2),cursor1.getString(0)));
            cursor.moveToNext();
            cursor1.moveToNext();
        }
        adapter=new StudentAdapter(this,studentpojos);
    }
}
