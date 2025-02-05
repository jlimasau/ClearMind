package com.clearmind123;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.fragment.app.ListFragment;

import android.os.Handler;
import android.os.SystemClock;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.tabs.TabLayout;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.w3c.dom.Text;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Random;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link page1#newInstance} factory method to
 * create an instance of this fragment.
 */
public class page1 extends ListFragment {

    TextView textView;
    ListView myListView;

    String currentTitle;
    ArrayList myArrayList;
    ListAdapter adapter;
    ArrayList<String> titles = new ArrayList<>();
    EditText text1;
    int changeenterbtnaction1 = 0;

    int currentpos1;
    String newString;





    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_page1, container, false);
        //String data = getArguments().getString("key");
        int num = getArguments().getInt("number");
      //  textView.setText(data);





        SharedPreferences sharedPreferences = this.getActivity().getSharedPreferences("shared preferences", Context.MODE_PRIVATE);

        Gson gson = new Gson();
        String json = sharedPreferences.getString("quickstartTitles", null);
        Type type = new TypeToken<ArrayList>() {}.getType();
        titles = gson.fromJson(json, type);
        if (titles == null) {
            titles = new ArrayList<>();
        }


        Gson gson5 = new Gson();
        currentTitle = sharedPreferences.getString("CT", null);
//change what current title is based on number



        if(num==titles.size()){

        }

        else{


            String var1 = String.valueOf(titles.get(num));
        String json5 = sharedPreferences.getString(var1, null);

        Type type5 = new TypeToken<ArrayList>() {}.getType();
        myArrayList = gson5.fromJson(json5, type5);

        if (myArrayList == null) {
            myArrayList = new ArrayList<>();
        }
    }
        adapter = new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_list_item_1, myArrayList);
        // myListView.setAdapter(adapter);

        setListAdapter(adapter);



        return view;
    }


@Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);




    text1 = getActivity().findViewById(R.id.inputtext);
    Random rand = new Random();

