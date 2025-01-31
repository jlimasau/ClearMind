package com.clearmind123;

import androidx.annotation.ColorInt;
import androidx.annotation.GravityInt;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatImageButton;
import androidx.appcompat.widget.ButtonBarLayout;

import android.app.backup.BackupManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.preference.PreferenceManager;
import android.text.InputType;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CheckedTextView;
import android.widget.EditText;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import java.util.Random;
//add ads
public class QuickStart extends AppCompatActivity {

    ListView myListView;
    ArrayList<String> myArrayList = new ArrayList<>();
    EditText text1;

    ArrayList<String> titles = new ArrayList<>();
    int numberofbuttons;
    String currentTitle;
    int i;
    ArrayAdapter<String> adapter;
    EditText input;
    AppCompatButton add1;

    int buttony;
    int whichbutton;
    AdView mAdView;
    Menu optionsMenu;
    long timeLastClicked;
    int changeenterbtnaction = 0;
    int currentpos;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quick_start);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //currentTitle = titles.get(0).toString();

       // loadData();
        loadTitles();

        //view binding
        adapter = new ArrayAdapter<String>(QuickStart.this, android.R.layout.simple_list_item_1, myArrayList);
        Random rand = new Random();
        myListView = (ListView) findViewById(R.id.listView);
        myListView.setAdapter(adapter);
        Button enterbutton = findViewById(R.id.button3);
        Button rollbutton = findViewById(R.id.button4);
        text1 = findViewById(R.id.inputtext);


        HorizontalScrollView scrollView = findViewById(R.id.scrollView);
        LinearLayout layout = (LinearLayout) findViewById(R.id.buttonlayout);
       // LinearLayout buttonLayout =  findViewById(R.id.buttonlayout);




    /*    Button firstBtn = findViewById(R.id.firstBtn);
        Button secondBtn = findViewById(R.id.secondBtn);
        Button thirdBtn = findViewById(R.id.button8);*/



        //this tells tools which title to go to
        //set it to a variable then have clicking title change it
        SharedPreferences sharedPreferences = this.getSharedPreferences("shared preferences", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        //editor.putString("whichlist", "1st");
        editor.apply();


        //firstBtn.setBackgroundColor(R.color.purple_500);



 /*       firstBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), QuickStart.class);
                startActivity(intent);
            }
        }
        );

        secondBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), QuickStart2.class);
                startActivity(intent);
            }
        });

        thirdBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), QuickStart3.class);
                startActivity(intent);
            }
        });
*/



/*
        SharedPreferences sharedpref = PreferenceManager.getDefaultSharedPreferences(this);
        boolean isFirstRun = sharedpref.getBoolean("first1", true);
        //SharedPreferences.Editor editor = sharedpref.edit();

        if (isFirstRun == true) {
            //titles.add("example");
            editor.putBoolean("first", false);
            editor.apply();

        }*/

         /*   currentTitle = "Example";

                titles.add("example");
                // saveTitle();
            adapter.add(getResources().getString(R.string.example1));
            adapter.add(getResources().getString(R.string.example2));
            adapter.add(getResources().getString(R.string.example3));
            saveData();
                editor.putBoolean("first1", false);
                editor.apply();

                //numberofbuttons = numberofbuttons+1;
            }*/


           /* Button example = new Button(this);
            example.setText("Example");
            example.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    currentTitle = example.getText().toString();
                    adapter.clear();
                    loadList();

                }
            });

            adapter.add(getResources().getString(R.string.example1));
            adapter.add(getResources().getString(R.string.example2));
            adapter.add(getResources().getString(R.string.example3));
            saveData();
            Toast.makeText(this, "Here are some example activities to get you started", Toast.LENGTH_LONG).show();

            editor.putBoolean("first", false);
            editor.apply();
        }*/







       // int listSize = myArrayList.size();
      //  List<Boolean> checklist = new ArrayList<Boolean>(Arrays.asList(new Boolean[listSize]));
   /*     System.out.println("list size is: " + listSize);
        for( int x = 0; x < listSize; x++){
            if (  checklist.get(x) == true){


            }
        }*/


  /*     myListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                long currentTime = System.currentTimeMillis();
                if (currentTime - timeLastClicked < ViewConfiguration.getDoubleTapTimeout()) {
                    onItemDoubleClick(parent, view, position, id);
                } else if (currentTime - timeLastClicked > ViewConfiguration.getDoubleTapTimeout()) {






                    String tempString = adapter.getItem(position);
                    if (tempString.contains("✘") == true || tempString.contains("✔")) {
                        String newString2 = tempString.replace("✘", "");
                        String newString1 = newString2.replace("✔", "");

                        adapter.remove(adapter.getItem(position));
                        adapter.insert(newString1, position);
                    } else {
                        String newString = "✔" + tempString;
                        adapter.remove(adapter.getItem(position));
                        adapter.insert(newString, position);
                    }
                    saveData();



                }
                timeLastClicked = currentTime;

            }
//✔
           private void onItemDoubleClick(AdapterView<?> parent, View view, int position, long id) {



               String tempString = adapter.getItem(position);
               if (tempString.contains("✔") == true) {
                   String newString1 = tempString.replace("✔", "✘");
                   adapter.remove(adapter.getItem(position));
                   adapter.insert(newString1, position);
               } else if (tempString.contains("✘") == true) {

                   String newString1 = tempString.replace("✘", "");
                   adapter.remove(adapter.getItem(position));
                   adapter.insert(newString1, position);
               } else {
                   String newString = "✘" + tempString;
                   adapter.remove(adapter.getItem(position));
                   adapter.insert(newString, position);
               }
               saveData();
           System.out.println("DOUBLEEEE");
            }



       });*/







        //submit button should clear message, add to list,
        enterbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(changeenterbtnaction ==0){

                if (titles.size()==0) {

                    System.out.println("IS EMPTY");
                    add1.performClick();

                }
                else{




                    if(String.valueOf(text1.getText()).equals("")){
                        Toast.makeText(QuickStart.this, R.string.emptyinput_quickstart, Toast.LENGTH_LONG).show();
                    }
                    else {

                        adapter.add(String.valueOf(text1.getText()));

                        text1.setText("");
                        saveData();
                        myListView.setSelection(myArrayList.size() - 1);
                        //System.out.println("hello world");
                        //
                        //text1.getText().clear();
                    }
            }
        } else if (changeenterbtnaction == 1) {
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

        rollbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (titles.size()==0) {

                    System.out.println("IS EMPTY");
                    add1.performClick();

                }
                else{
                    int bound = myArrayList.size();
                    int y = 0;

                    for(int x = 0; x<bound; x++) {
                        if (adapter.getItem(x).contains("✘") == true || adapter.getItem(x).contains("✔")) {
                        y++;
                        }
                    }
                    if (y >= bound) {
                        Toast.makeText(QuickStart.this, "You Finished!", Toast.LENGTH_LONG).show();
                        return;
                    }
                int rand1 = rand.nextInt(bound);
                String stringy = myArrayList.get(rand1);

                if (adapter.getItem(rand1).contains("✘") == true || adapter.getItem(rand1).contains("✔")) {
                    rollbutton.performClick();
                }
                else {
                    //View view1 = myListView.getChildAt(rand1);
                  //myListView.setSelector(R.color.black);
                   //myListView.getChildAt(rand1).setActivated(true);





                    for(int q = 0; q<myArrayList.size(); q++) {
                        if (adapter.getItem(q).contains("⬅") == true) {
                            String newString2 = adapter.getItem(q).replace("⬅", "");
                            adapter.remove(adapter.getItem(q));
                            adapter.insert(newString2, q);
                        }
                    }




                    String text2 = myListView.getItemAtPosition(rand1).toString() + "⬅";

                    //text2.toUpperCase();
                    adapter.remove(adapter.getItem(rand1));
                    adapter.insert(text2, rand1);
                    saveData();




                    Toast.makeText(QuickStart.this, "Start with: " + stringy.replace("⬅", ""), Toast.LENGTH_LONG).show();
                    //myListView.setBackgroundColor(getResources().getColor(R.color.translucent));


                }
                }
                //return;
/*                    for (int x = 0; x < bound; x++) {
                        int z = 0;
                        if (adapter.getItem(x).contains("√") == true) {
                            if (z == bound) {
                                System.out.println("x is: " + x);
                                Toast.makeText(QuickStart.this, "You Finished!", Toast.LENGTH_LONG).show();

                                return;
                            }
                            z++;

                        }
                        int rand1 = rand.nextInt(bound);

                            String stringy = myArrayList.get(rand1);

                            Toast.makeText(QuickStart.this, "Start with: " + stringy, Toast.LENGTH_LONG).show();




                        }
                    }*/

                /*if(y == 1){
                    return;
                }
                else if(y == 0){
                    if (adapter.isEmpty()) {
                        return;
                    } else {


                        String tempString = adapter.getItem(rand1);
                        if (tempString.contains("√") == true) {
                            rollbutton.performClick();
                        } else {
                            // System.out.println(rand1);
                                         //if rand == checked then reroll
                            //if (myArrayList.isChecked() )

                        }


                        // a for loop that goes through and checks every row for check if every row has a check then return
                    }
                }*/
            }
        });







        myListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


                String tempString = adapter.getItem(position);

                PopupMenu popupMenu = new PopupMenu(QuickStart.this, view);
                popupMenu.getMenu().add("✔");
                popupMenu.getMenu().add("✘");
                popupMenu.getMenu().add("Edit");
                popupMenu.getMenu().add("Delete");

                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        switch (menuItem.getTitle().toString()){
                            case "✔" :
                                System.out.println("check");
                                if (tempString.contains("✘") == true || tempString.contains("✔")) {
                                    String newString3 = tempString.replace("⬅", "");
                                    String newString2 = newString3.replace("✘", "");
                                    String newString1 = newString2.replace("✔", "");

                                    adapter.remove(adapter.getItem(position));
                                    adapter.insert(newString1, position);
                                } else {
                                    String newString2 = tempString.replace("⬅", "");
                                    String newString = "✔" + newString2;
                                    adapter.remove(adapter.getItem(position));
                                    adapter.insert(newString, position);
                                }
                                saveData();
                                break;
                            case "✘" :
                                System.out.println("X");
                                //String tempString = adapter.getItem(position);
                                if (tempString.contains("✔") == true) {
                                    String newString2 = tempString.replace("⬅", "");
                                    String newString1 = newString2.replace("✔", "✘");
                                    adapter.remove(adapter.getItem(position));
                                    adapter.insert(newString1, position);
                                } else if (tempString.contains("✘") == true) {
                                    String newString2 = tempString.replace("⬅", "");

                                    String newString1 = newString2.replace("✘", "");
                                    adapter.remove(adapter.getItem(position));
                                    adapter.insert(newString1, position);
                                } else {
                                    String newString2 = tempString.replace("⬅", "");
                                    String newString = "✘" + newString2;
                                    adapter.remove(adapter.getItem(position));
                                    adapter.insert(newString, position);
                                }
                                saveData();
                                break;
                                case "Edit" :

                                    text1.setText(adapter.getItem(position).replace("✔", "").replace("✘", "").replace("⬅", "").toString());
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








        //delete function on slide?
        //

        Boolean isFirstRun = getSharedPreferences("shared preferences", MODE_PRIVATE)
                .getBoolean("isFirstRun", true);



        if (isFirstRun && titles.size()==0) {
            currentTitle = "example";
            titles.add("example");
            editor.putString("CT", currentTitle);
            editor.apply();
            saveTitle();
            loadTitles();
            adapter.add(getResources().getString(R.string.example1));
            adapter.add(getResources().getString(R.string.example2));
            adapter.add(getResources().getString(R.string.example3));
            saveData();
            getSharedPreferences("shared preferences", MODE_PRIVATE).edit()
                    .putBoolean("isFirstRun", false).commit();

        }
        else if (titles.size()==0) {

            System.out.println("IS EMPTY");
            add1.performClick();

        }



        loadList();

        buttony = sharedPreferences.getInt("buttony",buttony);



        scrollView.post(new Runnable() {
            @Override
            public void run() {
                scrollView.scrollBy(buttony,0);

            }
        });


                //setBackgroundColor(R.color.purple_500);





        myListView.clearChoices();





    /*    whichbutton = sharedPreferences.getInt("whichbutton", titles.size());
        System.out.println(whichbutton);
        if(layout.getChildAt(0)!=null) {
            layout.getChildAt(whichbutton).setBackgroundColor(R.color.purple_500);

        }*/


        //saves sharedpref to backup
        BackupManager.dataChanged("com.clearmind123");


        mAdView = findViewById(R.id.banner1);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

    }



    private void loadTitles() {
        HorizontalScrollView scrollView = findViewById(R.id.scrollView);

        SharedPreferences sharedPreferences = getSharedPreferences("shared preferences", MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences.getString("quickstartTitles", null);
        Type type = new TypeToken<ArrayList>() {}.getType();
        titles = gson.fromJson(json, type);



        if (titles == null) {
            titles = new ArrayList<>();
        }

       // SharedPreferences sharedPreferences = getSharedPreferences("shared preferences", MODE_PRIVATE);


        LinearLayout layout = (LinearLayout) findViewById(R.id.buttonlayout);
        layout.removeAllViewsInLayout();
        layout.setBackgroundColor(Color.WHITE);

       /*
*/


        //this is the line of code that was causing the error
        currentTitle = sharedPreferences.getString("CT", null);
        if (currentTitle == null){
            if(titles.size()==0){
                //do nothing
            }
            else {
                currentTitle = titles.get(titles.size()).toString();
            }
        }




        //add padding to each button
        //keyboard should open automatically when adding









        numberofbuttons =titles.size();
        Button[] buttons = new Button[numberofbuttons+1];
/*        for (int j =0; j< buttons.length-1; j++){


            layout.getChildAt(i).setBackgroundColor(R.color.purple_500);
        }*/


        for (i = 0; i<numberofbuttons; i++){
            AppCompatButton button = new AppCompatButton(this);
            button.setText(String.valueOf(titles.get(i)));
            //get value and set current title equal to sharedpref value, if no value select set it to the last title

            //button.setBackground(getDrawable(R.color.white));

            button.setBackgroundColor(0);
            button.setTextColor(R.color.black);
          /*  if (currentTitle.equals(null)) {


            }*/
            //currentTitle = String.valueOf(titles.get(i));
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    currentTitle = button.getText().toString();
                    //set current title in sharedprefs
                   // SharedPreferences sharedPreferences = this.getSharedPreferences("shared preferences", MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("CT", currentTitle);
                    editor.apply();

                    adapter.clear();
                    loadList();

                    ///check this
                    buttony = button.getLeft();
                    System.out.println(buttony);
                    editor.putInt("buttony", buttony);
                    editor.commit();

                    for( int k=0; k<titles.size(); k++) {
                        layout.getChildAt(k).setBackgroundColor(R.color.black);
                    }


                    whichbutton = titles.indexOf(String.valueOf(button.getText()));
                    editor.putInt("whichbutton", whichbutton);
                    editor.commit();
                    //int numberofbuttons = layout.getChildCount();
                    layout.getChildAt(whichbutton).setBackgroundColor(0);
                    //loadTitles();

                   // System.out.println("number of buttons: " + numberofbuttons);
                }
            });


            button.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {


                    PopupMenu popupMenu = new PopupMenu(QuickStart.this, view);
                    popupMenu.getMenu().add("Edit");
                    popupMenu.getMenu().add("Delete");

                    popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                        @Override
                        public boolean onMenuItemClick(MenuItem menuItem) {
                            switch (menuItem.getTitle().toString()){
                                case "Edit" :

                                    input = new EditText(QuickStart.this);
                                    input.setMaxLines(1);
                                    input.setInputType(InputType.TYPE_CLASS_TEXT);
                                    input.setText(button.getText().toString());

                                    AlertDialog.Builder builder = new AlertDialog.Builder(QuickStart.this);
                                    builder.setMessage(R.string.setTitle);
                                    builder.setCancelable(true)
                                            .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                                public void onClick(DialogInterface dialog, int id) {




                                                        // whichbutton = titles.indexOf(String.valueOf(button.getText())+1);

                                                        //titles.get(titles.indexOf(String.valueOf(button.getText()))).replace(String.valueOf(button.getText()),String.valueOf(input.getText()));
                                                        titles.set(titles.indexOf(String.valueOf(button.getText())), String.valueOf(input.getText()));
                                                        System.out.println(titles);
                                                        currentTitle = String.valueOf(input.getText());
                                                        saveData();
                                                        whichbutton = titles.indexOf(currentTitle);


                                                        SharedPreferences.Editor editor = sharedPreferences.edit();
                                                        editor.putString("CT", currentTitle);
                                                        editor.apply();
                                                        saveTitle();
                                                        System.out.println("WHICH BUTTON:" + whichbutton);
                                                        editor.putInt("whichbutton", whichbutton);
                                                        editor.commit();
                                                        Intent intent = getIntent();
                                                        startActivity(intent);
                                                        //scroll to current title here


                                                }
                                            });
                                    final AlertDialog alert = builder.create();
                                    alert.setView(input);
                                    alert.show();
                                    alert.getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(R.color.black);




                                    //get title.string
                                    //open dialog
                                    //set input text equal to titlestring



                              /*      text1.setText(adapter2.getItem(position).toString());
                                    changeenterbtnaction = 1;
                                    currentpos = position;
                                    cons.setText("Enter");
                                    pros.setText("Pro");
                                    cons.setEnabled(true);
                                    pros.setEnabled(false);*/

                                    break;

                                case "Delete" :

                                    String btntext = String.valueOf(button.getText());
                                    System.out.println(btntext);
                                    titles.remove(titles.indexOf(btntext));
                                    saveTitle();

                                    if(titles.size()==0){
                                        currentTitle= null;
                                    }
                                    else {
                                        currentTitle = titles.get(titles.size() - 1);
                                    }
                                    whichbutton = titles.size()-1;
                                    SharedPreferences.Editor editor = sharedPreferences.edit();
                                    editor.putString("CT", currentTitle);
                                    editor.putInt("whichbutton", whichbutton);
                                    editor.apply();

                                    Intent intent = getIntent();
                                    startActivity(intent);

                                  /*  System.out.println("delete");
                                    adapter2.remove(adapter2.getItem(position));
                                    saveData();*/
                                    break;
                            }

                            return false;
                        }
                    });
                    popupMenu.show();




                   /* String btntext = String.valueOf(button.getText());
                    System.out.println(btntext);
                    titles.remove(titles.indexOf(btntext));
                    saveTitle();
                    //currentTitle = String.valueOf(titles.get(i));
                    //current title sharedpref set
                    if(titles.size()==0){
                        currentTitle= null;
                    }
                    else {
                        currentTitle = titles.get(titles.size() - 1);
                    }
                    whichbutton = titles.size()-1;
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("CT", currentTitle);
                    editor.putInt("whichbutton", whichbutton);
                    editor.apply();
                    Intent intent = getIntent();
                    startActivity(intent);*/
                    return false;

                    //currentlist should be set to last list
                }
            });
            buttons[i] = button;
        }

















        add1 = new AppCompatButton(this);
        add1.setText("+");
        add1.setBackgroundColor(R.color.black);
        add1.setTextColor(R.color.black);
        add1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                        input = new EditText(QuickStart.this);
                        input.setMaxLines(1);
                input.setInputType(InputType.TYPE_CLASS_TEXT);
                        AlertDialog.Builder builder = new AlertDialog.Builder(QuickStart.this);
                        builder.setMessage(R.string.setTitle);
                        builder.setCancelable(true)
                                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        if(String.valueOf(input.getText()).equals("")){
                                            Toast.makeText(QuickStart.this, R.string.emptytitle, Toast.LENGTH_LONG).show();
                                            Intent intent = getIntent();
                                            startActivity(intent);
                                        }
                                        else {

                                            titles.add(String.valueOf(input.getText()));
                                            System.out.println(titles);


                                            currentTitle = String.valueOf(input.getText());
                                            SharedPreferences.Editor editor = sharedPreferences.edit();
                                            editor.putString("CT", currentTitle);
                                            editor.apply();


                                            saveTitle();
                                            // SharedPreferences.Editor editor = sharedPreferences.edit();
                                            whichbutton = titles.size() - 1;
                                            editor.putInt("whichbutton", whichbutton);
                                            editor.commit();


                                            buttony = add1.getLeft();
                                            editor.putInt("buttony", buttony);
                                            editor.commit();
                                            Intent intent = getIntent();
                                            startActivity(intent);
                                            //loadTitles();

                                            //save titles to sharedpref

                                        }
                                        //grab input and save it to title list
                                    }
                                });







                        final AlertDialog alert = builder.create();


                        //alert.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
                        alert.setView(input);
                       // alert.getWindow().setSoftInputMode();

                //alert.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);

                        alert.show();

                        alert.getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(R.color.black);




                    //opens the keyboard
                new Handler().postDelayed(new Runnable() {

                    public void run() {
                        input.dispatchTouchEvent(MotionEvent.obtain(SystemClock.uptimeMillis(), SystemClock.uptimeMillis(), MotionEvent.ACTION_DOWN, 0f, 0f, 0));
                        input.dispatchTouchEvent(MotionEvent.obtain(SystemClock.uptimeMillis(), SystemClock.uptimeMillis(), MotionEvent.ACTION_UP, 0f, 0f, 0));
                    }
                }, 200);






                    adapter.clear();

                        loadTitles();




            }

        });
        buttons[numberofbuttons] = add1;



        for(int j= 0; j <numberofbuttons+1; j++){
            layout.addView(buttons[j]);
        }


        if (titles.size()==0){

        }
        else{


            for( int k=0; k<titles.size(); k++) {
                layout.getChildAt(k).setBackgroundColor(R.color.black);
            }
            whichbutton = sharedPreferences.getInt("whichbutton", titles.size()-1);
            if(layout.getChildAt(whichbutton)==null){
                System.out.println(whichbutton);

            }
            else{


            layout.getChildAt(whichbutton).setBackgroundColor(0);
            System.out.println("THIS" + whichbutton);
            }
            //test for potential bug when erasing title
        }







      /*      AlertDialog.Builder builder = new AlertDialog.Builder(QuickStart.this);
            builder.setMessage(R.string.setTitle);
            builder.setCancelable(false)
                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            titles.add(String.valueOf(input.getText()));
                            System.out.println(titles);
                            saveTitle();
                            loadTitles();
                        }
                    });
            final AlertDialog alert = builder.create();
            alert.setView(input);
            alert.show();

            adapter.clear();
            loadTitles();*/




           /* input = new EditText(QuickStart.this);
            input.setMaxLines(1);
            input.setInputType(InputType.TYPE_CLASS_TEXT);
            AlertDialog.Builder builder = new AlertDialog.Builder(QuickStart.this);
            builder.setMessage(R.string.setTitle);
            builder.setCancelable(false)
                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            titles.add(String.valueOf(input.getText()));
                            System.out.println(titles);
                            saveTitle();
                            loadTitles();

                            scrollView.post(new Runnable() {
                                @Override
                                public void run() {
                                    scrollView.fullScroll(View.FOCUS_RIGHT);

                                }
                            });

                        }
                    });


            final AlertDialog alert = builder.create();
            alert.setView(input);
            alert.show();

            adapter.clear();
            loadTitles();*/


        //currentTitle = String.valueOf(titles.get(titles.size()-1));


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

        adapter = new ArrayAdapter<String>(QuickStart.this, android.R.layout.simple_list_item_1, myArrayList);
        myListView.setAdapter(adapter);
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



    private void saveData() {
        SharedPreferences sharedPreferences = getSharedPreferences("shared preferences", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(myArrayList);
        editor.putString(currentTitle , json);
        //replace activities list with en es translation
        editor.apply();

    }
    private void loadData(){
        SharedPreferences sharedPreferences = getSharedPreferences("shared preferences", MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences.getString("activities list", null);
        Type type = new TypeToken<ArrayList>() {}.getType();
        myArrayList = gson.fromJson(json, type);

        if (myArrayList == null) {
            myArrayList = new ArrayList<>();
        }

    }


    @Override
    public void onBackPressed() {

        Intent intent = new Intent(this, Tools.class);

        startActivity(intent);
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

                AlertDialog.Builder builder = new AlertDialog.Builder(QuickStart.this);
                builder.setMessage(R.string.quickstart_text);
                final AlertDialog alert = builder.create();
                alert.show();
                System.out.println("THIS!!!!");



                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }



}

