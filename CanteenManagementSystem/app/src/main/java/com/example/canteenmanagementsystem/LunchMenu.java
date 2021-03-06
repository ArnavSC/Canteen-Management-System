package com.example.canteenmanagementsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.Task;
import com.google.firebase.database.FirebaseDatabase;

public class LunchMenu extends AppCompatActivity {
    String day="/0" , category="/0";
    String title;
    Button b1;
    FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    Task<Void> reference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lunch_menu);
        Intent in=getIntent();
        title=in.getStringExtra("abc");
        b1=findViewById(R.id.edit);
        b1.setText(title);
    }


    public void monday(View view) {
        day="monday";
    }

    public void tuesday(View view) {
        day="tuesday";
    }

    public void wesnesday(View view) {

        day="wednesday";

    }

    public void thursday(View view) {
        day="thursday";
    }

    public void Veg(View view) {
        category="veg";
    }



    public void EditLunch(View view) {
        if (!(day.equalsIgnoreCase("/0") || category.equalsIgnoreCase("/0"))) {
            if (title.equalsIgnoreCase("delete")) {
                reference = FirebaseDatabase.getInstance().getReference("Lunch").child(day).child(category).removeValue();
            }
            if (title.equalsIgnoreCase("edit")) {
            } else {
                Toast.makeText(this, "Please Select All Fields", Toast.LENGTH_SHORT).show();
            }
        }
    }
    public void Friday(View view) {
        day="friday";
    }

    public void nonveg(View view) {
        category = "nonveg";
    }
}
