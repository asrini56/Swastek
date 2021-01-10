package com.example.swasdektest;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.Display;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;
import java.util.Observable;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity{

    RecyclerView recyclerView;
    MyAdapterCard adapterCard;
    Data data = new Data();
    private DrawerLayout d1;
    private ActionBarDrawerToggle abdt;
    private NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        d1 = (DrawerLayout)findViewById(R.id.mainActivity);
        abdt = new ActionBarDrawerToggle(this,d1,R.string.Open,R.string.Close);
        abdt.setDrawerIndicatorEnabled(true);

        d1.addDrawerListener(abdt);
        abdt.syncState();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        navigationView = (NavigationView)findViewById(R.id.navBar);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();

                if(id == R.id.myProfile){
                    Toast.makeText(MainActivity.this,"My Profile",Toast.LENGTH_SHORT).show();

                }
                if(id == R.id.mainLogout){
                    FirebaseAuth.getInstance().signOut();
                    startActivity(new Intent(getApplicationContext(), Login.class));
                    finish();
                }
                return true;
            }
        });

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        adapterCard = new MyAdapterCard(this,getMyList());
        recyclerView.setAdapter(adapterCard);

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(abdt.onOptionsItemSelected(item))
            return true;

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed(){
        moveTaskToBack(true);
    }
    private ArrayList<ModelCard> getMyList(){
        ArrayList<ModelCard> models = new ArrayList<>();
        ModelCard m = new ModelCard();

        m.setTitle("Heart Rate");
        m.setImg(R.drawable.heartrate);
        m.setDescription("88 bpm");
        models.add(m);

        m = new ModelCard();
        m.setTitle("Body Temperature");
        m.setDescription("37.2 C");
        m.setImg(R.drawable.temperature);
        models.add(m);

        m = new ModelCard();
        m.setTitle("SP02");
        m.setDescription("91 %");
        m.setImg(R.drawable.sp02);
        models.add(m);

        m = new ModelCard();
        m.setTitle("Blood Pressure");
        m.setDescription("120/80");
        m.setImg(R.drawable.bp);
        models.add(m);

        m = new ModelCard();
        m.setTitle("Fall");
        m.setDescription("Fall has not occured");
        m.setImg(R.drawable.fall);
        models.add(m);

        return models;
    }

    public void logout(View view){
        FirebaseAuth.getInstance().signOut();
        startActivity(new Intent(getApplicationContext(), Login.class));
        finish();
    }

    private ArrayList<ModelCard> getMyListData(){
        ArrayList<ModelCard> models = new ArrayList<>();
        ModelCard m = new ModelCard();

        m.setTitle("Heart Rate");
        m.setImg(R.drawable.heartrate);
        int min = 70;
        int max = 90;
        int random = new Random().nextInt((max - min) + 1) + min;
        m.setDescription(random+" bpm");
        models.add(m);

        m = new ModelCard();
        m.setTitle("Body Temperature");
        Random r = new Random();
        double val = 35.5 + (37 - 35.5) * r.nextDouble();
        val = val*100;
        val = Math.round(val);
        val = val /100;

        m.setDescription(val+" C");
        m.setImg(R.drawable.temperature);
        models.add(m);

        m = new ModelCard();
        m.setTitle("SP02");
        min = 90;
        max = 100;
        random = new Random().nextInt((max - min) + 1) + min;
        m.setDescription(random+" %");
        m.setImg(R.drawable.sp02);
        models.add(m);

        m = new ModelCard();
        m.setTitle("Blood Pressure");
        m.setDescription("120/80");
        m.setImg(R.drawable.bp);
        models.add(m);

        m = new ModelCard();
        m.setTitle("Fall");
        m.setDescription("Fall has not occured");
        m.setImg(R.drawable.fall);
        models.add(m);

        return models;
    }


    public void getData(View view){
        adapterCard = new MyAdapterCard(this,getMyListData());
        recyclerView.setAdapter(adapterCard);
    }


}