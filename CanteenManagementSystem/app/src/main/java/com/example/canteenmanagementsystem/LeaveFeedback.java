package com.example.canteenmanagementsystem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class LeaveFeedback extends AppCompatActivity {
    static int rating;
    String name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leave_feedback);

        Intent intent = getIntent();
        this.name=intent.getStringExtra("NAME");//this should be the name of the user who has logged in
        rating = 0;

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Feedback");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.hasChild(name)) {
                    // run some code
                    int rating = snapshot.child(name).child("rating").getValue(int.class);
                    LeaveFeedback.rating=rating;
                    updateRating();
                    Log.d("TAG3", "User found");
                    EditText editText = findViewById(R.id.editTextTextMultiLine);
                    editText.setText(snapshot.child(name).child("review").getValue(String.class));

                    Button button = findViewById(R.id.delete);
                    button.setVisibility(View.VISIBLE);

                }
                else
                    Log.d("TAG4", "User not found");
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.d("TAG5", "Error");
            }
        });

    }

    void updateRating(){
        //to update stars if rating exists
        if(rating>=1) {
            ImageView star1 = findViewById(R.id.star1);
            star1.setImageResource(R.drawable.btn_star_big_on);
        }
        if(rating>=2) {
            ImageView star2 = findViewById(R.id.star2);
            star2.setImageResource(R.drawable.btn_star_big_on);
        }
        if(rating>=3) {
            ImageView star3 = findViewById(R.id.star3);
            star3.setImageResource(R.drawable.btn_star_big_on);
        }
        if(rating>=4) {
            ImageView star4 = findViewById(R.id.star4);
            star4.setImageResource(R.drawable.btn_star_big_on);
        }
        if(rating>=5) {
            ImageView star5 = findViewById(R.id.star5);
            star5.setImageResource(R.drawable.btn_star_big_on);
        }
    }

    public void star1(View view) {
        ImageView star1 = findViewById(R.id.star1);
        star1.setImageResource(R.drawable.btn_star_big_on);
        ImageView star2 = findViewById(R.id.star2);
        star2.setImageResource(R.drawable.btn_star_big_off);
        ImageView star3 = findViewById(R.id.star3);
        star3.setImageResource(R.drawable.btn_star_big_off);
        ImageView star4 = findViewById(R.id.star4);
        star4.setImageResource(R.drawable.btn_star_big_off);
        ImageView star5 = findViewById(R.id.star5);
        star5.setImageResource(R.drawable.btn_star_big_off);
        rating =1;

    }

    public void star2(View view) {
        ImageView star1 = findViewById(R.id.star1);
        star1.setImageResource(R.drawable.btn_star_big_on);
        ImageView star2 = findViewById(R.id.star2);
        star2.setImageResource(R.drawable.btn_star_big_on);
        ImageView star3 = findViewById(R.id.star3);
        star3.setImageResource(R.drawable.btn_star_big_off);
        ImageView star4 = findViewById(R.id.star4);
        star4.setImageResource(R.drawable.btn_star_big_off);
        ImageView star5 = findViewById(R.id.star5);
        star5.setImageResource(R.drawable.btn_star_big_off);
        rating =2;
    }

    public void star3(View view) {
        ImageView star1 = findViewById(R.id.star1);
        star1.setImageResource(R.drawable.btn_star_big_on);
        ImageView star2 = findViewById(R.id.star2);
        star2.setImageResource(R.drawable.btn_star_big_on);
        ImageView star3 = findViewById(R.id.star3);
        star3.setImageResource(R.drawable.btn_star_big_on);
        ImageView star4 = findViewById(R.id.star4);
        star4.setImageResource(R.drawable.btn_star_big_off);
        ImageView star5 = findViewById(R.id.star5);
        star5.setImageResource(R.drawable.btn_star_big_off);
        rating =3;
    }

    public void star4(View view) {
        ImageView star1 = findViewById(R.id.star1);
        star1.setImageResource(R.drawable.btn_star_big_on);
        ImageView star2 = findViewById(R.id.star2);
        star2.setImageResource(R.drawable.btn_star_big_on);
        ImageView star3 = findViewById(R.id.star3);
        star3.setImageResource(R.drawable.btn_star_big_on);
        ImageView star4 = findViewById(R.id.star4);
        star4.setImageResource(R.drawable.btn_star_big_on);
        ImageView star5 = findViewById(R.id.star5);
        star5.setImageResource(R.drawable.btn_star_big_off);
        rating =4;
    }

    public void star5(View view) {
        ImageView star1 = findViewById(R.id.star1);
        star1.setImageResource(R.drawable.btn_star_big_on);
        ImageView star2 = findViewById(R.id.star2);
        star2.setImageResource(R.drawable.btn_star_big_on);
        ImageView star3 = findViewById(R.id.star3);
        star3.setImageResource(R.drawable.btn_star_big_on);
        ImageView star4 = findViewById(R.id.star4);
        star4.setImageResource(R.drawable.btn_star_big_on);
        ImageView star5 = findViewById(R.id.star5);
        star5.setImageResource(R.drawable.btn_star_big_on);
        rating =5;
    }

    public void back(View view) {
        super.finish();
    }

    public void post(View view) {
        TextView textView = findViewById(R.id.textViewError);
        textView.setText("");
        boolean noMistakes=true;
        if(rating==0) {
            textView.append("Rating not given, please give rating\n");
            noMistakes = false;
        }
        EditText editText = findViewById(R.id.editTextTextMultiLine);
        String review = editText.getText().toString();
        int reviewMaxLength=100;
        if(review.length()>reviewMaxLength) {
            textView.append("Review cannot be more than " + reviewMaxLength + " characters\n");
            noMistakes = false;
        }

        if(noMistakes){

            Feedback feedback = new Feedback();
            feedback.setRating(rating);
            feedback.setReview(editText.getText().toString());

            DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Feedback");
            databaseReference.child(name).setValue(feedback);

            Toast.makeText(this, "SUCCESS", Toast.LENGTH_SHORT).show();
            super.finish();
        }


    }

    public void delete(View view) {
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Feedback");
        databaseReference.child(name).removeValue();
        Toast.makeText(this, "DELETED", Toast.LENGTH_SHORT).show();
        super.finish();
    }
}