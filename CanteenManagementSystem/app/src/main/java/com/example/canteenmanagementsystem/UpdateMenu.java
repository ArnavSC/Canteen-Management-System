package com.example.canteenmanagementsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class UpdateMenu extends AppCompatActivity {
    String c;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_menu);
        Intent intent=getIntent();
        c=intent.getStringExtra("abc");
    }

    public void combos(View view) {
        if(c.equalsIgnoreCase("a")){
            Intent in=new Intent(getApplicationContext(),AddCombo.class);
            startActivity(in);
        }
        else if(c.equalsIgnoreCase("e")){
            Intent in=new Intent(getApplicationContext(),ComboMenu.class);
            in.putExtra("det","Edit");
            startActivity(in);
        }
        else{
            Intent in=new Intent(getApplicationContext(),ComboMenu.class);
            in.putExtra("det","Click On the Name To Delete");
            startActivity(in);

        }
    }

    public void lunch(View view) {
        if(c.equalsIgnoreCase("a")){
            Intent in=new Intent(getApplicationContext(),Addlunch.class);
            startActivity(in);
        }
        else if(c.equalsIgnoreCase("e")){}
        else{
            Intent in=new Intent(getApplicationContext(),LunchMenu.class);
            in.putExtra("abc","Delete");
            startActivity(in);
        }
    }

    public void products(View view) {
        if(c.equalsIgnoreCase("a")){
            Intent in=new Intent(getApplicationContext(),AddProducts.class);
            startActivity(in);
        }
        else if(c.equalsIgnoreCase("e")){
            Intent in=new Intent(getApplicationContext(),ComboMenu.class);
            in.putExtra("det","Edit Products");
            startActivity(in);
        }
        else{
            Intent in=new Intent(getApplicationContext(),ComboMenu.class);
            in.putExtra("det","Product Delete");
            startActivity(in);
        }
    }
}