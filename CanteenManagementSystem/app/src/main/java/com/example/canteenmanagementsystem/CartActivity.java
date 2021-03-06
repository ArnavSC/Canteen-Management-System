package com.example.canteenmanagementsystem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

public class CartActivity extends AppCompatActivity {
    private ListView listView;

    public CartAdapter cartAdapter;

    String username;

    FirebaseDatabase mDatabase;
    Button order;
    int price=0;
    HashMap<String,String> hash=new HashMap<>();
    ArrayList<cartItems> mCartItems=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        Intent intent = getIntent();

        username = "Jayesh";
        listView = findViewById(R.id.itemList);

        mDatabase = FirebaseDatabase.getInstance();
        cartAdapter= new CartAdapter(this,R.layout.activity_cart_adapter,mCartItems);

        readUsers();
        order=findViewById(R.id.order);
        order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UUID id = UUID.randomUUID();
                DatabaseReference ref = mDatabase.getReference("Orders").child(id+"");
                HashMap<String, String> hashMap=new HashMap<>();
                hashMap.put("username",username);
                hashMap.put("price",price+"");
                ref.setValue(hashMap);
                ref.child("Products").setValue(hash);
                DatabaseReference newref= mDatabase.getReference("cart").child("Jayesh");
                newref.removeValue();
                startActivity(new Intent(getApplicationContext(), Payment.class));
            }
        });

    }
    private void readUsers() {

        DatabaseReference reference = mDatabase.getReference("cart").child("Jayesh");//.child(mAuth.getCurrentUser().getUid());


            final int[] y = {0};
        reference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    mCartItems.clear();
                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                        y[0] +=1;
                        cartItems item = snapshot.getValue(cartItems.class);
                        int n=Integer.parseInt(item.getPrice());
                        price+=n;
                        hash.put(item.getName()+"",item.getQuantity()+"");
                        mCartItems.add(item);
                    }
                    listView.setAdapter(cartAdapter);
                }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}