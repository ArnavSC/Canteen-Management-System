package com.example.canteenmanagementsystem;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.HashMap;

public class AddCombo extends AppCompatActivity {
    EditText name,foodname,foodname1,foodname2,foodname3,foodname4,price,url;
    String name1,price1,url1;
    Button add1,add2;
    FirebaseDatabase firebaseDatabase= FirebaseDatabase.getInstance();
    FirebaseDatabase rootNode;
    DatabaseReference reference;
    StorageReference firebaseStorage = FirebaseStorage.getInstance().getReference();
    ImageButton personImageButton;

    private static final int PICK_IMAGE_REQUEST = 1;
    private Uri mImageUri;
    int gotProfileImage=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_combo);
        name=findViewById(R.id.comboname);
        foodname=findViewById(R.id.foodname);
        foodname1=findViewById(R.id.foodname1);
        foodname2=findViewById(R.id.foodname2);
        foodname3=findViewById(R.id.foodname3);
        foodname4=findViewById(R.id.foodname4);
        personImageButton = findViewById(R.id.personImage);

        price=findViewById(R.id.cost);
        add2=findViewById(R.id.add2);
        rootNode = FirebaseDatabase.getInstance();
        //reference = rootNode.getReference("Combos");

        add2.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {



                name1=name.getText().toString();
                if(name1==null){
                    Toast.makeText(AddCombo.this, "Enter Combo Name", Toast.LENGTH_SHORT).show();
                }
                else if(foodname1.getText().toString()==null){
                    Toast.makeText(AddCombo.this, "Enter Atleast One Food Name", Toast.LENGTH_SHORT).show();
                }
                else if(price.getText().toString()==null){
                    Toast.makeText(AddCombo.this, "Enter Price", Toast.LENGTH_SHORT).show();}
                else{
                    check();
                }
            }
        });
    }

    private void check() {

        final DatabaseReference combo = firebaseDatabase.getReference("Products");
        combo.addValueEventListener(new ValueEventListener() {
            int a=0,b=0,c=0;
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                if (foodname.getText().toString().equalsIgnoreCase("1")) {b++;}else{


                    if (snapshot.hasChild(foodname.getText().toString())) {c++;
                    }
                    else
                    {a++;
                        Toast.makeText(AddCombo.this,"This Product dosent Exist",Toast.LENGTH_SHORT).show();}
                }
                if (foodname1.getText().toString().equalsIgnoreCase("2")) {b++;}else{


                    if (snapshot.hasChild(foodname1.getText().toString())) {c++;
                    }
                    else
                    {a++;
                        Toast.makeText(AddCombo.this,"This Product dosent Exist",Toast.LENGTH_SHORT).show();}
                }
                if (foodname2.getText().toString().equalsIgnoreCase("3")) {b++;}else{


                    if (snapshot.hasChild(foodname2.getText().toString())) {c++;
                    }
                    else
                    {a++;
                        Toast.makeText(AddCombo.this,"This Product dosent Exist",Toast.LENGTH_SHORT).show();}
                }

                if (foodname3.getText().toString().equalsIgnoreCase("4")) {b++;}else{


                    if (snapshot.hasChild(foodname3.getText().toString())) {c++;
                    }
                    else
                    {a++;
                        Toast.makeText(AddCombo.this,"This Product dosent Exist",Toast.LENGTH_SHORT).show();}
                }

                if (foodname4.getText().toString().equalsIgnoreCase("5")) {b++;}else{


                    if (snapshot.hasChild(foodname4.getText().toString())) {c++;
                    }
                    else
                    {a++;
                        Toast.makeText(AddCombo.this,"This Product dosent Exist",Toast.LENGTH_SHORT).show();}
                }

                Log.d("check1"," "+a+" "+b+" "+c);
                Log.d("hello","hi  "+foodname2.getText().toString());
                if(a!=0||b!=5){adddata(name1,c);

                }
                else{
                    Toast.makeText(AddCombo.this,"Enter Details Correctly",Toast.LENGTH_SHORT).show();
                }

            }


            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

    private void adddata( String n, final int b) {
        final DatabaseReference combo = firebaseDatabase.getReference("Combos");
        final String na=n;
        combo.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Log.d("switch"," "+b);
                switch (b){
                    case 1:{
                        reference=FirebaseDatabase.getInstance().getReference("Combos").child(na);
                        HashMap<String,String> hashMap=new HashMap<>();
                        hashMap.put("1",foodname.getText().toString());
                        hashMap.put("price",price.getText().toString());
                        reference.setValue(hashMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                Log.d("donedone","donedone");

                            }
                        });

                        break;}
                    case 2:{
                        reference=FirebaseDatabase.getInstance().getReference("Combos").child(na);
                        HashMap<String,String>hashMap=new HashMap<>();
                        hashMap.put("1",foodname.getText().toString());
                        hashMap.put("2",foodname1.getText().toString());
                        hashMap.put("price",price.getText().toString());
                        reference.setValue(hashMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                Log.d("donedone","donedone");

                            }
                        });
                        break;}
                    case 3:{
                        reference=FirebaseDatabase.getInstance().getReference("Combos").child(na);
                        HashMap<String,String>hashMap=new HashMap<>();
                        hashMap.put("1",foodname.getText().toString());
                        hashMap.put("2",foodname1.getText().toString());
                        hashMap.put("3",foodname2.getText().toString());
                        hashMap.put("price",price.getText().toString());
                        reference.setValue(hashMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                Log.d("donedone","donedone");

                            }
                        });
                        break;}
                    case 4:{
                        reference=FirebaseDatabase.getInstance().getReference("Combos").child(na);
                        HashMap<String,String>hashMap=new HashMap<>();
                        hashMap.put("1",foodname.getText().toString());
                        hashMap.put("2",foodname1.getText().toString());
                        hashMap.put("3",foodname2.getText().toString());
                        hashMap.put("4",foodname3.getText().toString());
                        hashMap.put("price",price.getText().toString());
                        reference.setValue(hashMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                Log.d("donedone","donedone");

                            }
                        });
                        break;}
                    case 5:{
                        reference=FirebaseDatabase.getInstance().getReference("Combos").child(na);
                        HashMap<String,String>hashMap=new HashMap<>();
                        hashMap.put("1",foodname.getText().toString());
                        hashMap.put("2",foodname1.getText().toString());
                        hashMap.put("3",foodname2.getText().toString());
                        hashMap.put("4",foodname3.getText().toString());
                        hashMap.put("5",foodname4.getText().toString());
                        hashMap.put("price",price.getText().toString());
                        reference.setValue(hashMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                Log.d("donedone","donedone");

                            }
                        });
                        break;}
                    default:

                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public void selectImage(View view) {
        openFileChooser();
    }

    private void openFileChooser() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, PICK_IMAGE_REQUEST);
    }

    public void sendImage(View view) {
        if(gotProfileImage==0){
            Toast.makeText(getApplicationContext(),"Please Add Image",Toast.LENGTH_SHORT).show();
            return;
        }
        uploadImage();
    }

    void uploadImage(){
        final StorageReference mStorageRef = firebaseStorage.child("personImage");
        mStorageRef.putFile(mImageUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                mStorageRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {
                        DatabaseReference personDetails = firebaseDatabase.getReference("personImage");
                        personDetails.child("imageUrl").setValue(uri.toString());
                        Toast.makeText(getApplicationContext(),"Performed Successfully",Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && data != null && data.getData() != null) {
            mImageUri = data.getData();
            Toast.makeText(getApplicationContext(), "Got Succesfully", Toast.LENGTH_SHORT).show();
            personImageButton.setImageURI(mImageUri);
            gotProfileImage = 1;
        } else {
            gotProfileImage=0;
            mImageUri = Uri.parse(ContentResolver.SCHEME_ANDROID_RESOURCE +
                    "://" + getResources().getResourcePackageName(R.drawable.user_font)
                    + '/' + getResources().getResourceTypeName(R.drawable.user_font) + '/' + getResources().getResourceEntryName(R.drawable.user_font));
            Toast.makeText(getApplicationContext(), "Not Got", Toast.LENGTH_SHORT).show();
        }
    }
}