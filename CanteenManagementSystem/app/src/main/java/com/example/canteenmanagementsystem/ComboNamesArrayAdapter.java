package com.example.canteenmanagementsystem;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class ComboNamesArrayAdapter extends ArrayAdapter {
    public ComboNamesArrayAdapter(Context context, List<Combolistclass> resource) {
        super(context, 0, resource);

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertview, @NonNull ViewGroup parent)
    {

        View listItemView=convertview;
        if (listItemView == null){
            listItemView= LayoutInflater.from(getContext()).inflate(
                    R.layout.activity_combo_names_array_adapter,parent,false);
        }
        Combolistclass fc= (Combolistclass) getItem(position);
        Log.d("TAG", "getView: "+position);

        TextView textView=listItemView.findViewById(R.id.CombonameListAdapter);

        Log.d("FoodNamesadapter", fc.name);

        textView.setText(fc.name);

        return listItemView;

    }
}