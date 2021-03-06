package com.example.canteenmanagementsystem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class DisplayItems extends AppCompatActivity {

    private ListView listView;

    public ItemArrayAdapter itemArrayAdapter;

    FirebaseDatabase mDatabase;
    ArrayList<items> mItems=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_items);

        listView = findViewById(R.id.itemList);

        mDatabase = FirebaseDatabase.getInstance();
        itemArrayAdapter= new ItemArrayAdapter(this,R.layout.activity_item_array_adapter,mItems);

        readUsers();
    }
    private void readUsers() {
        DatabaseReference reference = mDatabase.getReference("Products");//.child(mAuth.getCurrentUser().getUid());

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                mItems.clear();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    String u = "" + snapshot.getValue();
                    items item = snapshot.getValue(items.class);
                    Log.d("That's",u);
                    assert item!=null;
                    //Log.d("PicFromFrag",item.getPicture()+"  "+item.getUsername()+"  "+item.getId());
                    mItems.add(item);
                }
                listView.setAdapter(itemArrayAdapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}