package com.example.canteenmanagementsystem;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.HashMap;

public class ItemArrayAdapter extends ArrayAdapter<items> {
    private Context mContext;
    int mResource;
    Button button,reduce,addToCart;

    public ItemArrayAdapter(@NonNull Context context, int resource, @NonNull ArrayList<items> objects) {
        super(context, resource, objects);
        mContext=context;
        mResource=resource;

    }
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        String price,availableQuantity,totalQuantity;
        String image,name;

        price = getItem(position).getPrice();
        availableQuantity = getItem(position).getAvailableQuantity();
        totalQuantity = getItem(position).getTotalQuantity();
        image = getItem(position).getImage();
        name = getItem(position).getName();


        items person = new items(price, availableQuantity, totalQuantity, image, name);

        LayoutInflater inflater = LayoutInflater.from(mContext);
        convertView = inflater.inflate(mResource, parent, false);
        TextView tvPrice = (TextView) convertView.findViewById(R.id.price);
        TextView tvAvailableQuantity = (TextView) convertView.findViewById(R.id.availableQuantity);
        TextView tvTotalQuantity = (TextView) convertView.findViewById(R.id.totalQuantity);
        TextView tvName= (TextView) convertView.findViewById(R.id.name);
        ImageView imageView = convertView.findViewById(R.id.image);


        tvPrice.setText(String.valueOf(price));
        tvAvailableQuantity.setText(String.valueOf(availableQuantity));
        tvTotalQuantity.setText(String.valueOf(totalQuantity));
        tvName.setText(name);
        Glide.with(mContext).load(image).into(imageView);

        TextView tv_quantity= (TextView) convertView.findViewById(R.id.quantity);
        button = convertView.findViewById(R.id.add);
        addToCart=convertView.findViewById(R.id.addToCart);
        reduce = convertView.findViewById(R.id.remove);
        final int[] i = {0};
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                i[0] = i[0] +1;
                tv_quantity.setText(String.valueOf(i[0]));
                getItem(position).setQuantity(i[0]);
            }
        });
        reduce.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(i[0]>0)
                {i[0] = i[0] - 1;}
                tv_quantity.setText(String.valueOf(i[0]));
                getItem(position).setQuantity(i[0]);
            }
        });
        addToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatabaseReference reference;

                reference = (DatabaseReference) FirebaseDatabase.getInstance().getReference("cart").child("Jayesh").child(name);

                HashMap<String, String> hashMap=new HashMap<>();
                hashMap.put("name",name);
                hashMap.put("quantity",i[0]+"");
                int p = Integer.parseInt(price)*i[0];
                hashMap.put("price",p+"");
                hashMap.put("image", image);
                reference.setValue(hashMap);
            }
        });

        return convertView;
    }
}