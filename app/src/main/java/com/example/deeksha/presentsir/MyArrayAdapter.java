package com.example.deeksha.presentsir;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Deeksha on 7/6/2016.
 */
public class MyArrayAdapter  extends BaseAdapter
{

    Context ctx;
    LayoutInflater lInflater;
    ArrayList<Student> objects;

    MyArrayAdapter(Context context, ArrayList<Student> products) {
        ctx = context;
        objects = products;
        }
    @Override
    public int getCount() {
        return objects.size();
    }

    @Override
    public Object getItem(int position) {
        return objects.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent)
    {
        lInflater = (LayoutInflater) ctx
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View view = convertView;
        if (view == null) {
            view = lInflater.inflate(R.layout.mylist, parent, false);
        }

        Student student = getStudent(position);


        ImageView imgView = (ImageView) view.findViewById(R.id.imgView);

        CheckBox cbBuy = (CheckBox) view.findViewById(R.id.cbBox);
        cbBuy.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                getStudent((Integer) buttonView.getTag()).box = isChecked;
            }
        });




        TextView nameView = (TextView) view.findViewById(R.id.name);
        nameView.setText(student.getName());

        TextView sidView = (TextView) view.findViewById(R.id.sid);
        sidView.setText(student.getSid());

        cbBuy.setTag(position);
        cbBuy.setChecked(student.box);
        return view;
    }
    Student getStudent(int position) {
        return ((Student) getItem(position));
    }
    ArrayList<Student> getBoxPresent() {
        ArrayList<Student> box = new ArrayList<Student>();
        for (Student p : objects) {
            if (p.box)
                box.add(p);
        }
        return box;
    }
    ArrayList<Student> getBoxAbsent() {
        ArrayList<Student> box = new ArrayList<Student>();
        for (Student p : objects) {
            if (!p.box)
                box.add(p);
        }
        return box;
    }
}

