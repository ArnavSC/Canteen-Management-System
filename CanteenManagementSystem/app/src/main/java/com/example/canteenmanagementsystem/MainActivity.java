package com.example.canteenmanagementsystem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {

    String user;
    EditText username1;
    EditText password1;
    EditText number;
    String usern,pass, numberS;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent = getIntent();

        user = intent.getStringExtra("user");

        username1=findViewById(R.id.userName);
        number=findViewById(R.id.number);
        password1=findViewById(R.id.password);
    }

    public void callHomescreen(View view) {

        usern = username1.getText().toString();
        pass = password1.getText().toString();
        numberS = number.getText().toString();
        if(user.equals("Admin")) {
            loadDataFromFireBase();
        } else if(user.equals("Student")) {
            user = "Users";
            loadDataFromFireBase();
        }
    }

    public void callForgotPassword(View view) {
    }

    private void loadDataFromFireBase() {
        final DatabaseReference Admin = FirebaseDatabase.getInstance().getReference(user);
        Admin.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String userid = snapshot.child(numberS).child("userName").getValue().toString();
                Log.d("username",snapshot.child(numberS).child("userName").getValue().toString());
                String userpass = snapshot.child(numberS).child("password").getValue().toString();
                if (userid.equalsIgnoreCase(usern)){
                    if (pass.equalsIgnoreCase(userpass)){
                        Toast.makeText(getApplicationContext(),"Logged in",Toast.LENGTH_SHORT).show();
                        if(user.equals("Admin")) {
                            Intent in = new Intent(getApplicationContext(), Adminpg.class);
                            startActivity(in);
                        } else if(user.equals("Users")){
                            Intent in = new Intent(getApplicationContext(), HomeScreen.class);
                            startActivity(in);
                        }
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
}