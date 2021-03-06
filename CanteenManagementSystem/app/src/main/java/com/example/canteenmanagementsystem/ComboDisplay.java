package com.example.canteenmanagementsystem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class ComboDisplay extends AppCompatActivity {
    private ListView listView;

    FirebaseDatabase mDatabase;
    String var;
    ImageView comboImage;
    EditText name, price, item1,item2,item3,item4,item5;
    final int[] available = {0,0,0,0,0};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_combo_display);
        Intent in=getIntent();
        var=in.getStringExtra("comboname");
        mDatabase = FirebaseDatabase.getInstance();

        price=findViewById(R.id.price);
        comboImage=findViewById(R.id.comboImage);
        item1=findViewById(R.id.item1);
        item2=findViewById(R.id.item2);
        item3=findViewById(R.id.item3);
        item4=findViewById(R.id.item4);
        item5=findViewById(R.id.item5);
        name=findViewById(R.id.name);
        name.setText(var);
        readUsers();
    }
    private void readUsers() {

        final DatabaseReference reference = mDatabase.getReference("Combos").child(var);
        //final DatabaseReference databaseReference=mDatabase.getReference("Products");//.child(mAuth.getCurrentUser().getUid());
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                item1.setText(snapshot.child("1").getValue().toString());
                Log.d("hiee"," "+snapshot.getChildrenCount());
                if(snapshot.getChildrenCount()==(long)4)
                {
                    item2.setText(snapshot.child("2").getValue().toString());
                }
                else
                    item2.setText(null);
                if(snapshot.getChildrenCount()==(long)5)
                {
                    item3.setText(snapshot.child("3").getValue().toString());
                }else
                    item3.setText(null);
                if(snapshot.getChildrenCount()==(long)6)
                {
                    item4.setText(snapshot.child("4").getValue().toString());
                }else
                    item4.setText(null);
                if(snapshot.getChildrenCount()==(long)7)
                {
                    item5.setText(snapshot.child("5").getValue().toString());
                }else
                    item5.setText(null);
                price.setText(snapshot.child("price").getValue().toString());
                //Glide.with(ComboDisplay.this).load(snapshot.child("image").getValue().toString()).into(comboImage);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
    DatabaseReference reference;

    public void edit(View view) {
        Log.d("edit",var);
        if(price.getText().toString().equalsIgnoreCase(null)){
            Toast.makeText(ComboDisplay.this,"Enter Price",Toast.LENGTH_SHORT).show();
        }
        else {
            reference = FirebaseDatabase.getInstance().getReference("Combos").child(var);
            HashMap<String, String> hashMap = new HashMap<>();
            if (item1.getText().toString()!=null)
                hashMap.put("1", item1.getText().toString());
            if (item2.getText().toString()!=null)
                hashMap.put("2", item2.getText().toString());
            if (item3.getText().toString()!=null)
                hashMap.put("3", item3.getText().toString());
            if (item4.getText().toString()!=null)
                hashMap.put("4", item4.getText().toString());
            if (item5.getText().toString()!=null)
                hashMap.put("5", item5.getText().toString());
            hashMap.put("price", price.getText().toString());
            reference.setValue(hashMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    Log.d("donedone","donedone");

                }
            });
        }
    }
}