//    Button rollbutton = getActivity().findViewById(R.id.button4);



    SharedPreferences sharedPreferences = getActivity().getSharedPreferences("shared preferences", Context.MODE_PRIVATE);



    getListView().setOnItemClickListener(new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            int positem = getListView().getFirstVisiblePosition();


            String tempString = (String) adapter.getItem(position);

            PopupMenu popupMenu = new PopupMenu(getActivity(), view);
            popupMenu.getMenu().add("✔");
            popupMenu.getMenu().add("✘");
            popupMenu.getMenu().add("Edit");
            popupMenu.getMenu().add("Delete");

            popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                @Override
                public boolean onMenuItemClick(MenuItem menuItem) {
                    System.out.println("MyArrayList: "+myArrayList);
                    currentTitle = sharedPreferences.getString("CT", null);
                    if (currentTitle == null){
                        if(titles.size()==0){
                            //do nothing
                        }
                        else {
                            currentTitle = titles.get(titles.size()).toString();
                        }
                    }


                    switch (menuItem.getTitle().toString()){
                        case "✔" :
                               System.out.println("check");

                            if (tempString.contains("✘") == true || tempString.contains("✔")) {
                                String newString3 = tempString.replace("⬅", "");

                                String newString2 = newString3.replace("✔", "");
                                String newString1 = newString2.replace("✘", "✔");


                                myArrayList.remove(adapter.getItem(position));
                                myArrayList.add(position,newString1);
                               // adapter.insert(newString1, position);
                            } else {
                                String newString2 = tempString.replace("⬅", "");
                                newString = "✔" + newString2;
                                myArrayList.remove(adapter.getItem(position));
                                myArrayList.add(position, newString);
                            }
                            SharedPreferences.Editor editor = sharedPreferences.edit();


                            System.out.println("position after checkmark is: " + position);
                            editor.putInt(newString ,position);
                            editor.apply();
                            saveData();
                           setListAdapter(adapter);
                           System.out.println("Position" + position + "array" + myArrayList);


                           // Integer pagenumber = sharedPreferences.getInt("tabpos", 0);



                        /*    SharedPreferences sharedPreferences = getActivity().getSharedPreferences("shared preferences", Context.MODE_PRIVATE);
                            SharedPreferences.Editor editor = sharedPreferences.edit();
                            editor.putInt("btnaction1", changeenterbtnaction1);
                            editor.apply();*/


                            //((QuickStart2)getActivity()).saveData();
                            break;
                        case "✘" :
                                System.out.println("X");
                            if (tempString.contains("✔") == true) {
                                String newString2 = tempString.replace("⬅", "");
                                String newString1 = newString2.replace("✔", "✘");
                                myArrayList.remove(adapter.getItem(position));
                                myArrayList.add(position, newString1);
                            } else if (tempString.contains("✘") == true) {
                                String newString2 = tempString.replace("⬅", "");

                                String newString1 = newString2.replace("✘", "");
                                myArrayList.remove(adapter.getItem(position));
                                myArrayList.add(position, newString1);
                            } else {
                                String newString2 = tempString.replace("⬅", "");
                                String newString = "✘" + newString2;
                                myArrayList.remove(adapter.getItem(position));
                                myArrayList.add(position, newString);
                            }
                            saveData();
                            setListAdapter(adapter);
                            //((QuickStart2)getActivity()).dataChanged(getArguments().getInt("number"));
                            break;
                        case "Edit" :

                            String stringy1 = (String) adapter.getItem(position);
                            text1.setText(stringy1.replace("✔", "").replace("✘", "").replace("⬅", "").toString());
                            int currentpos;
                            System.out.println("text1 is: " + text1.toString());
                            changeenterbtnaction1 = 1; //HERE
                            currentpos1 = position;
/*
                            SharedPreferences sharedPreferences = getActivity().getSharedPreferences("shared preferences", Context.MODE_PRIVATE);
*/
                            SharedPreferences.Editor editor1 = sharedPreferences.edit();
                            editor1.putInt("btnaction1", changeenterbtnaction1);
                            editor1.putInt("currentpos1", currentpos1);
                            editor1.apply();
                            //text1 = getActivity().findViewById(R.id.inputtext);
                            //text1.setEnabled(true);

                            new Handler().postDelayed(new Runnable() {

                                public void run() {


                                    text1.dispatchTouchEvent(MotionEvent.obtain(SystemClock.uptimeMillis(), SystemClock.uptimeMillis(), MotionEvent.ACTION_DOWN, text1.getBottom(), 0f, 0));
                                    text1.dispatchTouchEvent(MotionEvent.obtain(SystemClock.uptimeMillis(), SystemClock.uptimeMillis(), MotionEvent.ACTION_UP, text1.getBottom(), 0f, 0));

                                }
                            }, 200);

                            //InputMethodManager inputMethodManager = (InputMethodManager)

                            break;

                        case "Delete" :
                            //Integer num = getArguments().getInt("number");
                                System.out.println("delete");
                            myArrayList.remove(adapter.getItem(position));
                            saveData();
                            setListAdapter(adapter);

                            //((QuickStart2)getActivity()).dataChanged(getArguments().getInt("number"));
                            //setListAdapter(getListAdapter());

                           // ((QuickStart2)getActivity()).dataChanged(0);
                            break;
                    }


                    getListView().setSelection(positem);

                    return false;

                }

            });
            popupMenu.show();

        }
    });




}


    private void loadList() {
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("shared preferences", Context.MODE_PRIVATE);
        Gson gson5 = new Gson();
        String json5 = sharedPreferences.getString(currentTitle, null);
        Type type5 = new TypeToken<ArrayList>() {}.getType();
        myArrayList = gson5.fromJson(json5, type5);

       /* if (myArrayList == null) {
            myArrayList = new ArrayList<>();
        }*/

       // adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, myArrayList);

    }

    private void saveData() {
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("shared preferences", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(myArrayList);
        editor.putString(currentTitle , json);
        //replace activities list with en es translation
        editor.apply();
        editor.commit();

    }

    public void setSelection1(int position3){
        getListView().setSelection(position3);
        System.out.println("HERE!!!!!!!!!!!!!!!!");


      //  SharedPreferences sharedPreferences = this.getActivity().getSharedPreferences("shared preferences", Context.MODE_PRIVATE);



       // adapter = new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_list_item_1, myArrayList);
        // myListView.setAdapter(adapter);

      /*  adapter = getListAdapter();
        setListAdapter(adapter);
        System.out.println("THIS: " + adapter);*/
    }


/*    @Override
    public void onItemClick(AdapterView parent, View view, int position, long id){

    }*/


}