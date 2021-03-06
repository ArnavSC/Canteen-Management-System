package com.example.canteenmanagementsystem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ComboMenu extends AppCompatActivity {
    String det,a="Combos";
    TextView title;
    ListView listView;
    List<Combolistclass> Tname=new ArrayList<Combolistclass>();
    FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    Task<Void> reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_combo_menu);
        listView=findViewById(R.id.listview);
        Intent in=getIntent();
        det=in.getStringExtra("det");
        title=findViewById(R.id.headline);
        title.setText(det);
        if (det.equalsIgnoreCase("Product Delete"))
            a="Products";
        if (det.equalsIgnoreCase("Edit Products"))
            a="Products";
        final DatabaseReference combodetails = firebaseDatabase.getReference(a);
        combodetails.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                DataSnapshot lastDataSnapshot = null;
                Iterable<DataSnapshot> iterable = snapshot.getChildren();
                for (Iterator iterator = iterable.iterator(); iterator.hasNext(); ) {
                    lastDataSnapshot = (DataSnapshot) iterator.next();
                    Log.d("userdets", lastDataSnapshot.getKey());
                    Combolistclass t = new Combolistclass(lastDataSnapshot.getKey());
                    Tname.add(t);

                }

                Asynchronous asynk=new Asynchronous();
                asynk.execute();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private class Asynchronous extends AsyncTask<String ,Void,List<Combolistclass>> {
        @Override
        protected List<Combolistclass> doInBackground(String... strings) {
            return Tname;
        }

        @Override
        protected void onPostExecute(List<Combolistclass> Combolistclasses) {
            Log.d("Async" , "InAsynchronous");
            final ComboNamesArrayAdapter cityadapter=new  ComboNamesArrayAdapter(ComboMenu.this , Combolistclasses);
            listView.setAdapter((ListAdapter) cityadapter);
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Combolistclass f=(Combolistclass) cityadapter.getItem(position);
                    if(((det.equalsIgnoreCase("Click On the Name To Delete"))|| (det.equalsIgnoreCase("Product Delete"))))
                    {
                        reference=FirebaseDatabase.getInstance().getReference(a).child(f.name).removeValue();
                        // Log.d("delete", String.valueOf(reference.setValue(null)));
                    }
                    if(det.equalsIgnoreCase("Edit")){
                        Intent in=new Intent(ComboMenu.this,ComboDisplay.class);
                        in.putExtra("comboname",f.name);
                        startActivity(in);
                    }
                    if (det.equalsIgnoreCase("Edit Products")){
                        Intent in=new Intent(ComboMenu.this,ProductDisplay.class);
                        in.putExtra("prodname",f.name);
                        startActivity(in);
                    }
                }
            });
        }
    }
}