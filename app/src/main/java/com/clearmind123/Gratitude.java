package com.clearmind123;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.PopupMenu;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class Gratitude extends AppCompatActivity {

    ListView myListView;
    ArrayList<String> myArrayList = new ArrayList<>();
    EditText text1;
    AdView mAdView;
    int changeenterbtnaction = 0;
    int currentpos;
    Menu optionsMenu;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gratitude);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        loadData();
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(Gratitude.this, android.R.layout.simple_list_item_1, myArrayList);
        myListView = (ListView) findViewById(R.id.listView);
        myListView.setAdapter(adapter);
        Button enterbutton = findViewById(R.id.enterbutton);
        text1 = findViewById(R.id.inputtext);


        enterbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //check if i should add     if (titles.size()==0) {
                //
                //                    System.out.println("IS EMPTY");
                //                    add2.performClick();
                //
                //                }

                if(changeenterbtnaction==0){
                    adapter.add(String.valueOf(text1.getText()));
                    text1.setText("");
                    saveData();
                    myListView.setSelection(myArrayList.size()-1);
                }

                else if (changeenterbtnaction == 1) {
                    adapter.remove(adapter.getItem(currentpos));
                    saveData();
                    adapter.insert( String.valueOf(text1.getText()),currentpos);
                    text1.setText("");
                    saveData();
                    myListView.setSelection(currentpos);
                    changeenterbtnaction = 0;

                }
            }
        });



        myListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


                String tempString = adapter.getItem(position);

                PopupMenu popupMenu = new PopupMenu(Gratitude.this, view);
                popupMenu.getMenu().add("Edit");
                popupMenu.getMenu().add("Delete");

                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        switch (menuItem.getTitle().toString()){
                            case "Edit" :

                                text1.setText(adapter.getItem(position).toString());
                                changeenterbtnaction = 1;
                                currentpos = position;

                                break;

                            case "Delete" :
                                System.out.println("delete");
                                adapter.remove(adapter.getItem(position));
                                saveData();
                                break;
                        }

                        return false;
                    }
                });
                popupMenu.show();
            }
        });

 /*       myListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                adapter.remove(adapter.getItem(position));
                saveData();
                return false;
            }


        });*/



        mAdView = findViewById(R.id.banner2);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);



    }

    private void saveData() {
        SharedPreferences sharedPreferences = getSharedPreferences("shared preferences", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(myArrayList);
        editor.putString("gratitude list" , json);
        //replace activities list with en es translation
        editor.apply();
    }
    private void loadData(){
        SharedPreferences sharedPreferences = getSharedPreferences("shared preferences", MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences.getString("gratitude list", null);
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
        optionsMenu = menu;
        //MenuItem item = optionsMenu.getItem(R.id.help);


        MenuItem item = optionsMenu.findItem(R.id.help);
        item.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(@NonNull MenuItem menuItem) {

                AlertDialog.Builder builder = new AlertDialog.Builder(Gratitude.this);
                builder.setMessage(R.string.gratitude_text);
                final AlertDialog alert = builder.create();
                alert.show();
              //  System.out.println("THIS!!!!");



                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }



}