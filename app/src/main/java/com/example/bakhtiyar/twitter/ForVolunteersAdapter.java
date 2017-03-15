package com.example.bakhtiyar.twitter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.List;

/**
 * Created by Syed_Bakhtiyar on 15/03/2017.
 */

public class ForVolunteersAdapter extends ArrayAdapter<PostVolunteer> {

    LayoutInflater inflater;

    TextView name;



    public ForVolunteersAdapter(Context context, int resource, List<PostVolunteer> objects) {
        super(context, resource, objects);
    }



    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        view = inflater.from(getContext()).inflate(R.layout.for_volunteer_lists,viewGroup,false);

        name = (TextView) view.findViewById(R.id.name);

        PostVolunteer postVolunteer =  getItem(i);

        name.setText(postVolunteer.getName());

        return view;

    }



}

