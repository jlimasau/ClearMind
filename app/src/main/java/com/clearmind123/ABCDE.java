package com.clearmind123;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Random;

public class ABCDE extends AppCompatActivity {

    private TextView activating;
    private TextView belief;
    private TextView consequence;
    private TextView dispute;
    private TextView effect;
    ArrayList<String> myArrayList = new ArrayList<>();
    AdView mAdView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_abcde);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        loadData(); //does it need to call loaddata?

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(ABCDE.this, android.R.layout.simple_list_item_1, myArrayList);


        EditText activating = findViewById(R.id.activating1);
        EditText belief = findViewById(R.id.belief1);
        EditText consequence = findViewById(R.id.consequence1);
        EditText dispute = findViewById(R.id.dispute1);
        EditText  effect = findViewById(R.id.effective1);

       // activating.setSelection(activating.getText().length());
       // activating.append("");

//sets cursor to end of text on touch
        activating.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                activating.onTouchEvent(motionEvent);
                activating.setSelection(activating.getText().length());
                return true;
            }
        });

        belief.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                belief.onTouchEvent(motionEvent);
                belief.setSelection(belief.getText().length());
                return true;
            }
        });

        consequence.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                consequence.onTouchEvent(motionEvent);
                consequence.setSelection(consequence.getText().length());
                return true;
            }
        });

        dispute.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                dispute.onTouchEvent(motionEvent);
                dispute.setSelection(dispute.getText().length());
                return true;
            }
        });

        effect.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                effect.onTouchEvent(motionEvent);
                effect.setSelection(effect.getText().length());
                return true;
            }
        });


        Button myEntries = findViewById(R.id.myEntries);
        myEntries.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(ABCDE.this, ABCDESaveData.class);
                startActivity(intent1);
            }
        });


        Button savebutton = findViewById(R.id.savebutton);
        savebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if(
                        activating.getText().toString().matches(getResources().getString(R.string.activating_event))
                        || belief.getText().toString().matches(getResources().getString(R.string.belief_about_event_irrational))
                        || consequence.getText().toString().matches(getResources().getString(R.string.consequence_of_irrational_belief))
                        || dispute.getText().toString().matches(getResources().getString(R.string.dispute_my_irrational_belief))
                        || effect.getText().toString().matches(getResources().getString(R.string.effective_change_in_my_thinking))
                ){
                    Toast.makeText(ABCDE.this, ABCDE.this.getString(R.string.emptytoast), Toast.LENGTH_SHORT).show();
                }
                else{

                    adapter.add(String.valueOf(activating.getText()));
                    adapter.add(String.valueOf(belief.getText()));
                    adapter.add(String.valueOf(consequence.getText()));
                    adapter.add(String.valueOf(dispute.getText()));
                    adapter.add(String.valueOf(effect.getText()));
                    adapter.add("");
                    saveData();
                    activating.setText(R.string.activating_event);
                    belief.setText(R.string.belief_about_event_irrational);
                    consequence.setText(R.string.consequence_of_irrational_belief);
                    dispute.setText(R.string.dispute_my_irrational_belief);
                    effect.setText(R.string.effective_change_in_my_thinking);
                    Toast.makeText(ABCDE.this, ABCDE.this.getString(R.string.MyEntries), Toast.LENGTH_SHORT).show();

                }


            }
        });

        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);



    }


    private void saveData() {
        SharedPreferences sharedPreferences = getSharedPreferences("shared preferences", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(myArrayList);
        editor.putString("abcde list" , json);
        editor.apply();
    }
    private void loadData(){
        SharedPreferences sharedPreferences = getSharedPreferences("shared preferences", MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences.getString("abcde list", null);
        Type type = new TypeToken<ArrayList>() {}.getType();
        myArrayList = gson.fromJson(json, type);

        if (myArrayList == null) {
            myArrayList = new ArrayList<>();
        }

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

                AlertDialog.Builder builder = new AlertDialog.Builder(ABCDE.this);
                builder.setMessage(R.string.abcdeexplanation);
                final AlertDialog alert = builder.create();
                alert.show();
                System.out.println("THIS!!!!");



                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }



    @Override
    public void onBackPressed() {
        Intent intent = new Intent(ABCDE.this, Tools.class);
        startActivity(intent);
    }



}