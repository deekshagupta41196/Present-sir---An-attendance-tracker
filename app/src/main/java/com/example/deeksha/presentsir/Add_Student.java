package com.example.deeksha.presentsir;

import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

/**
 * Created by Deeksha on 7/6/2016.
 */
public class Add_Student extends Activity
{
    int sem;
    String section;
    DBHandler dbh;
    String branchh;
    String[] branch={"--Select Branch--","Computer_Science","Electrical","Electronic_&_Comm."};
    String [] semester={"--Select Semester--","1","2","3","4","5","6","7","8"};
    String [] sectionss={"--Select Section--","A","B","C"};
    EditText id,name;
    Button add;
    String sem1;
    Spinner branchsp,semsp,secsp;
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_student);
        id=(EditText)findViewById(R.id.id);
        name=(EditText)findViewById(R.id.name);
        branchsp=(Spinner)findViewById(R.id.branchsp);
        semsp=(Spinner)findViewById(R.id.semsp);
        secsp=(Spinner)findViewById(R.id.secsp);
        add=(Button)findViewById(R.id.add);


        dbh=new DBHandler(this);
        final ArrayAdapter<String> branchs=new ArrayAdapter<String>(Add_Student.this,android.R.layout.simple_list_item_1,branch);
        branchsp.setAdapter(branchs);
        branchsp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                branchh=branchsp.getAdapter().getItem(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        ArrayAdapter<String> semesterr=new ArrayAdapter<String>(Add_Student.this,android.R.layout.simple_list_item_1,semester);
        semsp.setAdapter(semesterr);
        semsp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                sem1=semsp.getAdapter().getItem(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        final ArrayAdapter<String> sectionn=new ArrayAdapter<String>(Add_Student.this,android.R.layout.simple_list_item_1,sectionss);
        secsp.setAdapter(sectionn);
        secsp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                section=secsp.getAdapter().getItem(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        add.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                String sid1=id.getText().toString();
                String sid=sid1.toUpperCase();
                String name1=name.getText().toString();
                String namess=name1.toUpperCase();
                String id3=sid1.toLowerCase();
                String sname=namess.toLowerCase();
                String username=sname+id3;
                sem=Integer.parseInt(sem1);
                String password=sname;
                if(sid.equals(null)||namess.equals(null)||branchh.equals("--Select Branch--")||sem1.equals("--Select Semester--")||section.equals("--Select Section--"))
                {
                    Toast.makeText(Add_Student.this, "Fill the Form Properly", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Cursor cursor = dbh.checkid(sid);
                    cursor.moveToFirst();
                    if (cursor.getCount() > 0)
                    {
                        Toast.makeText(Add_Student.this, "ID: " + sid + "Already Exist", Toast.LENGTH_SHORT).show();
                    }
                    else
                    {
                        long id2 = dbh.addstudent(sid, namess, username, password, branchh, sem, section);
                        if (id2 > 0) {
                            onCreate(savedInstanceState);
                            Toast.makeText(Add_Student.this, "Student Registered Successfully", Toast.LENGTH_LONG).show();
                        } else {
                            Toast.makeText(Add_Student.this, "Student not Registered", Toast.LENGTH_LONG).show();
                        }
                    }

                }
            }
        });

    }
}
