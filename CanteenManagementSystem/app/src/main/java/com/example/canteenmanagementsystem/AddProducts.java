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
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.HashMap;

public class AddProducts extends AppCompatActivity {
    EditText name,foodname,foodname1,foodname2,foodname3,foodname4,price,url;
    FirebaseDatabase firebaseDatabase= FirebaseDatabase.getInstance();
    FirebaseDatabase rootNode;
    Button add2;
    String name1;
    DatabaseReference reference;
    StorageReference firebaseStorage = FirebaseStorage.getInstance().getReference();
    ImageButton personImageButton;

    private static final int PICK_IMAGE_REQUEST = 1;
    private Uri mImageUri;
    int gotProfileImage=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_products);
        name=findViewById(R.id.comboname);//productname
        foodname=findViewById(R.id.foodname);//quantity
        foodname1=findViewById(R.id.foodname1);//availablequantity
        price=findViewById(R.id.cost);
        url=findViewById(R.id.url);
        add2=findViewById(R.id.add2);
        rootNode = FirebaseDatabase.getInstance();
        add2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                name1=name.getText().toString();
                if(name1==null){
                    Toast.makeText(AddProducts.this, "Enter Product Name", Toast.LENGTH_SHORT).show();
                }
                else if(foodname.getText().toString()==null){
                    Toast.makeText(AddProducts.this, "Enter Total Quantity", Toast.LENGTH_SHORT).show();
                }
                else if(foodname1.getText().toString()==null){
                    Toast.makeText(AddProducts.this, "Enter Available Quantity", Toast.LENGTH_SHORT).show();
                }
                else if(price.getText().toString()==null){
                    Toast.makeText(AddProducts.this, "Enter Price", Toast.LENGTH_SHORT).show();}
                else if(url.getText().toString()==null){
                    Toast.makeText(AddProducts.this, "Enter Url", Toast.LENGTH_SHORT).show();}
                else{
                    load();
                }
            }
        });
    }

    private void load() {
        reference=FirebaseDatabase.getInstance().getReference("Products").child(name1);
        HashMap<String,String> hashMap=new HashMap<>();
        hashMap.put("totalQuantity",foodname.getText().toString());
        hashMap.put("availableQuantity","0");
        hashMap.put("price",price.getText().toString());
        hashMap.put("image",url.getText().toString());
        hashMap.put("name",name1);
        reference.setValue(hashMap).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                Log.d("donedone","donedone");

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