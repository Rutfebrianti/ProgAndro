package com.example.progandro;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;


public class About extends Fragment {

        private Button changeButton;

        @Override
        public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup viewGroup, @Nullable Bundle savedInstanceState) {
            super.onCreateView(inflater, viewGroup, savedInstanceState);
            View rootView = inflater.inflate(R.layout.about_fragment, viewGroup, false);
            changeButton = rootView.findViewById(R.id.btn_Add);
            changeButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getActivity(), ShowItem.class);
                    startActivity(intent);
                }
            });
            return rootView;
        }

    }