package com.example.bakhtiyar.twitter;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.FirebaseDatabase;

import static com.google.android.gms.internal.zzt.TAG;


/**
 * A simple {@link Fragment} subclass.
 */
public class PostWriteFragment extends Fragment {

    Spinner sblood,surgent,scountry,sstate,scity,shospital,srelation;

    EditText sphone, sinfo,sunit;



    String[] countarray = {"Pakistan"};

    String[] relationarray = {"Father","Mother","Son","Daughter","Aunt","Uncle","Nephew","Niece","Friend","Neighbour","None"};

    String[] statearray = {"Sindh","Kpk","Punjab","Baluchistan"};

    String[] cityarray = {"Karachi","Peshawar","Lahore","Quetta"};

    String[] hospitalarray = {"Indus Hospital","Ziauddin Hospital","Agha Khan Hospital","Liaquat National Hospital","OMI","Jinnah Hospital","Holy Family Hospital"};

    String[] bloodarray = {"A positive","A Negative","B positive","B Negative","O Positive","O Negative"};

    String[] urgentArray = {"Urgent","With in 5 hours","With in 12 hours","with in 24 hours","Witin 2 days","within weak"};


    int unit;

    String blood=null;
    String urgent=null;
    String country=null;
    String state=null;
    String city=null;
    String hospital=null;
    String relation=null;

    String phone=null;

    String info=null;


    View v;



    public PostWriteFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_post_write, container, false);

        sblood = (Spinner) v.findViewById(R.id.blood);


        surgent = (Spinner) v.findViewById(R.id.urgent);

        scountry = (Spinner) v.findViewById(R.id.country);

        sstate = (Spinner) v.findViewById(R.id.state);

        scity = (Spinner) v.findViewById(R.id.city);

        shospital = (Spinner) v.findViewById(R.id.hospital);

        srelation = (Spinner) v.findViewById(R.id.relation);

        sphone = (EditText) v.findViewById(R.id.phone);

        sinfo = (EditText) v.findViewById(R.id.instruction);

        sunit = (EditText) v.findViewById(R.id.units);


        ArrayAdapter<String> bloodadapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_dropdown_item, bloodarray);
        sblood.setAdapter(bloodadapter);

        ArrayAdapter<String> urgentadapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_dropdown_item, urgentArray);
        surgent.setAdapter(urgentadapter);

        ArrayAdapter<String> stadapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_dropdown_item, statearray);
        sstate.setAdapter(stadapter);

        ArrayAdapter<String> countadapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_dropdown_item, countarray);
        scountry.setAdapter(countadapter);

        ArrayAdapter<String> ctadapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_dropdown_item, cityarray);
        scity.setAdapter(ctadapter);

        ArrayAdapter<String> sreadapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_dropdown_item, relationarray);
        srelation.setAdapter(sreadapter);

        ArrayAdapter<String> shospitaladapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_dropdown_item, hospitalarray);
        shospital.setAdapter(shospitaladapter);




        sblood.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {


                blood = adapterView.getItemAtPosition(i).toString();

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        surgent.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {


                urgent = adapterView.getItemAtPosition(i).toString();

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        scountry.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {


                country = adapterView.getItemAtPosition(i).toString();

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        scity.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {


                city = adapterView.getItemAtPosition(i).toString();

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        sstate.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {


                state = adapterView.getItemAtPosition(i).toString();

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        shospital.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {


                hospital = adapterView.getItemAtPosition(i).toString();

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        srelation.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {


                relation = adapterView.getItemAtPosition(i).toString();

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
////////////////////////////
        sblood.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {


                blood = adapterView.getItemAtPosition(i).toString();

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });






        v.findViewById(R.id.post).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


               phone=sphone.getText().toString().trim();

                info=sinfo.getText().toString().trim();

                try {

                    unit = Integer.parseInt(sunit.getText().toString().trim());
                }catch (Exception e){

                    Toast.makeText(getContext(), "Please Enter a number", Toast.LENGTH_SHORT).show();

                    sunit.setText("");


                }

                if(TextUtils.isEmpty(phone)){

                    Toast.makeText(getContext(), "Please Enter Phone Number", Toast.LENGTH_SHORT).show();


                    sphone.setText("");

                    return;
                }
                if (TextUtils.isEmpty(info)){

                    info = "No status";



                }

                try {


                    UsersPost usersPost = new UsersPost(StaticVariables.userInfo.getName(), StaticVariables.userInfo.getEmail()
                            , StaticVariables.userInfo.getBloodgroup(), StaticVariables.uid, FirebaseDatabase.getInstance().getReference().child("Posting").push().getKey(), unit, blood, urgent, country, state, city, hospital, relation, phone, info, 0);


                    FirebaseDatabase.getInstance().getReference().child("Posting").child(usersPost.getPush()).setValue(usersPost);

                    FirebaseDatabase.getInstance().getReference().child("MyPosting").child(StaticVariables.uid).child(usersPost.getPush()).setValue(usersPost);

                    Toast.makeText(getContext(), "Posted", Toast.LENGTH_SHORT).show();

                    Intent intent = getActivity().getIntent();
                    getActivity().finish();
                    startActivity(intent);
                }catch (Exception e){

                    Log.d("myerror", "onClick: "+e);

                    Toast.makeText(getContext(), "Sorry You Internet is not available", Toast.LENGTH_SHORT).show();

                }

            }
        });


        return v;
    }

}
