package com.example.canteenmanagementsystem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class Addlunch extends AppCompatActivity {
    EditText name,foodname,foodname1,foodname2,foodname3,foodname4,price,url,vegn;
    Button add1,add2;
    String name1;
    FirebaseDatabase firebaseDatabase= FirebaseDatabase.getInstance();
    FirebaseDatabase rootNode;
    DatabaseReference reference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addlunch);
        name=findViewById(R.id.comboname);
        foodname=findViewById(R.id.foodname);
        foodname1=findViewById(R.id.foodname1);
        foodname2=findViewById(R.id.foodname2);
        foodname3=findViewById(R.id.foodname3);
        foodname4=findViewById(R.id.foodname4);
        price=findViewById(R.id.cost);
        vegn=findViewById(R.id.vegnon);
        url=findViewById(R.id.url);
        add2=findViewById(R.id.add2);
        rootNode = FirebaseDatabase.getInstance();
        add2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                name1 = name.getText().toString();
                if(name1==null){
                    Toast.makeText(Addlunch.this, "Enter Day", Toast.LENGTH_SHORT).show();
                }
                else if(vegn.getText().toString()==null){
                    Toast.makeText(Addlunch.this, "Enter Veg or Nonveg", Toast.LENGTH_SHORT).show();
                }
                else if(foodname1.getText().toString()==null){
                    Toast.makeText(Addlunch.this, "Enter Atleast One Food Item", Toast.LENGTH_SHORT).show();
                }
                else if(price.getText().toString()==null){
                    Toast.makeText(Addlunch.this, "Enter Price", Toast.LENGTH_SHORT).show();}
                else if(url.getText().toString()==null){
                    Toast.makeText(Addlunch.this, "Enter Url", Toast.LENGTH_SHORT).show();}
                else{
                    send();
                }
            }
        });
    }

    private void send() {
        int i=1;
        reference=FirebaseDatabase.getInstance().getReference("Lunch").child(name1).child(vegn.getText().toString());
        HashMap<String,String> hashMap=new HashMap<>();
        if(foodname.getText().toString().equalsIgnoreCase("1")){}
        else{
            hashMap.put(String.valueOf(i),foodname.getText().toString());
            i++;
        }
        if(foodname1.getText().toString().equalsIgnoreCase("2")){}
        else{
            hashMap.put(String.valueOf(i),foodname1.getText().toString());
            i++;
        }
        if(foodname2.getText().toString().equalsIgnoreCase("3")){}
        else{
            hashMap.put(String.valueOf(i),foodname2.getText().toString());
            i++;
        }
        if(foodname3.getText().toString().equalsIgnoreCase("4")){}
        else{
            hashMap.put(String.valueOf(i),foodname3.getText().toString());
            i++;
        }
        if(foodname4.getText().toString().equalsIgnoreCase("5")){}
        else{
            hashMap.put(String.valueOf(i),foodname4.getText().toString());
            i++;
        }
        hashMap.put("price",price.getText().toString());
        hashMap.put("totalQuantity",url.getText().toString());
        hashMap.put("availableQuantity",url.getText().toString());
        reference.setValue(hashMap).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                Log.d("donedone","donedone");

            }
        });

    }
}