package com.example.deeksha.presentsir;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

/**
 * Created by Deeksha on 7/6/2016.
 */
public class FacultyMenu extends Activity
{
    String strDate;
    int day,yr,month;
    String[] branches={"--Select Branch--","Computer_Science","Electrical","Electronic_&_Comm."};
    String [] semester={"--Select Semester--","1","2","3","4","5","6","7","8"};
    String [] sectionss={"--Select Section--","A","B","C"};
    String [] option={"Mark Attendence","Add Student","Delete Student","Compile Attendance"};

    GridView gridview;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.facultymenu);
        gridview=(GridView)findViewById(R.id.gridview);
        ArrayAdapter<String> adapter=new ArrayAdapter<String>(FacultyMenu.this,android.R.layout.simple_list_item_1,option);
        gridview.setAdapter(adapter);
        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {

                String str=gridview.getAdapter().getItem(position).toString();
                if(str.equals("Mark Attendence"))
                {
                    showAlert();
                }
                else if(str.equals("Add Student"))
                {
                    Intent addstudent=new Intent(FacultyMenu.this,Add_Student.class);
                    startActivity(addstudent);
                }
                else if(str.equals("Compile Attendance"))
                {
                    Intent intent=new Intent(FacultyMenu.this,CrossCheck.class);
                    startActivity(intent);
                }
                else if(str.equals("Delete Student"))
                {
                    showAlertDelete();
                }
            }
        });

    }

    private void showAlert()
    {
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        LinearLayout layout       = new LinearLayout(this);
        final Spinner b        = new Spinner(this);
        final Spinner se       = new Spinner(this);
        final Spinner s        = new Spinner(this);

        layout.setOrientation(LinearLayout.VERTICAL);
        layout.addView(b);
        layout.addView(se);
        layout.addView(s);
        alert.setTitle("Choose Branch,Semester,Section");
        alert.setView(layout);
        final ArrayAdapter<String> branchs=new ArrayAdapter<String>(FacultyMenu.this,android.R.layout.simple_list_item_1,branches);
        final ArrayAdapter<String> semesterr=new ArrayAdapter<String>(FacultyMenu.this,android.R.layout.simple_list_item_1,semester);
        final ArrayAdapter<String> sectionn=new ArrayAdapter<String>(FacultyMenu.this,android.R.layout.simple_list_item_1,sectionss);
        b.setAdapter(branchs);
        se.setAdapter(semesterr);
        s.setAdapter(sectionn);

        alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        alert.setPositiveButton("Done", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which)
            {
                String branchh = (String)b.getSelectedItem();
                String sectionnn = (String)s.getSelectedItem();
                String  semester1 = (String)se.getSelectedItem();

                if (branchh.equals("--Select--") && sectionnn.equals("--Select--"))
                {
                    Toast.makeText(FacultyMenu.this, "Fill branch,semester,section first", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Intent intent = new Intent(FacultyMenu.this, Mark_Attendence.class);
                    intent.putExtra("Branch", branchh);
                    intent.putExtra("Semester", semester1);
                    intent.putExtra("Section", sectionnn);
                    startActivity(intent);
                    Toast.makeText(getBaseContext(), "Welcome", Toast.LENGTH_SHORT).show();
                }
            }
        });
        alert.show();
    }


    private void showAlertCross()
    {
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        LinearLayout layout       = new LinearLayout(this);
        final Spinner b        = new Spinner(this);
        final Spinner se       = new Spinner(this);
        final Spinner s        = new Spinner(this);

        layout.setOrientation(LinearLayout.VERTICAL);
        layout.addView(b);
        layout.addView(se);
        layout.addView(s);
        alert.setTitle("Choose Branch,Semester,Section");
        alert.setView(layout);
        final ArrayAdapter<String> branchs=new ArrayAdapter<String>(FacultyMenu.this,android.R.layout.simple_list_item_1,branches);
        final ArrayAdapter<String> semesterr=new ArrayAdapter<String>(FacultyMenu.this,android.R.layout.simple_list_item_1,semester);
        final ArrayAdapter<String> sectionn=new ArrayAdapter<String>(FacultyMenu.this,android.R.layout.simple_list_item_1,sectionss);
        b.setAdapter(branchs);
        se.setAdapter(semesterr);
        s.setAdapter(sectionn);

        alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        alert.setPositiveButton("Done", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which)
            {
                String branchh = (String)b.getSelectedItem();
                String sectionnn = (String)s.getSelectedItem();
                String  semester1 = (String)se.getSelectedItem();

                if (branchh.equals("--Select Branch--") && sectionnn.equals("--Select Section--"))
                {
                    Toast.makeText(FacultyMenu.this, "Fill branch,semester,section first", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Intent intent = new Intent(FacultyMenu.this, CrossCheck.class);
                    intent.putExtra("Branch", branchh);
                    intent.putExtra("Semester", semester1);
                    intent.putExtra("Section", sectionnn);
                    startActivity(intent);
                    Toast.makeText(getBaseContext(), "Welcome", Toast.LENGTH_SHORT).show();
                }
            }
        });
        alert.show();
    }



    private void showAlertDelete()
    {
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        LinearLayout layout       = new LinearLayout(this);
        final Spinner b        = new Spinner(this);
        final Spinner se       = new Spinner(this);
        final Spinner s        = new Spinner(this);

        layout.setOrientation(LinearLayout.VERTICAL);
        layout.addView(b);
        layout.addView(se);
        layout.addView(s);
        alert.setTitle("Choose Branch,Semester,Section");
        alert.setView(layout);
        final ArrayAdapter<String> branchs=new ArrayAdapter<String>(FacultyMenu.this,android.R.layout.simple_list_item_1,branches);
        final ArrayAdapter<String> semesterr=new ArrayAdapter<String>(FacultyMenu.this,android.R.layout.simple_list_item_1,semester);
        final ArrayAdapter<String> sectionn=new ArrayAdapter<String>(FacultyMenu.this,android.R.layout.simple_list_item_1,sectionss);
        b.setAdapter(branchs);
        se.setAdapter(semesterr);
        s.setAdapter(sectionn);

        alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        alert.setPositiveButton("Done", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which)
            {
                String branchh = (String)b.getSelectedItem();
                String sectionnn = (String)s.getSelectedItem();
                String  semester1 = (String)se.getSelectedItem();

                if (branchh.equals("--Select--") && sectionnn.equals("--Select--"))
                {
                    Toast.makeText(FacultyMenu.this, "Fill branch,semester,section first", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Intent intent = new Intent(FacultyMenu.this,Delete_Student.class);
                    intent.putExtra("Branch", branchh);
                    intent.putExtra("Semester", semester1);
                    intent.putExtra("Section", sectionnn);
                    startActivity(intent);
                }
            }
        });
        alert.show();
    }

}