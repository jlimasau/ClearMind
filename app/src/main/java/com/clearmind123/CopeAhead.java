package com.clearmind123;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class CopeAhead extends AppCompatActivity {
    ArrayList<String> myArrayList = new ArrayList<>();
    AdView mAdView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cope_ahead);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);



        loadData();

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(CopeAhead.this, android.R.layout.simple_list_item_1, myArrayList);
        EditText step1 = findViewById(R.id.step1input);
        EditText step2 = findViewById(R.id.step2input);
        EditText step3 = findViewById(R.id.step3input);

        Button myEntries = findViewById(R.id.myEntries);


        step1.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                step1.onTouchEvent(motionEvent);
                step1.setSelection(step1.getText().length());
                return true;
            }
        });

        step2.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                step2.onTouchEvent(motionEvent);
                step2.setSelection(step2.getText().length());
                return true;
            }
        });

        step3.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                step3.onTouchEvent(motionEvent);
                step3.setSelection(step3.getText().length());
                return true;
            }
        });


        myEntries.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(CopeAhead.this, CopeAheadSaveData.class);
                startActivity(intent1);
            }
        });

        Button savebutton = findViewById(R.id.savebutton);
        savebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(
                        step1.getText().toString().matches(getResources().getString(R.string.step1input))
                                || step2.getText().toString().matches(getResources().getString(R.string.step2input))
                                || step3.getText().toString().matches(getResources().getString(R.string.step3input))
                ){
                    Toast.makeText(CopeAhead.this, CopeAhead.this.getString(R.string.emptytoast), Toast.LENGTH_SHORT).show();
                }
                else{

                    System.out.println(myArrayList);
                    adapter.add(String.valueOf(step1.getText()));
                    adapter.add(String.valueOf(step2.getText()));
                    adapter.add(String.valueOf(step3.getText()));
                    adapter.add("");
                    saveData();
                    step1.setText(R.string.step1input);
                    step2.setText(R.string.step2input);
                    step3.setText(R.string.step3input);
                    Toast.makeText(CopeAhead.this, CopeAhead.this.getString(R.string.MyEntries), Toast.LENGTH_SHORT).show();

                }


            }
        });



        mAdView = findViewById(R.id.banner1);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

    }

    private void saveData() {
        SharedPreferences sharedPreferences = getSharedPreferences("shared preferences", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(myArrayList);
        editor.putString("copeahead list" , json);
        editor.apply();
    }
    private void loadData(){
        SharedPreferences sharedPreferences = getSharedPreferences("shared preferences", MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences.getString("copeahead list", null);
        Type type = new TypeToken<ArrayList>() {}.getType();
        myArrayList = gson.fromJson(json, type);

        if (myArrayList == null) {
            myArrayList = new ArrayList<>();
        }

    }



    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.actionbar, menu);
        Menu optionsMenu = menu;

        MenuItem item = optionsMenu.findItem(R.id.help);
        item.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(@NonNull MenuItem menuItem) {

                AlertDialog.Builder builder = new AlertDialog.Builder(CopeAhead.this);
                builder.setMessage(R.string.copeahead_text);
                final AlertDialog alert = builder.create();
                alert.show();


                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }


    @Override
    public void onBackPressed() {
        Intent intent = new Intent(CopeAhead.this, Tools.class);
        startActivity(intent);
    }

}