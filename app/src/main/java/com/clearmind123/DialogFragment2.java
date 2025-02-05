package com.clearmind123;

import static android.content.Context.MODE_PRIVATE;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.InputType;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.HorizontalScrollView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class DialogFragment2 extends AppCompatDialogFragment {
    EditText input;
    private ArrayList titles;

    String currentTitle;

    int whichbutton;
    int buttony;
    ArrayList<String> myArrayList;
    String currentTab;
    private ViewPager2 viewPager2;
    private FragmentStateAdapter adapter1;
    ArrayAdapter<String> adapter;
    ListView myListView;

//This dialog is for editing the title
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {


        //  loadList();


        input = new EditText(getActivity());
        input.setMaxLines(1);
        input.setInputType(InputType.TYPE_CLASS_TEXT);
        input.setTextColor(Color.GRAY);
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("shared preferences", MODE_PRIVATE);


        currentTab = sharedPreferences.getString("currentTab", null).replace("\u200E ", "");


        input.setText(currentTab);
        //input.setTextColor(getResources().getColor(R.color.black));
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity(), R.style.MyDialogTheme);
        builder.setMessage(R.string.setTitle);

        // getDialog().getWindow().setBackgroundDrawableResource(android.R.color.darker_gray);
        builder.setCancelable(true)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                        loadList();
                        System.out.println("Myarraylist: " + myArrayList);

                    /*    loadList();
                        loadTitles();*/


                        //currentTitle


                        if (String.valueOf(input.getText()).equals("")) {
                            Toast.makeText(getActivity(), R.string.emptytitle, Toast.LENGTH_LONG).show();
                            Intent intent = new Intent(getActivity(), QuickStart2.class);
                            startActivity(intent);
                        } else {

                            SharedPreferences sharedPreferences = getActivity().getSharedPreferences("shared preferences", MODE_PRIVATE);
                            Gson gson = new Gson();
                            String json = sharedPreferences.getString("quickstartTitles", null);
                            Type type = new TypeToken<ArrayList>() {
                            }.getType();
                            titles = gson.fromJson(json, type);


                            if (titles == null) {
                                titles = new ArrayList<>();
                            }
                            SharedPreferences.Editor editor = sharedPreferences.edit();


                            editor.putString("changeTitle", input.getText().toString());


                            titles.set(titles.indexOf(String.valueOf(currentTab)), String.valueOf(input.getText())+"\u200E");
                            System.out.println("TITLES: " + titles);
                            currentTitle = String.valueOf(input.getText()) + "\u200E";
                            saveData();
                            whichbutton = titles.indexOf(currentTitle);
                            editor.putString("CT", currentTitle);
                            editor.apply();
                            saveTitle();
                            // SharedPreferences.Editor editor = sharedPreferences.edit();

                            editor.putInt("whichbutton", whichbutton);



                          /*  editor.putInt("position1", whichbutton);
                            editor.putString("newtitle", String.valueOf(input.getText()));*/
                            editor.commit();

                            Intent intent = new Intent(getActivity(), QuickStart2.class);
                            startActivity(intent);

                        }
                    }
                });

        final AlertDialog alert = builder.create();
        alert.setView(input);
        input.requestFocus();

        alert.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);


        // Window window = getDialog().getWindow();
        //window.setBackgroundDrawableResource(android.R.color.transparent);
        alert.show();


        //.getWindow().setBackgroundDrawableResource(android.R.color.darker_gray);
        Button posbtn = alert.getButton(DialogInterface.BUTTON_POSITIVE);
        // posbtn.setBackgroundColor(Color.WHITE);
        posbtn.setTextColor(Color.GRAY);
        //alert.getButton(androidx.appcompat.app.AlertDialog.BUTTON_POSITIVE).setTextColor(R.color.black);

        return alert;
    }

    private void saveTitle() {
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("shared preferences", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson3 = new Gson();
        String json3 = gson3.toJson(titles);
        editor.putString("quickstartTitles", json3);
        //replace activities list with en es translation
        editor.apply();
    }

    private void saveData() {
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("shared preferences", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(myArrayList);
        editor.putString(currentTitle, json);
        //replace activities list with en es translation
        editor.apply();

    }

    private void loadList() {
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("shared preferences", MODE_PRIVATE);
        Gson gson5 = new Gson();
        String json5 = sharedPreferences.getString(currentTab, null);
        Type type5 = new TypeToken<ArrayList>() {
        }.getType();
        myArrayList = gson5.fromJson(json5, type5);

        if (myArrayList == null) {
            myArrayList = new ArrayList<>();
        }

        // adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, myArrayList);
        //  myListView.setAdapter(adapter);
    }

    private void loadTitles() {
        // HorizontalScrollView scrollView = findViewById(R.id.scrollView);

        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("shared preferences", MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences.getString("quickstartTitles", null);
        Type type = new TypeToken<ArrayList>() {
        }.getType();
        titles = gson.fromJson(json, type);


        if (titles == null) {
            titles = new ArrayList<>();
        }
    }
}


