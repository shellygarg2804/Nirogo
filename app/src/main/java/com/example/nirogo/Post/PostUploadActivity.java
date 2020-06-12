package com.example.nirogo.Post;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

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

import com.example.nirogo.Doctor.DetailsDoctor;
import com.example.nirogo.Doctor.DocUploadInfo;
import com.example.nirogo.HomeActivity;
import com.example.nirogo.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.UUID;

public class PostUploadActivity extends Activity {
    ImageView postphoto, camera;
    EditText postDetails;
    TextView submit;
    int Image_Request_Code = 7;

    // Folder path for Firebase Storage.
    String Storage_Path = "";

    // Root Database Name for Firebase Database.
    String Database_Path = "Post/";

    StorageReference storageReference ;
    DatabaseReference databaseReference;
    ProgressDialog progressDialog ;
    Uri FilePathUri;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_upload);

        storageReference = FirebaseStorage.getInstance().getReference();
        // Assign FirebaseDatabase instance with root database name.
        databaseReference = FirebaseDatabase.getInstance().getReference(Database_Path);

        progressDialog  = new ProgressDialog(this);

        postDetails = findViewById(R.id.enterText);
        postphoto = findViewById(R.id.imagePost);
        camera = findViewById(R.id.camera);
        submit = findViewById(R.id.sumbitPost);


        camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Please Select Image"), Image_Request_Code);
            }
        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            if((postDetails.getText().toString()).isEmpty())
            {
                postDetails.setError("Can't be empty");
                return;
            }
            else{
                checkUser();
                String name = getIntent().getStringExtra("name");
                UploadImageFileToFirebaseStorage("dr abc", "Ortho");
            }

            }
        });
    }
    private void checkUser() {
        DatabaseReference databaseReference2;
        databaseReference2 = FirebaseDatabase.getInstance().getReference(Database_Path);
        databaseReference2.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
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
                postphoto.setImageBitmap(bitmap);

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
    public void UploadImageFileToFirebaseStorage(final String name, final String spec) {

        // Checking whether FilePathUri Is empty or not.
        if (FilePathUri != null) {

            // Setting progressDialog Title.
            progressDialog.setTitle("Image is Uploading...");

            // Showing progressDialog.
            progressDialog.show();

            // Creating second StorageReference.
            final StorageReference storageReference2nd = storageReference.child(Storage_Path + System.currentTimeMillis() + "." + GetFileExtension(FilePathUri));

            // Adding addOnSuccess
            storageReference2nd.putFile(FilePathUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                    // Getting image name from EditText and store into string variable.
                    final String det = postDetails.getText().toString();

                    // Hiding the progressDialog after done uploading.
                    progressDialog.dismiss();

                    storageReference2nd.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                        @Override
                        public void onSuccess(Uri uri) {
                            String down = uri.toString();
                            Toast.makeText(getApplicationContext(), down, Toast.LENGTH_LONG).show();
                            String uniqueId = UUID.randomUUID().toString();

                            SimpleDateFormat sdf = new SimpleDateFormat("HH:mm", Locale.getDefault());
                            String currentDateandTime = sdf.format(new Date());

                            PostUploadInfo docUploadInfo = new PostUploadInfo("https://www.hiclipart.com/free-transparent-background-png-clipart-hpiwa",name, spec, det, "abc", currentDateandTime, down);

                            // Getting image upload ID.
                            // Adding image upload id s child element into databaseReference.
                            databaseReference.child(uniqueId).setValue(docUploadInfo);

                            Intent intent = new Intent(PostUploadActivity.this, HomeActivity.class);
                            startActivity(intent);
                                }
                    });

                }
            })
                    // If something goes wrong .
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception exception) {

                            // Hiding the progressDialog.
                            progressDialog.dismiss();

                            // Showing exception erro message.
                            Toast.makeText(getApplicationContext(), exception.getMessage(), Toast.LENGTH_LONG).show();
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
        } else {

            Toast.makeText(getApplicationContext(), "Please Select Image or Add Image Name", Toast.LENGTH_LONG).show();

        }

    }}
