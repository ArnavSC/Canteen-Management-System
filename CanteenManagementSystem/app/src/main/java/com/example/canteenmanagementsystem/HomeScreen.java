package com.example.canteenmanagementsystem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Iterator;

public class HomeScreen extends AppCompatActivity {

    RelativeLayout rl[] = new RelativeLayout[6];

    TextView t[] = new TextView[6];
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);

        rl[0] = findViewById(R.id.rl1);
        rl[1] = findViewById(R.id.rl2);
        rl[2] = findViewById(R.id.rl3);
        rl[3] = findViewById(R.id.rl4);
        rl[4] = findViewById(R.id.rl5);
        rl[5] = findViewById(R.id.rl6);

        t[0] = findViewById(R.id.textView1);
        t[1] = findViewById(R.id.textView2);
        t[2] = findViewById(R.id.textView3);
        t[3] = findViewById(R.id.textView4);
        t[4] = findViewById(R.id.textView5);
        t[5] = findViewById(R.id.textView6);

        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Combos");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                final int i = 0;
                final String arr[] = new String[6];
                DataSnapshot lastDataSnapshot = null;
                Iterable<DataSnapshot> iterable = dataSnapshot.getChildren();
                for (Iterator iterator = iterable.iterator(); iterator.hasNext(); ) {
                    lastDataSnapshot = (DataSnapshot) iterator.next();
                    Log.d("userdets", lastDataSnapshot.getKey());
                    Combolistclass t = new Combolistclass(lastDataSnapshot.getKey());
                    arr[i] = lastDataSnapshot.getKey();

                }

                t[0].setText(arr[0]);
                t[1].setText(arr[1]);
                t[2].setText(arr[2]);
                t[3].setText(arr[3]);
                t[4].setText(arr[4]);
                t[5].setText(arr[5]);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

    public void customizeMeal(View view) {
        startActivity(new Intent(getApplicationContext(), DisplayItems.class));
    }

    public void callOrderDetails(View view) {
    }

    public void callPayment(View view) {
        startActivity(new Intent(getApplicationContext(), Payment.class));
    }

    public void callCart(View view) {
        startActivity(new Intent(getApplicationContext(), CartActivity.class));
    }
}