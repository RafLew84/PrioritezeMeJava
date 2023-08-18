package com.example.prioritezemejava;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import com.example.prioritezemejava.viewmodel.TaskViewModel;

public class MainActivity extends AppCompatActivity {

    public TaskViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewModel = new ViewModelProvider(this).get(TaskViewModel.class);
    }
}