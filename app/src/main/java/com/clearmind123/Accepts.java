package com.clearmind123;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

public class Accepts extends AppCompatActivity {



    private TextView texty1;

    AdView mAdView;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accepts);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        texty1 = findViewById(R.id.textView2);
        Button activities = findViewById(R.id.activities);
        Button contributing = findViewById(R.id.contributing);
        Button comparisons = findViewById(R.id.comparisons);
        Button emotions = findViewById(R.id.emotions);
        Button pushing = findViewById(R.id.pushing);
        Button thoughts = findViewById(R.id.thoughts);
        Button sensations = findViewById(R.id.sensations);
  /*      Button title2 = findViewById(R.id.title1);*/




   /*     title2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                texty1.setText(R.string.accepts);
            }
        });*/

        activities.setOnClickListener(new View.OnClickListener() {
                                          @Override
                                          public void onClick(View view) {

                                              texty1.setText(R.string.activities);

                                          }
                                      });







        contributing.setOnClickListener(view -> texty1.setText(R.string.contributing));

        comparisons.setOnClickListener(view -> texty1.setText(R.string.comparisons));
        emotions.setOnClickListener(view -> texty1.setText(R.string.emotions));
        pushing.setOnClickListener(view -> texty1.setText(R.string.pushing));
        thoughts.setOnClickListener(view -> texty1.setText(R.string.thoughts));
        sensations.setOnClickListener(view -> texty1.setText(R.string.sensations));

        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.actionbar, menu);
        Menu optionsMenu = menu;
        //MenuItem item = optionsMenu.getItem(R.id.help);


        MenuItem item = optionsMenu.findItem(R.id.help);
        item.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(@NonNull MenuItem menuItem) {

                AlertDialog.Builder builder = new AlertDialog.Builder(Accepts.this);
                builder.setMessage(R.string.accepts);
                final AlertDialog alert = builder.create();
                alert.show();
                System.out.println("THIS!!!!");



                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }

}