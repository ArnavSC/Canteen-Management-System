package com.example.canteenmanagementsystem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Calendar;
import java.util.HashMap;

public class LunchActivity extends AppCompatActivity {
    private ListView listView;

    public ItemArrayAdapter itemArrayAdapter;

    FirebaseDatabase mDatabase;
    String var;
    String imageUrl;
    ImageView comboImage;
    Button addToCart;
    String username;
    TextView availableQuantity, name, price, item1,item2,item3,item4,item5;

    final int[] available = {0};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lunch);

        Intent intent = getIntent();
        var = intent.getStringExtra("token");
        username = intent.getStringExtra("userName");

        mDatabase = FirebaseDatabase.getInstance();

        availableQuantity=findViewById(R.id.availableQuantity);
        price=findViewById(R.id.price);
        comboImage=findViewById(R.id.comboImage);
        item1=findViewById(R.id.item1);
        item2=findViewById(R.id.item2);
        item3=findViewById(R.id.item3);
        item4=findViewById(R.id.item4);
        item5=findViewById(R.id.item5);
        name=findViewById(R.id.name);
        addToCart=findViewById(R.id.addToCart);
        name.setText(var);
        readUsers();
        addToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatabaseReference reference;
                reference = FirebaseDatabase.getInstance().getReference("cart").child(username).child(var);

                HashMap<String, String> hashMap=new HashMap<>();
                hashMap.put("name",var);
                hashMap.put("quantity","1");
                String p=price.getText()+"";
                hashMap.put("price",p);
                hashMap.put("image",imageUrl );
                reference.setValue(hashMap);

            }
        });

    }
    private void readUsers() {

        DatabaseReference reference = mDatabase.getReference(Calendar.getInstance().get(Calendar.DAY_OF_WEEK)+ "").child(var);
        DatabaseReference databaseReference=mDatabase.getReference("Products");//.child(mAuth.getCurrentUser().getUid());
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                item1.setText(snapshot.child("1").getValue().toString());
                item2.setText(snapshot.child("2").getValue().toString());

                if(snapshot.getChildrenCount()>5)
                {
                    item3.setText(snapshot.child("3").getValue().toString());

                }
                if(snapshot.getChildrenCount()>6)
                {
                    item4.setText(snapshot.child("4").getValue().toString());

                }
                if(snapshot.getChildrenCount()>7)
                {
                    item5.setText(snapshot.child("5").getValue().toString());
                }
                price.setText(snapshot.child("price").getValue().toString());
                availableQuantity.setText(snapshot.child("availableQuantity")+"");

                Glide.with(LunchActivity.this).load(snapshot.child("image").getValue().toString()).into(comboImage);
                imageUrl=snapshot.child("image").getValue().toString();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
//        reference.child(var).addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
//                    comboItems item = snapshot.getValue(comboItems.class);
//                    availableQuantity.setText(String.valueOf(item.getAvailableQuantity()));
//                    totalQuantity.setText(String.valueOf(item.getTotalQuantity()));
//                    price.setText(String.valueOf(item.getPrice()));
//                    item1.setText(String.valueOf(item.getItem1()));
//                    item2.setText(String.valueOf(item.getItem2()));
//                    item3.setText(String.valueOf(item.getItem3()));
//                    item4.setText(String.valueOf(item.getItem4()));
//                    item5.setText(String.valueOf(item.getItem5()));
//                }
//
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//
//            }
//        });

    }
}