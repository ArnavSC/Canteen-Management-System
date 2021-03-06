package com.example.canteenmanagementsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Adminpg extends AppCompatActivity {
    String c;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adminpg);
    }

    public void add(View view) {
        c="a";
        Intent intent=new Intent(Adminpg.this,UpdateMenu.class);
        intent.putExtra("abc",c);
        startActivity(intent);
    }

    public void edit(View view) {
        c="e";
        Intent intent=new Intent(Adminpg.this,UpdateMenu.class);
        intent.putExtra("abc",c);
        startActivity(intent);
    }

    public void delete(View view) {
        c="d";
        Intent intent=new Intent(Adminpg.this,UpdateMenu.class);
        intent.putExtra("abc",c);
        startActivity(intent);
    }
}