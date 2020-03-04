package com.example.progandro;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;


public class About extends Fragment {

        @Override
        public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup viewGroup, @Nullable Bundle savedInstanceState) {
            super.onCreateView(inflater, viewGroup, savedInstanceState);
            System.out.println("tes");
            return inflater.inflate(R.layout.about_fragment, viewGroup, false);
        }
    }