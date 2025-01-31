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
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;

import com.google.android.material.tabs.TabLayout;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class DialogFragment extends AppCompatDialogFragment implements DialogInterface.OnDismissListener {
    EditText input;
    private ArrayList titles;

    String currentTitle;

    int whichbutton;
    int buttony;
    ArrayList<String> myArrayList = new ArrayList<>();
    String currentTab;



    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {

        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("shared preferences", MODE_PRIVATE);
        currentTab = sharedPreferences.getString(currentTitle, null);
        input = new EditText(getActivity());
        input.setMaxLines(1);
        input.setInputType(InputType.TYPE_CLASS_TEXT);
        input.setTextColor(Color.BLACK);
        //input.setBackgroundColor(Color.GREEN);
        //input.setTextColor(getResources().getColor(R.color.black));
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity(), R.style.MyDialogTheme);
        builder.setMessage(R.string.setTitle);

       // getDialog().getWindow().setBackgroundDrawableResource(android.R.color.darker_gray);
        builder.setCancelable(true)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        if (String.valueOf(input.getText()).equals("")) {
                            Toast.makeText(getActivity(), R.string.emptytitle, Toast.LENGTH_LONG).show();
                            Intent intent = new Intent(getActivity(), QuickStart2.class);
                            startActivity(intent);
                        } else {

                            Gson gson = new Gson();
                            String json = sharedPreferences.getString("quickstartTitles", null);
                            Type type = new TypeToken<ArrayList>() {
                            }.getType();
                            titles = gson.fromJson(json, type);


                            if (titles == null) {
                                titles = new ArrayList<>();
                            }

                            titles.add(String.valueOf(input.getText()));
                            System.out.println(titles);
                            currentTitle = String.valueOf(input.getText());
                            SharedPreferences.Editor editor = sharedPreferences.edit();
                            editor.putString("CT", currentTitle);
                            editor.commit();
                            saveTitle();
                            // SharedPreferences.Editor editor = sharedPreferences.edit();
                            whichbutton = titles.size() - 1;
                            editor.putInt("whichbutton", whichbutton);
                            editor.commit();

                            //buttony = tabLayout.getLeft();
                            editor.putInt("buttony", buttony);
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
        posbtn.setTextColor(Color.BLACK);
        //alert.getButton(androidx.appcompat.app.AlertDialog.BUTTON_POSITIVE).setTextColor(R.color.black);

        return alert;
    }

    private void saveTitle() {
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("shared preferences", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson3 = new Gson();
        String json3 = gson3.toJson(titles);
        editor.putString("quickstartTitles" , json3);
        //replace activities list with en es translation
        editor.apply();
    }

    @Override
    public void onDismiss(final DialogInterface dialog) {
        //Fragment dialog had been dismissed
/*        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("shared preferences", MODE_PRIVATE);

        SharedPreferences.Editor editor = sharedPreferences.edit();*/
      /*  currentTitle = currentTab;
                editor.putString("CT", currentTitle);
                editor.commit();*/
    Intent intent = new Intent(getActivity(), QuickStart2.class);
    startActivity(intent);
    }
}


