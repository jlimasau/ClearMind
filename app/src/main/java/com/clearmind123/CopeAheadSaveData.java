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
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class CopeAheadSaveData extends AppCompatActivity {

    ArrayList<String> myArrayList = new ArrayList<>();
    ListView myListView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cope_ahead_save_data);

        loadData();

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(CopeAheadSaveData.this, android.R.layout.simple_list_item_1, myArrayList);
        myListView = (ListView) findViewById(R.id.copeaheadlist);
        myListView.setAdapter(adapter);



        myListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {

                adapter.remove(adapter.getItem(i));
                saveData();

                return false;
            }
        });
    }



    private void loadData() {
        SharedPreferences sharedPreferences = getSharedPreferences("shared preferences", MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences.getString("copeahead list", null);
        Type type = new TypeToken<ArrayList>() {}.getType();
        myArrayList = gson.fromJson(json, type);
        if (myArrayList == null) {
            myArrayList = new ArrayList<>();
        }
    }

    private void saveData() {
        SharedPreferences sharedPreferences = getSharedPreferences("shared preferences", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(myArrayList);
        //editor.putInt("incno", incrementingNumber);
        editor.putString("copeahead list" , json);
        //replace activities list with en es translation
        editor.apply();
    }


    @Override
    public void onBackPressed() {
        Intent intent = new Intent(CopeAheadSaveData.this, CopeAhead.class);
        startActivity(intent);
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

                AlertDialog.Builder builder = new AlertDialog.Builder(CopeAheadSaveData.this);
                builder.setMessage(R.string.CopeAheadSaveData_text);
                final AlertDialog alert = builder.create();
                alert.show();




                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }


}