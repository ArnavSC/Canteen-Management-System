package com.example.canteenmanagementsystem;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class CartAdapter extends ArrayAdapter<cartItems> {
    private Context mContext;
    int mResource;

    public CartAdapter(@NonNull Context context, int resource, @NonNull ArrayList<cartItems> objects) {
        super(context, resource, objects);
        mContext=context;
        mResource=resource;
    }
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {


        String image, name, price, quantity;

        price = getItem(position).getPrice();
        name = getItem(position).getName();
        quantity = getItem(position).getQuantity();
        image = getItem(position).getImage();

        cartItems person = new cartItems(quantity, image, name, price);

        LayoutInflater inflater = LayoutInflater.from(mContext);
        convertView = inflater.inflate(mResource, parent, false);
        TextView tvPrice = (TextView) convertView.findViewById(R.id.price);
        TextView tvQuantity = (TextView) convertView.findViewById(R.id.quantity);
        ;
        TextView tvName = (TextView) convertView.findViewById(R.id.name);
        ImageView imageView = convertView.findViewById(R.id.image);


        tvPrice.setText(String.valueOf(price));
        tvQuantity.setText(String.valueOf(quantity));
        tvName.setText(name);
        Glide.with(mContext).load(image).into(imageView);

        TextView tv_quantity = (TextView) convertView.findViewById(R.id.quantity);
        return convertView;
    }
}