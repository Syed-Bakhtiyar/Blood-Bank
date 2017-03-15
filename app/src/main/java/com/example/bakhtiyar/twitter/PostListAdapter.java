package com.example.bakhtiyar.twitter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import java.util.ArrayList;

/**
 * Created by Bakhtiyar on 2/26/2017.
 */
public class PostListAdapter extends BaseAdapter {


    ArrayList<PostVolunteer> arrayListTest;

  ArrayList<UsersPost> arrayList;

    LayoutInflater inflater;

    TextView name, unit, hospitalrelation,urgent,phone, info, volunteer;

    Button voll;




    Context context;

    public PostListAdapter(ArrayList<UsersPost> arrayList, Context context) {
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


        arrayListTest = new ArrayList<>();




        final int[] count = {0};
        final int temp = i;

        FirebaseDatabase.getInstance().getReference().child("Volunteer").child(arrayList.get(temp).getPush()).addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {


                PostVolunteer postVolunteer = dataSnapshot.getValue(PostVolunteer.class);

                arrayListTest.add(postVolunteer);


            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        final int[] volll = {arrayList.get(temp).getVolunteer()};

        view = inflater.from(context).inflate(R.layout.view_post_list,viewGroup,false);




        name = (TextView) view.findViewById(R.id.name);

        unit = (TextView) view.findViewById(R.id.units);

        hospitalrelation = (TextView) view.findViewById(R.id.hospitalrelation);

        urgent = (TextView) view.findViewById(R.id.urgent);

        phone = (TextView) view.findViewById(R.id.phone);

        info = (TextView) view.findViewById(R.id.instruction);

      //  volunteer = (TextView) view.findViewById(R.id.volunteer);

        name.setText(arrayList.get(i).getName());

        unit.setText(arrayList.get(i).getUnits()+" of "+arrayList.get(i).getBlood() +" is required");

        hospitalrelation.setText("At "+arrayList.get(i).getHospital()+" for my "+arrayList.get(i).getRelation());

        urgent.setText(arrayList.get(i).getUrgency());

        phone.setText("Contact at: "+arrayList.get(i).getContact());

        info.setText(arrayList.get(i).getInfo());

       // volunteer.setText("Volunteers uptill now: " +arrayList.get(i).getVolunteer());


        voll = (Button) view.findViewById(R.id.vol);





        if(arrayList.get(i).getVolunteer() == 0){




        }

        voll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                PostVolunteer postVolunteer = new PostVolunteer(StaticVariables.userInfo.getName(),arrayList.get(temp).getPush());


                FirebaseDatabase.getInstance().getReference().child("Volunteer").child(arrayList.get(temp).getPush()).child(StaticVariables.uid).setValue(postVolunteer);


            }
        });

        view.findViewById(R.id.comment).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                StaticVariables.push = arrayList.get(temp).getPush();


                context.startActivity(new Intent(context.getApplicationContext(),Commints.class));




            }
        });

//        TextView name, unit, hospitalrelation,urgent,phone, info, volunteer;
        return view;
    }


}
