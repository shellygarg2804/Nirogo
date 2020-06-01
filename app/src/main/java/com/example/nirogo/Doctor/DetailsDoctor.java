package com.example.nirogo.Doctor;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nirogo.HomeActivity;
import com.example.nirogo.R;
import com.example.nirogo.Supplier.DetailsSupplier;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.IOException;
import java.util.UUID;

public class DetailsDoctor extends Activity {

    // Folder path for Firebase Storage.
    String Storage_Path = "";

    // Root Database Name for Firebase Database.
    String Database_Path = "Doctor/";

    Uri FilePathUri;

    StorageReference storageReference ;
    DatabaseReference databaseReference;

    int Image_Request_Code = 7;
    ProgressDialog progressDialog ;

    private static final int CAMERA_REQUEST = 1888;

    String name, age, speciality, city;

    EditText nameIn, ageIn, specIn, cityIn;
    ImageView cameraBut, cameraDisp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_doctor);

        storageReference = FirebaseStorage.getInstance().getReference();

        // Assign FirebaseDatabase instance with root database name.
        databaseReference = FirebaseDatabase.getInstance().getReference(Database_Path);

        checkUser();
        nameIn = findViewById(R.id.nameDoc);
        ageIn = findViewById(R.id.ageDoc);
        specIn = findViewById(R.id.specDoc);
        cityIn = findViewById(R.id.cityDoc);

        cameraBut = findViewById(R.id.imageButton);
        cameraDisp = findViewById(R.id.imageDisp);

        progressDialog  = new ProgressDialog(this);

        cameraBut.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
//            Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//            startActivityForResult(cameraIntent, CAMERA_REQUEST);
            Intent intent = new Intent();
            intent.setType("image/*");
            intent.setAction(Intent.ACTION_GET_CONTENT);
            startActivityForResult(Intent.createChooser(intent, "Please Select Image"), Image_Request_Code);

             }
    });

        TextView submit  = findViewById(R.id.sumbitDoc);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UploadImageFileToFirebaseStorage();
            }
        });
    }

    private void checkUser() {
        DatabaseReference databaseReference2;
        databaseReference2 = FirebaseDatabase.getInstance().getReference(Database_Path);
        databaseReference2.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
//                if (id.equals(UUID.randomUUID().toString()))
//                    Toast.makeText(DetailsDoctor.this, "Same User", Toast.LENGTH_LONG).show();
//
//                else  Toast.makeText(DetailsDoctor.this, "New User", Toast.LENGTH_LONG).show();

            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == Image_Request_Code && resultCode == RESULT_OK && data != null && data.getData() != null) {

            FilePathUri = data.getData();

            try {

                // Getting selected image into Bitmap.
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), FilePathUri);

                // Setting up bitmap selected image into ImageView.
                cameraDisp.setImageBitmap(bitmap);

            }
            catch (IOException e) {

                e.printStackTrace();
            }
        }
    }

    public String GetFileExtension(Uri uri) {

        ContentResolver contentResolver = getContentResolver();

        MimeTypeMap mimeTypeMap = MimeTypeMap.getSingleton();

        // Returning the file Extension.
        return mimeTypeMap.getExtensionFromMimeType(contentResolver.getType(uri)) ;

    }

    //uploading Image
    public void UploadImageFileToFirebaseStorage() {

        // Checking whether FilePathUri Is empty or not.
        if (FilePathUri != null) {

            // Setting progressDialog Title.
            progressDialog.setTitle("Image is Uploading...");

            // Showing progressDialog.
            progressDialog.show();

            // Creating second StorageReference.
            final StorageReference storageReference2nd = storageReference.child(Storage_Path + System.currentTimeMillis() + "." + GetFileExtension(FilePathUri));

            // Adding addOnSuccessListener to second StorageReference.
            storageReference2nd.putFile(FilePathUri)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                            // Getting image name from EditText and store into string variable.
                            name = nameIn.getText().toString();
                            age = ageIn.getText().toString();
                            speciality = specIn.getText().toString();
                            city = cityIn.getText().toString();

                            // Hiding the progressDialog after done uploading.
                            progressDialog.dismiss();

                            // Showing toast message after done uploading.
                            Toast.makeText(getApplicationContext(), "Image Uploaded Successfully ", Toast.LENGTH_LONG).show();

                            String uniqueId = UUID.randomUUID().toString();
                            @SuppressWarnings("VisibleForTests")
                            DocUploadInfo docUploadInfo = new DocUploadInfo(uniqueId,"Doctor",name, storageReference2nd.getDownloadUrl().toString(), age, city, speciality);

                            // Getting image upload ID.
                            String ImageUploadId = databaseReference.push().getKey();

                            // Adding image upload id s child element into databaseReference.
                            databaseReference.child(ImageUploadId).setValue(docUploadInfo);

                            Intent intent = new Intent(DetailsDoctor.this, HomeActivity.class);
                            intent.putExtra("type","Doctor");
                            startActivity(intent);
                        }
                    })
                    // If something goes wrong .
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception exception) {

                            // Hiding the progressDialog.
                            progressDialog.dismiss();

                            // Showing exception erro message.
                            Toast.makeText(DetailsDoctor.this, exception.getMessage(), Toast.LENGTH_LONG).show();
                        }
                    })

                    // On progress change upload time.
                    .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {

                            // Setting progressDialog Title.
                            progressDialog.setTitle("Uploading Data");

                        }
                    });
        }
        else {

            Toast.makeText(DetailsDoctor.this, "Please Select Image or Add Image Name", Toast.LENGTH_LONG).show();

        }
    }

}

