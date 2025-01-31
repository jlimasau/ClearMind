package com.clearmind123;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.provider.AlarmClock;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.material.snackbar.Snackbar;

public class Tools extends AppCompatActivity {
    AdView mAdView;
    ConstraintLayout layout1;
    String whichlist = "1st";
    Menu optionsMenu;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tools);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        layout1 = findViewById(R.id.constraint);


        Button accepts = findViewById(R.id.accepts);
        Button hulahoop = findViewById(R.id.hulahoop);
        Button abcde = findViewById(R.id.abcde);
        Button quickstart = findViewById(R.id.quickstart);
        Button pomodoro = findViewById(R.id.pomodoro);
        Button prosncons = findViewById(R.id.prosncons);
        Button gratitude = findViewById(R.id.gratitude);
        Button copeahead = findViewById(R.id.copeahead);


        accepts.setOnClickListener(view -> openActivityAccepts());
        hulahoop.setOnClickListener(view -> openActivityHulahoop());
        abcde.setOnClickListener(view -> openActivityABCDE());
        quickstart.setOnClickListener(view -> openActivityquickstart());
        pomodoro.setOnClickListener(view -> openActivityPomodoro());
        prosncons.setOnClickListener(view -> openActivityProsncons());
        gratitude.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Tools.this, Gratitude.class);
                startActivity(intent);

            }
        });

        copeahead.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Tools.this, CopeAhead.class);
                startActivity(intent);

            }
        });


        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

    }



    private void openActivityPomodoro() {
        //Toast.makeText(getApplicationContext(), getString(R.string.pomodoroText), Toast.LENGTH_LONG).show();

 /*       Snackbar.make(layout1, getString(R.string.pomodoroText), Snackbar.LENGTH_LONG).setAction("Return", new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent2 = new Intent(Tools.this, Tools.class);
                startActivity(intent2);
            }
        });*/


        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setMessage(R.string.pomodoroText);
        //AlertDialog dialog = builder.create();
       // builder.create().show();

        final AlertDialog alert = builder.create();
        alert.show();



       /* Intent i = new Intent(AlarmClock.ACTION_SET_TIMER);
        i.putExtra(AlarmClock.EXTRA_LENGTH, 1500);
        i.putExtra(AlarmClock.EXTRA_SKIP_UI, true);
        startActivity(i);*/


        alert.setOnKeyListener(new Dialog.OnKeyListener() {
            @Override
            public boolean onKey(DialogInterface arg0, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_BACK) {
                    recreate();
                }
                return true;
            }
        });


        alert.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(final DialogInterface arg0) {
                // do something
                Intent i = new Intent(AlarmClock.ACTION_SET_TIMER);
                i.putExtra(AlarmClock.EXTRA_LENGTH, 1500);
               // i.putExtra(AlarmClock.EXTRA_SKIP_UI, true);
                startActivity(i);
            }
        });











    }

    private void openActivityquickstart() {


 /*       SharedPreferences sharedPreferences = getSharedPreferences("shared preferences", MODE_PRIVATE);
        whichlist = sharedPreferences.getString("whichlist", "1st");
        System.out.println(whichlist);
        if (whichlist.equals("2nd")){
            Intent intent2 = new Intent(this, QuickStart2.class);
            startActivity(intent2);
            System.out.println("this");
        }
        if (whichlist.equals("3rd")){
            Intent intent3 = new Intent(this, QuickStart3.class);
            startActivity(intent3);
        }
        if (whichlist.equals("1st")){*/

            Intent intent1 = new Intent(this, QuickStart2.class);
            startActivity(intent1);
        //}
        //first pick up variable in the beginning use it to set class
    }


    public void openActivityAccepts(){
        Intent intent = new Intent(this, Accepts.class);
        startActivity(intent);
    }
    public void openActivityHulahoop(){
        Intent intent = new Intent(this, Hulahoop.class);
        startActivity(intent);
    }
    public void openActivityABCDE(){
        Intent intent = new Intent(this, ABCDE.class);
        startActivity(intent);
    }

    private void openActivityProsncons() {
        Intent intent = new Intent(this, Prosncons.class);
        startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(Tools.this, MainActivity.class);
        startActivity(intent);
        //intent start intent tools.java
        // super.onBackPressed();
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.actionbar, menu);
        optionsMenu = menu;
        //MenuItem item = optionsMenu.getItem(R.id.help);
        MenuItem item = optionsMenu.findItem(R.id.help);
        item.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(@NonNull MenuItem menuItem) {

                AlertDialog.Builder builder = new AlertDialog.Builder(Tools.this);
                builder.setMessage(R.string.tools_text);
                final AlertDialog alert = builder.create();
                alert.show();
                System.out.println("THIS!!!!");
                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }



}