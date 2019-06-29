package com.example.deeksha.presentsir;

import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;

/**
 * Created by Deeksha on 7/6/2016.
 */
public class Mark_Attendence extends Activity
{
    DBHandler dbh;
    int day=1;
    ListView listView;
    Calendar calendar;
    Button submit;
    ArrayList<Student> student=new ArrayList<>();
    String branch;
    String semester1;
    int semester;
    String section;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mark_attendence);
        calendar=Calendar.getInstance();
        final MyArrayAdapter  boxAdapter = new MyArrayAdapter(this,student);
        listView = (ListView) findViewById(R.id.listView);
        submit = (Button) findViewById(R.id.submit);

        dbh=new DBHandler(this);
        Bundle bundle=getIntent().getExtras();
        branch=bundle.getString("Branch");
        semester1=bundle.getString("Semester");
        semester=Integer.parseInt(semester1);
        section=bundle.getString("Section");

        fillData(branch,semester,section);
        listView.setAdapter(boxAdapter);

        submit.setOnClickListener(new View.OnClickListener() 
        {
            @Override
            public void onClick(View v) 
            {
                int days=calendar.get(calendar.DAY_OF_MONTH);
                int month=calendar.get(calendar.MONTH);
                int yr=calendar.get(calendar.YEAR);
                String date=days+"/"+(month+1)+"/"+yr;
                for (Student p : boxAdapter.getBoxPresent())
                {
                    if (p.box)
                    {
                        String present = "P";
                        String sid = p.getSid();
                        String sname=p.getName();
                        long id2=dbh.addattendane(sid,present,date,day);
                        if(id2>0)
                        {
                            Toast.makeText(Mark_Attendence.this, "Name="+sname+"=Present", Toast.LENGTH_SHORT).show();
                        }
                        else
                        {
                            Toast.makeText(Mark_Attendence.this, "NO RECORD inserted ", Toast.LENGTH_SHORT).show();
                        }
                    }
                    for (Student p1:boxAdapter.getBoxAbsent())
                    {
                        if (!p1.box)
                        {
                            String absent = "A";
                            String sid = p1.getSid();
                            String sname=p1.getName();

                            long id3 = dbh.addattendane(sid, absent, date, day);
                            if (id3 > 0)
                            {
                                Toast.makeText(Mark_Attendence.this, "Name="+sname+"=Absent", Toast.LENGTH_SHORT).show();
                            }
                            else
                            {
                                Toast.makeText(Mark_Attendence.this, "NO RECORD INSERTED", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                }
            }
        });
    }
    void fillData(String branchh,int sem,String sectionn)
    {
        branch=branchh;
        semester=sem;
        section=sectionn;
            dbh = new DBHandler(this);
            dbh.openReadable();
            Cursor cursor= dbh.list(branch, semester, section);
            cursor.moveToFirst();
            while(cursor.isAfterLast()==false)
            {
                Log.d("1", "fillData: "+cursor.getString(0)+"-"+cursor.getString(1));
                student.add(new Student(cursor.getString(1),cursor.getString(0),R.drawable.download_1,false));
                cursor.moveToNext();
            }
        }
    }



