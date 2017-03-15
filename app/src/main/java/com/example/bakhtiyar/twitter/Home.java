package com.example.bakhtiyar.twitter;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class Home extends AppCompatActivity {

    TabLayout tabLayout;

    ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        PostFragment postFragment = new PostFragment(getSupportFragmentManager());

        tabLayout = (TabLayout) findViewById(R.id.tab);

        viewPager = (ViewPager) findViewById(R.id.vp);

        viewPager.setAdapter(postFragment);

        tabLayout.setupWithViewPager(viewPager);


    }
}
