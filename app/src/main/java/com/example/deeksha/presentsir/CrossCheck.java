package com.example.deeksha.presentsir;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

/**
 * Created by Deeksha on 7/16/2016.
 */
public class
        CrossCheck extends Activity
{
    String[] branches={"--Select Branch--","Computer_Science","Electrical","Electronic_&_Comm."};
    String [] semester={"--Select Semester--","1","2","3","4","5","6","7","8"};
    String [] sectionss={"--Select Section--","A","B","C"};
    Spinner branchsp,sectionsp,semestersp;
    Button btnall;
    String branchh,sectionnn,semester1;
    DBHandler dbh;
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.crosscheck);
        branchsp=(Spinner)findViewById(R.id.branchsp);
        sectionsp=(Spinner)findViewById(R.id.sectionsp);
        semestersp=(Spinner)findViewById(R.id.semestersp);
        btnall=(Button)findViewById(R.id.btnall);

        dbh=new DBHandler(this);

        final ArrayAdapter<String> branchs=new ArrayAdapter<String>(CrossCheck.this,android.R.layout.simple_list_item_1,branches);
        final ArrayAdapter<String> semesterr=new ArrayAdapter<String>(CrossCheck.this,android.R.layout.simple_list_item_1,semester);
        final ArrayAdapter<String> sectionn=new ArrayAdapter<String>(CrossCheck.this,android.R.layout.simple_list_item_1,sectionss);
        branchsp.setAdapter(branchs);
        sectionsp.setAdapter(sectionn);
        semestersp.setAdapter(semesterr);


        btnall.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                branchh = (String)branchsp.getSelectedItem();
                sectionnn = (String)sectionsp.getSelectedItem();
                semester1 = (String)semestersp.getSelectedItem();

                if(branchh.equals("--Select Branch--")&&sectionn.equals("--Select Section")&&semester1.equals("--Select Semester--"))
                {
                    Toast.makeText(CrossCheck.this, "form incomplete", Toast.LENGTH_SHORT).show();
                }
                else {
                    Log.d("de", "onClick:click  ");
                    Intent all = new Intent(CrossCheck.this, AllTime.class);
                    all.putExtra("branch", branchh);
                    all.putExtra("section", sectionnn);
                    all.putExtra("semester", semester1);
                    Log.d("de1", "onClick: " + branchh + "-" + sectionnn + "-" + semester1);
                    startActivity(all);
                }
            }
        });

    }
}
