package com.example.bakhtiyar.twitter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Syed_Bakhtiyar on 3/17/2017.
 */

public class LikesAdapter extends BaseAdapter {


    ArrayList<LikesClass> arrayList;

    Context context;

    TextView name;

    LayoutInflater inflater;


    public LikesAdapter(ArrayList<LikesClass> arrayList, Context context) {
        this.arrayList = arrayList;
        this.context = context;
    }

    @Override
    public int getCount() {
        return arrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        convertView = inflater.from(context).inflate(R.layout.for_volunteer_lists,parent,false);

        name = (TextView) convertView.findViewById(R.id.name);

        name.setText(arrayList.get(position).getName());


        return convertView;
    }
}
