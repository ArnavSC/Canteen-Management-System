package com.example.canteenmanagementsystem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class ProductDisplay extends AppCompatActivity {
    String a,img;
    EditText name,price,totalquantity,availablequantity;
    DatabaseReference reference;
    FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_display);
        Intent in=getIntent();
        a=in.getStringExtra("prodname");
        name=findViewById(R.id.name);
        price=findViewById(R.id.price);
        totalquantity=findViewById(R.id.item2);
        availablequantity=findViewById(R.id.item1);

        final DatabaseReference P = firebaseDatabase.getReference("Products").child(a);
        P.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                name.setText(a);
                price.setText(snapshot.child("price").getValue().toString());
                totalquantity.setText(snapshot.child("totalQuantity").getValue().toString());
                availablequantity.setText(snapshot.child("availableQuantity").getValue().toString());
                img=snapshot.child("image").getValue().toString();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public void edit(View view) {
        reference= FirebaseDatabase.getInstance().getReference("Products").child(a);
        HashMap<String,String> hashMap=new HashMap<>();
        hashMap.put("totalQuantity",totalquantity.getText().toString());
        hashMap.put("availableQuantity",availablequantity.getText().toString());
        hashMap.put("price",price.getText().toString());
        hashMap.put("image",img);
        reference.setValue(hashMap).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                Log.d("donedone","donedone");

            }
        });
    }
}