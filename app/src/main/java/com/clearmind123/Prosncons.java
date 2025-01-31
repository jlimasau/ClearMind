package com.clearmind123;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.text.InputType;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.Toast;
import android.widget.Toolbar;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;

public class Prosncons extends AppCompatActivity {

    ArrayList<String> myArrayList = new ArrayList<>();
    ListView myListView;
    ArrayList<String> myArrayList2 = new ArrayList<>();
    ListView myListView2;
    EditText text1;

   // String title = "title";

    ArrayList<String> titles = new ArrayList<>();
    int numberofbuttons;
    String currentTitle;
    int i;
    EditText input = null;
    ArrayAdapter<String> adapter;
    ArrayAdapter<String> adapter2;

    int whichbutton;
    AppCompatButton add2;

    int buttony2;

    Menu optionsMenu;
    AdView mAdView;

    int changeenterbtnaction = 0;
    int currentpos;
    //HorizontalScrollView scrollView;
    //




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prosncons);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        mAdView = findViewById(R.id.banner_ad_view);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);;


        //sharepref load tittle array
        //loadData3();
        loadTitles();

//get rid of save button then add new button after list instead

      //  loadBoth();



        //loadData();
        //loadData2();



        HorizontalScrollView scrollView = findViewById(R.id.scrollView);


        Button pros = findViewById(R.id.pros);
        Button cons = findViewById(R.id.cons);



     /*   final int helpid = getResources().getIdentifier("actionbar" , "help","android");
        findViewById(helpid).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println("THIS!!!");
            }
        });*/
        //Button save = findViewById(R.id.save);
        //Button entries = findViewById(R.id.entries);

         adapter = new ArrayAdapter<String>(Prosncons.this, android.R.layout.simple_list_item_1, myArrayList);
         adapter2 = new ArrayAdapter<String>(Prosncons.this, android.R.layout.simple_list_item_1, myArrayList2);

        myListView = (ListView) findViewById(R.id.pro);
        myListView.setAdapter(adapter);
        text1 = findViewById(R.id.inputtext);

        myListView2 = (ListView) findViewById(R.id.con);
        myListView2.setAdapter(adapter2);







        pros.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (titles.size()==0) {

                    System.out.println("IS EMPTY");
                    add2.performClick();

                }
                else {





                    if(changeenterbtnaction==0){


                        if(String.valueOf(text1.getText()).equals("")){
                            Toast.makeText(Prosncons.this, R.string.emptypro, Toast.LENGTH_LONG).show();
                        }
                        else {
                            adapter.add(String.valueOf(text1.getText()));
                            text1.setText("");
                            //saveData();
                            saveBoth();
                            myListView.setSelection(myArrayList.size() - 1);
                        }

                    }
                    else if (changeenterbtnaction == 1) {


                        adapter.remove(adapter.getItem(currentpos));
                        saveData();
                        adapter.insert( String.valueOf(text1.getText()),currentpos);
                        text1.setText("");
                        saveBoth();
                        myListView.setSelection(currentpos);
                        changeenterbtnaction = 0;
                        pros.setText("Pro");
                        cons.setEnabled(true);

                    }





                }
            }
        });


        cons.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (titles.size()==0) {

                    System.out.println("IS EMPTY");
                    add2.performClick();

                }
                else {


                    if(changeenterbtnaction==0){


                        if(String.valueOf(text1.getText()).equals("")){
                            Toast.makeText(Prosncons.this, R.string.emptycon, Toast.LENGTH_LONG).show();

                        }
                        else {

                            adapter2.add(String.valueOf(text1.getText()));
                            text1.setText("");
                            //saveData();
                            saveBoth();
                            myListView2.setSelection(myArrayList2.size() - 1);
                        }

                    }
                    else if (changeenterbtnaction == 1) {


                        adapter2.remove(adapter2.getItem(currentpos));
                        saveData();
                        adapter2.insert( String.valueOf(text1.getText()),currentpos);
                        text1.setText("");
                        saveBoth();
                        myListView2.setSelection(currentpos);
                        changeenterbtnaction = 0;
                        cons.setText("Con");
                        pros.setEnabled(true);

                    }


                }
            }


        });

  /*      entries.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(view.getContext(), ProsnconsEntries.class);
                startActivity(intent);


            }
        });*/

    /*    save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //popup prompt for title
                input = new EditText(Prosncons.this);
                AlertDialog.Builder builder = new AlertDialog.Builder(Prosncons.this);
                builder.setMessage(R.string.setTitle);
                builder.setCancelable(false)
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                titles.add(String.valueOf(input.getText()));
                                System.out.println(titles);
                                saveTitle();
                                loadTitles();



                                //save titles to sharedpref


                                //grab input and save it to title list
                            }
                        });


                final AlertDialog alert = builder.create();
                alert.setView(input);
                alert.show();


               adapter.clear();
               adapter2.clear();
                //saveBoth();
                //loadData();
               // loadData2();
                loadTitles();






                //clear pro
                //clear con


                //save pro list
                //save cons list
               // save them to a title

                //should reload titles after saving




            }
        });*/


        if(titles.isEmpty()){

           // add2.performClick();
        }




        myListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


                String tempString = adapter.getItem(position);

                PopupMenu popupMenu = new PopupMenu(Prosncons.this, view);
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
                                pros.setText("Enter");
                                cons.setText("Con");
                                pros.setEnabled(true);
                                cons.setEnabled(false);

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


        myListView2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


                String tempString = adapter2.getItem(position);

                PopupMenu popupMenu = new PopupMenu(Prosncons.this, view);
                popupMenu.getMenu().add("Edit");
                popupMenu.getMenu().add("Delete");

                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        switch (menuItem.getTitle().toString()){
                            case "Edit" :

                                text1.setText(adapter2.getItem(position).toString());
                                changeenterbtnaction = 1;
                                currentpos = position;
                                cons.setText("Enter");
                                pros.setText("Pro");
                                cons.setEnabled(true);
                                pros.setEnabled(false);

                                break;

                            case "Delete" :
                                System.out.println("delete");
                                adapter2.remove(adapter2.getItem(position));
                                saveData();
                                break;
                        }

                        return false;
                    }
                });
                popupMenu.show();
            }
        });




    /*    myListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                adapter.remove(adapter.getItem(position));
                //saveData();
                saveBoth();
                return false;
            }
        });


        myListView2.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                adapter2.remove(adapter2.getItem(position));
               // saveData2();
                saveBoth();
                return false;
            }
        });*/


        Boolean isFirstRun = getSharedPreferences("shared preferences", MODE_PRIVATE).getBoolean("isFirstRun1", true);
        SharedPreferences sharedPreferences = this.getSharedPreferences("shared preferences", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        if(isFirstRun && titles.size()==0) {
            currentTitle = "example";
            titles.add("example");
            editor.putString("CT1", currentTitle);
            editor.commit();
            saveTitle();

            loadTitles();

            adapter.add(getResources().getString(R.string.proexample1));
            adapter.add(getResources().getString(R.string.proexample2));
            adapter.add(getResources().getString(R.string.proexample3));
            adapter2.add(getResources().getString(R.string.conexample1));

            saveBoth();
            getSharedPreferences("shared preferences", MODE_PRIVATE).edit().putBoolean("isFirstRun1", false).commit();

        }


        else if (titles.size()==0) {

            System.out.println("IS EMPTY");
            add2.performClick();

        }



        loadBoth();

        //SharedPreferences sharedPreferences = this.getSharedPreferences("shared preferences", MODE_PRIVATE);


        buttony2 = sharedPreferences.getInt("buttony2",500);



        scrollView.post(new Runnable() {
            @Override
            public void run() {
                scrollView.scrollBy(buttony2,0);

            }
        });



    /*    scrollView.post(new Runnable() {
            @Override
            public void run() {
                scrollView.fullScroll(View.FOCUS_RIGHT);

            }
        });*/





    }

    private void loadTitles() {
        HorizontalScrollView scrollView = findViewById(R.id.scrollView);

        SharedPreferences sharedPreferences = getSharedPreferences("shared preferences", MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences.getString("titles", null);
        Type type = new TypeToken<ArrayList>() {}.getType();
        titles = gson.fromJson(json, type);

        if (titles == null) {
            titles = new ArrayList<>();
        }

        LinearLayout layout = (LinearLayout) findViewById(R.id.buttonlayout);
        layout.removeAllViewsInLayout();
        layout.setBackgroundColor(Color.WHITE);



        currentTitle = sharedPreferences.getString("CT1", null);
        if (currentTitle == null){
            if(titles.size()==0){
                //do nothing
            }
            else {
                currentTitle = titles.get(titles.size()).toString();
            }
        }

        System.out.println("current title is: " + currentTitle);


        numberofbuttons =titles.size();
        Button[] buttons = new Button[numberofbuttons+1];
        for (i = 0; i<numberofbuttons; i++){
            Button button = new Button(this);
            button.setText(String.valueOf(titles.get(i)));
           // currentTitle = String.valueOf(titles.get(i));
           // String title1 = titles.get(i);

            button.setBackgroundColor(0);
            button.setTextColor(R.color.black);
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    currentTitle = button.getText().toString();

                    //SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("CT1", currentTitle);
                    buttony2 = button.getLeft();
                    System.out.println(buttony2);
                    editor.putInt("buttony2", buttony2);
                    editor.commit();
                    editor.apply();


                    //clear list
                    adapter.clear();
                    adapter2.clear();
                    //loadTitles();
                    loadBoth();


                    for( int k=0; k<titles.size(); k++) {
                        layout.getChildAt(k).setBackgroundColor(R.color.black);
                    }


                    whichbutton = titles.indexOf(String.valueOf(button.getText()));
                    editor.putInt("whichbutton2", whichbutton);
                    editor.commit();
                    //int numberofbuttons = layout.getChildCount();
                    layout.getChildAt(whichbutton).setBackgroundColor(0);

                    //System.out.println(currentTitle);



                    //currentTitle = String.valueOf(titles.get(i));
                    //load display pros and cons
                    //load pros
      /*              SharedPreferences sharedPreferences = getSharedPreferences("shared preferences", MODE_PRIVATE);
                    Gson gson5 = new Gson();
                    String json5 = sharedPreferences.getString(title1 + "pro", null);
                    Type type5 = new TypeToken<ArrayList>() {}.getType();
                    myArrayList = gson5.fromJson(json5, type5);

                    if (myArrayList == null) {
                        myArrayList = new ArrayList<>();
                    }


                    Gson gson4 = new Gson();
                    String json4 = sharedPreferences.getString(title1 + "con", null);
                    Type type4 = new TypeToken<ArrayList>() {}.getType();
                    myArrayList2 = gson4.fromJson(json4, type4);

                    if (myArrayList2 == null) {
                        myArrayList2 = new ArrayList<>();
                    }*/


                    //System.out.println(title1);

                    //load cons
                }
            });
            button.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {

                    PopupMenu popupMenu = new PopupMenu(Prosncons.this, view);
                    popupMenu.getMenu().add("Edit");
                    popupMenu.getMenu().add("Delete");

                    popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                        @Override
                        public boolean onMenuItemClick(MenuItem menuItem) {
                            switch (menuItem.getTitle().toString()){
                                case "Edit" :

                                    input = new EditText(Prosncons.this);
                                    input.setMaxLines(1);
                                    input.setInputType(InputType.TYPE_CLASS_TEXT);
                                    input.setText(button.getText().toString());
                                    AlertDialog.Builder builder = new AlertDialog.Builder(Prosncons.this);
                                    builder.setMessage(R.string.setTitle);
                                    builder.setCancelable(true)
                                            .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                                public void onClick(DialogInterface dialog, int id) {
                                                    titles.set(titles.indexOf(String.valueOf(button.getText())), String.valueOf(input.getText()));
                                                    System.out.println(titles);

                                                    currentTitle = String.valueOf(input.getText());
                                                    SharedPreferences.Editor editor = sharedPreferences.edit();
                                                    editor.putString("CT1", currentTitle);
                                                    editor.apply();
                                                    saveTitle();
                                                    whichbutton = titles.indexOf(currentTitle);
                                                    editor.putInt("whichbutton2", whichbutton);
                                                    editor.commit();
                                                    saveBoth();


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
                                    editor.putString("CT1", currentTitle);
                                    editor.putInt("whichbutton2", whichbutton);
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






                    return false;
                }
            });
            buttons[i] = button;
        }














        add2 = new AppCompatButton(this);
        add2.setText("+");
        add2.setBackgroundColor(R.color.black);
        add2.setTextColor(R.color.black);
        add2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                input = new EditText(Prosncons.this);
                input.setMaxLines(1);
                input.setInputType(InputType.TYPE_CLASS_TEXT);
                AlertDialog.Builder builder = new AlertDialog.Builder(Prosncons.this);
                builder.setMessage(R.string.setTitle);
                builder.setCancelable(true)
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {

                                if (String.valueOf(input.getText()).equals("")) {
                                    Toast.makeText(Prosncons.this, R.string.emptytitle, Toast.LENGTH_LONG).show();
                                    Intent intent = getIntent();
                                    startActivity(intent);
                                } else {
                                    titles.add(String.valueOf(input.getText()));
                                    System.out.println(titles);

                                    currentTitle = String.valueOf(input.getText());
                                    SharedPreferences.Editor editor = sharedPreferences.edit();
                                    editor.putString("CT1", currentTitle);
                                    editor.apply();
                                    saveTitle();
                                    whichbutton = titles.size() - 1;
                                    editor.putInt("whichbutton2", whichbutton);
                                    editor.commit();
                                    buttony2 = layout.getRight();
                                    editor.putInt("buttony2", buttony2);
                                    editor.commit();
                                    editor.apply();

                                    Intent intent = getIntent();
                                    startActivity(intent);


                                }
                            }
                        });
                final AlertDialog alert = builder.create();
                alert.setView(input);
                alert.show();
                alert.getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(R.color.black);


                //open keyboard
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
        buttons[numberofbuttons] = add2;



        for(int j= 0; j <numberofbuttons+1; j++){
            layout.addView(buttons[j]);
        }


        if (titles.size()==0){
        }
        else{
            for( int k=0; k<titles.size(); k++) {
                layout.getChildAt(k).setBackgroundColor(R.color.black);
            }
            whichbutton = sharedPreferences.getInt("whichbutton2", titles.size()-1);

                    layout.getChildAt(whichbutton).setBackgroundColor(0);




        }


    }

    private void saveTitle() {
        SharedPreferences sharedPreferences = getSharedPreferences("shared preferences", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson3 = new Gson();
        String json3 = gson3.toJson(titles);
        editor.putString("titles" , json3);
        //replace activities list with en es translation
        editor.apply();
    }


    private void saveData2() {
        SharedPreferences sharedPreferences = getSharedPreferences("shared preferences", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(myArrayList2);
        editor.putString("cons list" , json);
        //replace activities list with en es translation
        editor.apply();
    }

    private void saveData() {
        SharedPreferences sharedPreferences = getSharedPreferences("shared preferences", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(myArrayList);
        editor.putString("pros and cons list" , json);
        //replace activities list with en es translation
        editor.apply();
    }
    private void loadData(){
        SharedPreferences sharedPreferences = getSharedPreferences("shared preferences", MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences.getString("pros and cons list", null);
        Type type = new TypeToken<ArrayList>() {}.getType();
        myArrayList = gson.fromJson(json, type);

        if (myArrayList == null) {
            myArrayList = new ArrayList<>();
        }

    }

    private void loadData2(){
        SharedPreferences sharedPreferences = getSharedPreferences("shared preferences", MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences.getString("cons list", null);
        Type type = new TypeToken<ArrayList>() {}.getType();
        myArrayList2 = gson.fromJson(json, type);

        if (myArrayList2 == null) {
            myArrayList2 = new ArrayList<>();
        }

    }



    private void loadData3() {
        SharedPreferences sharedPreferences = getSharedPreferences("shared preferences", MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences.getString(String.valueOf(currentTitle), null);
        Type type = new TypeToken<ArrayList>() {}.getType();
        myArrayList2 = gson.fromJson(json, type);

        if (myArrayList2 == null) {
            myArrayList2 = new ArrayList<>();
        }

    }


    private void saveBoth() {
        SharedPreferences sharedPreferences = getSharedPreferences("shared preferences", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gsonn = new Gson();
        String jsonn = gsonn.toJson(myArrayList);
        editor.putString(currentTitle + "pro", jsonn);
        editor.apply();

        Gson gsonn2 = new Gson();
        String jsonn2 = gsonn2.toJson(myArrayList2);
        editor.putString(currentTitle + "con" , jsonn2);
        //replace activities list with en es translation
        editor.apply();
        System.out.println(currentTitle + "saveBoth's");
        //save title


    /*    Gson gson3 = new Gson();
        String json3 = gson3.toJson(titles);
        editor.putString("titles" , json3);
        //replace activities list with en es translation
        editor.apply();*/
    /*    editor.putString("title", input.getText().toString());
        editor.apply();*/
    }


    private void loadBoth(){
        //load pros
        SharedPreferences sharedPreferences = getSharedPreferences("shared preferences", MODE_PRIVATE);
        Gson gson5 = new Gson();
        String json5 = sharedPreferences.getString(currentTitle + "pro", null);
        Type type5 = new TypeToken<ArrayList>() {}.getType();
        myArrayList = gson5.fromJson(json5, type5);

        if (myArrayList == null) {
            myArrayList = new ArrayList<>();
        }


        Gson gson4 = new Gson();
        String json4 = sharedPreferences.getString(currentTitle + "con", null);
        Type type4 = new TypeToken<ArrayList>() {}.getType();
        myArrayList2 = gson4.fromJson(json4, type4);

        if (myArrayList2 == null) {
            myArrayList2 = new ArrayList<>();
        }

        adapter = new ArrayAdapter<String>(Prosncons.this, android.R.layout.simple_list_item_1, myArrayList);
        adapter2 = new ArrayAdapter<String>(Prosncons.this, android.R.layout.simple_list_item_1, myArrayList2);
        myListView.setAdapter(adapter);
        myListView2.setAdapter(adapter2);

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

                AlertDialog.Builder builder = new AlertDialog.Builder(Prosncons.this);
                builder.setMessage(R.string.prosncons_text);
                final AlertDialog alert = builder.create();
                alert.show();
                System.out.println("THIS!!!!");



                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }
}