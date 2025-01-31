package com.clearmind123;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class CustomAdapter extends ArrayAdapter<String> {
        private ArrayList<String> items = new ArrayList<String>();
        private Context mContext;
        private int id;


        public CustomAdapter(Context context, int textViewResourceId, ArrayList<String> list) {
            super(context, textViewResourceId, list);
            mContext = context;
            id = textViewResourceId;
            items = list;

        }


    @Override
    public View getView(int position, View view, ViewGroup parent) {
            View view1 = view;
            if (view1 == null) {
                LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                view1 = inflater.inflate(R.layout.custom_list_layout, null);
            }

            //Handle TextView and display string from your list
            TextView text1= (TextView)view.findViewById(R.id.textlayout);

            if(items.get(position) != null){
                text1.setBackgroundColor(Color.GRAY);

        }


            return view1;
        }
    }




