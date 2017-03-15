package com.example.bakhtiyar.twitter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Bakhtiyar on 2/26/2017.
 */
public class CommintListAdapter extends BaseAdapter {

    ArrayList<MyCommints> arrayList;

    TextView textView,name;

    Context context;

    LayoutInflater inflater;

    public CommintListAdapter(ArrayList<MyCommints> arrayList, Context context) {
        this.arrayList = arrayList;
        this.context = context;
    }

    @Override
    public int getCount() {
        return arrayList.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
       view = inflater.from(context).inflate(R.layout.commint_list,viewGroup,false);

        name = (TextView) view.findViewById(R.id.name);

        textView = (TextView) view.findViewById(R.id.comint);

        name.setText(arrayList.get(i).getName());

        textView.setText(arrayList.get(i).getCommint());


        return view;
    }
}
