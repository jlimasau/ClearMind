package com.clearmind123;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class Adapter1 extends FragmentStateAdapter {

   // private String[] fragmentData;
    public int numPages;

    public Adapter1(@NonNull FragmentActivity fragmentActivity, int numPages){
        super(fragmentActivity);

       // this.fragmentData = fragmentData;
        this.numPages = numPages;
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        Bundle args = new Bundle();
        Fragment fragment = new page1();

        //args.putString(page1.TITLE, "");

        //args.putString("key", fragmentData[position]);
        args.putInt("number", position);
        fragment.setArguments(args);


        return fragment;
    }

    @Override
    public int getItemCount() {

        return numPages;
    }




}

