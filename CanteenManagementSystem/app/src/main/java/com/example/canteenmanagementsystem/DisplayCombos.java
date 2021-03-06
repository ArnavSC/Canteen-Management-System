package com.example.canteenmanagementsystem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
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

import java.util.HashMap;

public class DisplayCombos extends AppCompatActivity {
    private ListView listView;

    public ItemArrayAdapter itemArrayAdapter;
    String username;

    FirebaseDatabase mDatabase;
    String var;
    String imageUrl;
    ImageView comboImage;
    Button addToCart;
    TextView availableQuantity, name, price, item1,item2,item3,item4,item5,q1,q2,q3,q4,q5;

    final int[] available = {0};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_combos);


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
        q1=findViewById(R.id.q1);
        q2=findViewById(R.id.q2);
        q3=findViewById(R.id.q3);
        q4=findViewById(R.id.q4);
        q5=findViewById(R.id.q5);
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

        DatabaseReference reference = mDatabase.getReference("Combos").child(var);
        DatabaseReference databaseReference=mDatabase.getReference("Products");//.child(mAuth.getCurrentUser().getUid());
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                item1.setText(snapshot.child("1").getValue().toString());
                databaseReference.child(snapshot.child("1").getValue().toString()).addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dsnapshot) {
                        available[0]=Integer.parseInt((dsnapshot.child("availableQuantity")).getValue().toString());
                        Log.d("cesspool",available[0]+"");
                        q1.setText(available[0]+"");
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

                item2.setText(snapshot.child("2").getValue().toString());
                databaseReference.child(snapshot.child("2").getValue().toString()).addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dsnapshot) {
                        available[0]=Integer.parseInt((dsnapshot.child("availableQuantity")).getValue().toString());
                        Log.d("cesspool",available[0]+"");
                        q2.setText(available[0]+"");
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
                if(snapshot.getChildrenCount()>6)
                {
                    item3.setText(snapshot.child("3").getValue().toString());
                    databaseReference.child(snapshot.child("3").getValue().toString()).addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dsnapshot) {
                            available[0]=Integer.parseInt((dsnapshot.child("availableQuantity")).getValue().toString());
                            Log.d("cesspool",available[0]+"");
                            q3.setText(available[0]+"");
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });
                }
                if(snapshot.getChildrenCount()>7)
                {
                    item4.setText(snapshot.child("4").getValue().toString());
                    databaseReference.child(snapshot.child("4").getValue().toString()).addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dsnapshot) {
                            available[0]=Integer.parseInt((dsnapshot.child("availableQuantity")).getValue().toString());
                            Log.d("cesspool",available[0]+"");
                            q4.setText(available[0]+"");
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });
                }
                if(snapshot.getChildrenCount()>8)
                {
                    item5.setText(snapshot.child("5").getValue().toString());
                    databaseReference.child(snapshot.child("5").getValue().toString()).addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dsnapshot) {
                            available[0]=Integer.parseInt((dsnapshot.child("availableQuantity")).getValue().toString());
                            Log.d("cesspool",available[0]+"");
                            q5.setText(available[0]+"");
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });
                }
                price.setText(snapshot.child("price").getValue().toString());
                Glide.with(DisplayCombos.this).load(snapshot.child("image").getValue().toString()).into(comboImage);
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
    void getQuantity(DatabaseReference databaseReference,String item)
    {

        databaseReference.child(item).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dsnapshot) {
                available[0]=Integer.parseInt((dsnapshot.child("availableQuantity")).getValue().toString());
                Log.d("cesspool",available[0]+"");
                q1.setText(available[0]+"");
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}