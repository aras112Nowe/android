package com.example.aras1.calculator;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;

import com.example.aras1.calculator.utils.AutoWiredXML;
import com.example.aras1.calculator.utils.KittyKit;

public class Start extends AppCompatActivity
    {
    @AutoWiredXML
    private Button advanced, standard, my, off;

    @Override
    protected void onCreate(Bundle savedInstanceState)
        {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        KittyKit.initFieldByReflection(this);

        advanced.setOnClickListener((v ->
        {
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("standard", false);
        startActivity(intent);
        }));
        standard.setOnClickListener((v ->
        {
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("standard", true);
        startActivity(intent);
        }));
        my.setOnClickListener((v ->
        {
        Intent intent = new Intent(this, About.class);
        startActivity(intent);
        }));
        off.setOnClickListener((v ->
        {
        System.exit(0);
        }));
        }
    }

