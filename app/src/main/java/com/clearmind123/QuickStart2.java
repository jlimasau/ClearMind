package com.clearmind123;

import static kotlin.jvm.internal.Reflection.function;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.TooltipCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import android.app.FragmentTransaction;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.text.InputType;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Random;

public class QuickStart2 extends AppCompatActivity {

    private ViewPager2 viewPager2;
    private FragmentStateAdapter adapter1;
    private static int NUM_PAGES;

    private ArrayList titles;

    String currentTitle;

    int whichbutton;

    int i;

    String currentTab;

    ArrayList<String> myArrayList = new ArrayList<>();
    String newtitle;
    Integer position2;
    AdView mAdView;
    ArrayAdapter<String> adapter;

    int changeenterbtnaction=0;
    EditText text1;
    int currentpos;

    int scrollvar = 0;
    int q = 0;
    int tabpos;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quick_start2);




        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        TabLayout tabLayout = findViewById(R.id.tabs);
        viewPager2 = findViewById(R.id.view_pager);

        Button rollbutton = findViewById(R.id.button4);
        Button enterbutton = findViewById(R.id.button3);
        text1 = findViewById(R.id.inputtext);

        SharedPreferences sharedPreferences = getSharedPreferences("shared preferences", MODE_PRIVATE);

        Gson gson = new Gson();
        String json = sharedPreferences.getString("quickstartTitles", null);
        Type type = new TypeToken<ArrayList>() {}.getType();
        titles = gson.fromJson(json, type);


        if (titles == null) {
            titles = new ArrayList<>();
        }



        Boolean isFirstRun = getSharedPreferences("shared preferences", MODE_PRIVATE)
                .getBoolean("isFirstRun", true);

        SharedPreferences.Editor editor = sharedPreferences.edit();


        if (isFirstRun && titles.size()==0) {
            currentTitle = "Example";
            titles.add("Example");
            editor.putString("CT", currentTitle);
            editor.commit();
            saveTitle();
            // loadTitles();

            loadList();
            adapter.add(getResources().getString(R.string.example1));
            adapter.add(getResources().getString(R.string.example2));
            adapter.add(getResources().getString(R.string.example3));
            saveData();
            viewPager2.setAdapter(adapter1);
           // viewPager2.setCurrentItem(tab.getPosition(), false);


            getSharedPreferences("shared preferences", MODE_PRIVATE).edit()
                    .putBoolean("isFirstRun", false).commit();

        }




        NUM_PAGES = titles.size();


        adapter1 = new Adapter1(this, NUM_PAGES);
        viewPager2.setAdapter(adapter1);


        position2 = sharedPreferences.getInt("position1", 0);
        newtitle = sharedPreferences.getString("newtitle", null);


  /*      enterbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println("THISSSSSS");
            }
        });*/




        new TabLayoutMediator(tabLayout, viewPager2, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                tab.setText(titles.get(position).toString());
                //gets rid of popup tag
                TooltipCompat.setTooltipText(tab.view, null);

            }

        }).attach();



        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {



                if(tab.getPosition() == titles.size()){
                    System.out.println("THIS ONE!");

                    openDialog();

                }
                else{

                    SharedPreferences.Editor editor3 = sharedPreferences.edit();
                    editor3.putInt("whichbutton", tab.getPosition());

                    currentTitle = tab.getText().toString();
                    //set current title in sharedprefs
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("CT", currentTitle);
                    editor.apply();

                    editor3.apply();


                    viewPager2.setCurrentItem(tab.getPosition());
                    viewPager2.setTag(tab.getPosition());
                    //viewPager2.setAdapter(adapter1);

                }

                TooltipCompat.setTooltipText(tab.view, null);

                SharedPreferences sharedPreferences = getSharedPreferences("shared preferences", Context.MODE_PRIVATE);


                Integer inty = tab.getPosition();
                SharedPreferences.Editor editor = sharedPreferences.edit();





                currentTitle = tab.getText().toString();
                tabpos = tab.getPosition();



                text1 = findViewById(R.id.inputtext);
                Random rand = new Random();



                rollbutton.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View view) {
                        System.out.println("THIS!!!!!!!!!");


                        currentTitle = tab.getText().toString();
                        loadList();

                        System.out.println("MyArrayList: "+myArrayList);
                        int bound = myArrayList.size();
                        int y = 0;

                        for(int x = 0; x<bound; x++) {
                            if (adapter.getItem(x).contains("✘") == true || adapter.getItem(x).contains("✔")) {
                                y++;
                            }
                        }
                        if (y >= bound) {
                            Toast.makeText(QuickStart2.this, "You Finished!", Toast.LENGTH_LONG).show();
                            return;
                        }
                        int rand1 = rand.nextInt(bound);
                        String stringy = myArrayList.get(rand1);

                        if (adapter.getItem(rand1).contains("✘") == true || adapter.getItem(rand1).contains("✔")) {
                            rollbutton.performClick();
                        }
                        else {

                            for(int q = 0; q<myArrayList.size(); q++) {
                                if (adapter.getItem(q).contains("⬅") == true) {
                                    String newString2 = adapter.getItem(q).replace("⬅", "");
                                    adapter.remove(adapter.getItem(q));
                                    adapter.insert(newString2, q);
                                }
                            }


                            String text2 = adapter.getItem(rand1).toString() + "⬅";

                            adapter.remove(adapter.getItem(rand1));
                            adapter.insert(text2, rand1);
                            saveData();

                            viewPager2.setAdapter(adapter1);
                            viewPager2.setCurrentItem(tab.getPosition(), false);

                            Toast.makeText(QuickStart2.this, "Start with: " + stringy.replace("⬅", ""), Toast.LENGTH_LONG).show();

                        }
                    }
                });


            }
            @Override
            public void onTabUnselected(TabLayout.Tab tab) {


            }
            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                if(tab.getPosition() == titles.size()){
                    System.out.println("THIS ONE!");

                    openDialog();

                }
                else{

                    SharedPreferences.Editor editor3 = sharedPreferences.edit();
                    editor3.putInt("whichbutton", tab.getPosition());

                    currentTitle = tab.getText().toString();
                    //set current title in sharedprefs
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("CT", currentTitle);
                    editor.apply();

                    editor3.apply();


                    viewPager2.setCurrentItem(tab.getPosition());
                    viewPager2.setTag(tab.getPosition());
                    //viewPager2.setAdapter(adapter1);

                }
            }
        });


        enterbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                SharedPreferences.Editor editor = sharedPreferences.edit();

                loadList();
                changeenterbtnaction = sharedPreferences.getInt("btnaction1", 0);
                currentpos = sharedPreferences.getInt("currentpos1", 0);


                System.out.println("enterbuttonaction: " + changeenterbtnaction + " currentpos: "+  currentpos);
                ListView ls = (ListView) findViewById(android.R.id.list);

                //the 0 means the input should enter an item
                if(changeenterbtnaction ==0){

                    if (String.valueOf(text1.getText()).equals("")) {
                        Toast.makeText(QuickStart2.this, R.string.emptyinput_quickstart, Toast.LENGTH_LONG).show();

                    } else {
                        //  int positem2 = ls.getBottom();
                        System.out.println("THIS!!!!!!!!!");
                        System.out.println("arraywas: " +  myArrayList);

                        adapter.add(String.valueOf(text1.getText()));
                        text1.setText("");
                        saveData();

                        viewPager2.setAdapter(adapter1);
                        viewPager2.setCurrentItem(tabpos, false);


                        enterbutton.post(new Runnable() {
                            @Override
                            public void run() {
                                q = 1;
                                enterbutton.performClick();
                            }
                        });



                    }
                    //the 1 means the input should edit an item
                } else if (changeenterbtnaction == 1) {
                    //int positem = ls.getFirstVisiblePosition();


                    adapter.remove(adapter.getItem(currentpos));
                    saveData();
                    adapter.insert( String.valueOf(text1.getText()),currentpos);
                    text1.setText("");
                    saveData();
                    viewPager2.setAdapter(adapter1);
                    viewPager2.setCurrentItem(tabpos, false);
                    changeenterbtnaction = 0;
                    editor.putInt("btnaction1" , changeenterbtnaction);


                    // ls.setSelection(positem);

                }


                //SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putInt("btnaction1", 0);
                editor.putInt("currentpos1", 0);
                editor.apply();
                editor.commit();
                System.out.println("arraynow: " +  myArrayList);
                       /* Intent intent = new Intent(QuickStart2.this, QuickStart2.class);
                        startActivity(intent);*/

                if(q == 1) {


                    ls.setSelection(myArrayList.size());
                    q = 0;
                }
            }

        });





        TabLayout.Tab plusTab = tabLayout.newTab().setText("+");
        tabLayout.addTab(plusTab);

        LinearLayout tabStrip = (LinearLayout) tabLayout.getChildAt(0);
        //add a tag to each view then retrieve the tag to find the title
        for (i = 0; i < tabStrip.getChildCount(); i++) {
            tabStrip.getChildAt(i).setTag(i);
            if(i == tabStrip.getChildCount()-1){

            }
            else {

                // Set LongClick listener to each Tab
                tabStrip.getChildAt(i).setOnLongClickListener(new View.OnLongClickListener() {

                    @Override
                    public boolean onLongClick(View v) {
                        TooltipCompat.setTooltipText(v, null);
                        //System.out.println(v.getTag());
                        currentTab = tabLayout.getTabAt((int) v.getTag()).getText().toString();
                       Integer previousTab = (int) v.getTag()-1;
                        {
                            PopupMenu popupMenu = new PopupMenu(QuickStart2.this, v);
                            popupMenu.getMenu().add("Edit");
                            popupMenu.getMenu().add("Delete");

                            popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                                @Override
                                public boolean onMenuItemClick(MenuItem menuItem) {
                                    switch (menuItem.getTitle().toString()) {
                                        case "Edit":

                                            SharedPreferences.Editor editor = sharedPreferences.edit();
                                            editor.putString("currentTab", currentTab);
                                            //apply is the same as commit minus return value
                                            editor.apply();
                                            openDialog2();

                                            break;

                                        case "Delete":

                                            String btntext = String.valueOf(tabStrip.getChildAt(i));
                                            System.out.println(btntext);
                                            titles.remove(titles.indexOf(currentTab));
                                            saveTitle();


                                                currentTitle = (String) titles.get(titles.size() - 1);

                                            whichbutton = 0;
                                            SharedPreferences.Editor editor2 = sharedPreferences.edit();
                                            editor2.putString("CT", currentTitle);
                                            editor2.putInt("whichbutton", whichbutton);
                                            editor2.apply();

                                            tabLayout.getTabAt(previousTab).select();
                                           // tabLayout.getChildAt((int) v.getTag()-1).setSelected(true);
                                            Intent intent = getIntent();
                                            startActivity(intent);

                                            break;
                                    }

                                    return false;
                                }
                            });
                            popupMenu.show();
                            return false;

                        }

                    }
                });
            }


        }

        //set last tab editted as current tab
        whichbutton = sharedPreferences.getInt("whichbutton", 0);
        tabLayout.getTabAt(whichbutton).select();


        if (scrollvar == 1){
            ListView ls = (ListView) findViewById(android.R.id.list);

            ls.setSelection(myArrayList.size());
            scrollvar=0;
        }



        mAdView = findViewById(R.id.banner1);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);



    }


    private void openDialog2() {
        DialogFragment2 dialogFragment2 = new DialogFragment2();
        dialogFragment2.show(getSupportFragmentManager(), "example");
    }

    private void openDialog() {

        DialogFragment dialogFragment = new DialogFragment();
        dialogFragment.show(getSupportFragmentManager(), "example");

    }


    private void saveTitle() {
        SharedPreferences sharedPreferences = getSharedPreferences("shared preferences", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson3 = new Gson();
        String json3 = gson3.toJson(titles);
        editor.putString("quickstartTitles" , json3);
        //replace activities list with en es translation
        editor.apply();
    }
    public void saveData() {

        SharedPreferences sharedPreferences = getSharedPreferences("shared preferences", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(myArrayList);
        editor.putString(currentTitle , json);
        //replace activities list with en es translation
        editor.apply();

    }


    private void loadList() {
        SharedPreferences sharedPreferences = getSharedPreferences("shared preferences", MODE_PRIVATE);
        Gson gson5 = new Gson();
        String json5 = sharedPreferences.getString(currentTitle, null);
        Type type5 = new TypeToken<ArrayList>() {}.getType();
        myArrayList = gson5.fromJson(json5, type5);

        if (myArrayList == null) {
            myArrayList = new ArrayList<>();
        }


        adapter = new ArrayAdapter<String>(QuickStart2.this, android.R.layout.simple_list_item_1, myArrayList);

    }



    @Override
public boolean onCreateOptionsMenu(Menu menu) {
    MenuInflater menuInflater = getMenuInflater();
    menuInflater.inflate(R.menu.actionbar, menu);
    Menu optionsMenu = menu;

    MenuItem item = optionsMenu.findItem(R.id.help);
    item.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
        @Override
        public boolean onMenuItemClick(@NonNull MenuItem menuItem) {

            AlertDialog.Builder builder = new AlertDialog.Builder(QuickStart2.this);
            builder.setMessage(R.string.quickstart_text);
            final AlertDialog alert = builder.create();
            alert.show();


            return false;
        }
    });
    return super.onCreateOptionsMenu(menu);
}

    @Override
    public void onBackPressed() {

        Intent intent = new Intent(QuickStart2.this, Tools.class);
        startActivity(intent);

    }

    public void dataChanged() {
        saveData();

    }


}