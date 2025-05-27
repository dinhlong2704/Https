package com.example.http.ui.theme;

import android.os.Bundle;

import com.example.http.databinding.MainActivityBinding;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;

import com.example.http.R;

public class MainActivity extends AppCompatActivity {


    private MainActivityBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        binding = MainActivityBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        initView();

    }

    private void initView() {

    }
}
