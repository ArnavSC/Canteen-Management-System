package com.example.canteenmanagementsystem;

import android.content.Intent;
import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

public class Orders {
    String userName, price;
    ArrayList<items> products;

    public Orders(String userName, String price) {
        this.userName = userName;
        this.price = price;
    }

    public void addProducts(String name, int quantity) {
        products.add(new items(name, quantity));
    }

    public void addOrders() {
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("Orders");
        UUID id = UUID.randomUUID();
        HashMap<String,String> hashMap=new HashMap<>();
        hashMap.put("userName",userName);
        hashMap.put("price",price);
        ref.child(String.valueOf(id)).setValue(hashMap).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                Log.d("donedone","donedone");

            }
        });
        HashMap<String, String> hash = new HashMap<>();
        for(int i = 0; i < products.size(); i++) {
            hash.put(products.get(i).name, String.valueOf(products.get(i).quantity));
        }
        ref.child(String.valueOf(id)).child("products").setValue(hash).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                Log.d("donedone","donedone");

            }
        });
    }
}
