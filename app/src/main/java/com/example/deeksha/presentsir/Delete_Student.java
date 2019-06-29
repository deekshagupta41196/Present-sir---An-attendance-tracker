package com.example.deeksha.presentsir;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by Deeksha on 7/15/2016.
 */
public class Delete_Student extends Activity
{

    MyArrayAdapter adapter;

        ImageButton refresh;
        DBHandler dbh;
        int day=1;
        ListView listView;
        Button delete;
        ArrayList<Student> student=new ArrayList<>();
        String branch;
        String semester1;
        int semester;
        String section;
        MyArrayAdapter  boxAdapter = new MyArrayAdapter(this,student);

        @Override
        protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.deletestudent);

        adapter = new MyArrayAdapter(this, student);
        listView = (ListView) findViewById(R.id.listView);
        delete = (Button) findViewById(R.id.delete);
        refresh=(ImageButton)findViewById(R.id.refresh);

        Bundle bundle=getIntent().getExtras();
        branch=bundle.getString("Branch");
        semester1=bundle.getString("Semester");
        semester=Integer.parseInt(semester1);
        section=bundle.getString("Section");

        fillData(branch,semester,section);
        listView.setAdapter(adapter);

        delete.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                showAlert();
            }
        });

       refresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                fillData(branch,semester,section);
              //  listView.setAdapter(adapter);
            }
        });
    }
    void fillData(String branchh,int sem,String sectionn)
    {
        student.clear();
        branch=branchh;
        semester=sem;
        section=sectionn;
        dbh = new DBHandler(this);
        dbh.openReadable();
        Cursor cursor= dbh.list(branch, semester, section);
        cursor.moveToFirst();
        while(cursor.isAfterLast()==false)
        {
            Log.d("1", "fillData11: "+cursor.getString(0)+"-"+cursor.getString(1));
            student.add(new Student(cursor.getString(1),cursor.getString(0),R.drawable.download_1,false));
            cursor.moveToNext();
        }
        adapter.notifyDataSetChanged();

    }
    void showAlert()
    {
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        LinearLayout layout       = new LinearLayout(this);
        final TextView tv        = new TextView(this);

        tv.setText("Delete Student Details ?");
        tv.setTextSize(30);
        layout.setOrientation(LinearLayout.VERTICAL);
        layout.addView(tv);
        alert.setView(layout);

        alert.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        alert.setPositiveButton("DELETE", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                for(Student p:boxAdapter.getBoxPresent())
                {
                    if(p.box)
                    {
                        String sid=p.getSid();
                        String sname=p.getName();
                        Log.d("delete", "onClick: "+sid);
                        Cursor id=dbh.deletestudentt(sid);
                        if(id.getCount()>0)
                        {
                            Cursor id1 = dbh.deletestudent(sid);
                            if (id1.getCount() > 0)
                            {
                                Toast.makeText(Delete_Student.this, "Record Id=" + sid + " and Name=" + sname + " Deleted Successfully", Toast.LENGTH_SHORT).show();
                            }
                            else
                            {
                                Toast.makeText(Delete_Student.this, "Record Id="+sid+" and Name="+sname+" Not Deleted Successfully", Toast.LENGTH_SHORT).show();
                            }
                        }
                        else
                        {
                            Cursor id1 = dbh.deletestudent(sid);
                            if (id1.getCount() > 0)
                            {
                                Toast.makeText(Delete_Student.this, "Record Id=" + sid + " and Name=" + sname + " Deleted Successfully", Toast.LENGTH_SHORT).show();
                            }
                            else
                            {
                                Toast.makeText(Delete_Student.this, "Record Id="+sid+" and Name="+sname+"Deleted Successfully", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                }
            }
        });
        alert.show();
    }
}

