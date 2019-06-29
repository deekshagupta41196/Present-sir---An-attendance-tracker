package com.example.deeksha.presentsir;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Deeksha on 7/17/2016.
 */
public class StudentAdapter extends BaseAdapter {
    Context ctx;
    LayoutInflater lInflater;
    ArrayList<Studentpojo> objects;

    StudentAdapter(Context context, ArrayList<Studentpojo> products) {
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
    public View getView(final int position, View convertView, ViewGroup parent) {
        lInflater = (LayoutInflater) ctx
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View view = lInflater.inflate(R.layout.mlist, parent, false);

        Studentpojo studentpojo= objects.get(position);


        ImageView imageview = (ImageView) view.findViewById(R.id.ivImage);

        TextView txtsname = (TextView) view.findViewById(R.id.txtsname);
        txtsname.setText(studentpojo.getSname());

        TextView txtsid = (TextView) view.findViewById(R.id.txtsid);
        txtsid.setText(studentpojo.getSid());

        TextView txtpresent = (TextView) view.findViewById(R.id.txtpresent);
        txtpresent.setText(studentpojo.getPresent());

        TextView txttotal = (TextView) view.findViewById(R.id.txttotal);
        txttotal.setText(studentpojo.getTotal());

        imageview.setImageResource(R.drawable.face);

        return view;
    }
}