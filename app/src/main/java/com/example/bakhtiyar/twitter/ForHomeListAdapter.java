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

public class ForHomeListAdapter extends BaseAdapter {
    ArrayList<String> arrayList;

    Context context;

    LayoutInflater inflater;

    TextView textView;

    public ForHomeListAdapter(ArrayList<String> arrayList, Context context) {
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
        convertView = inflater.from(context).inflate(R.layout.forhomelist,parent,false);

        textView = (TextView) convertView.findViewById(R.id.name);

        textView.setText(arrayList.get(position));


        return convertView;
    }
}
