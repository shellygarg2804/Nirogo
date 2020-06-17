package com.example.nirogo.Post;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nirogo.Doctor.DocUploadInfo;
import com.example.nirogo.HomeScreen.HomeActivity;
import com.example.nirogo.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
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

    DatabaseReference databaseReference_fetch;
    StorageReference storageReference ;
    DatabaseReference databaseReference;
    ProgressDialog progressDialog ;
    Uri FilePathUri;
    FirebaseAuth firebaseAuth;

    String docname, docspec, doccity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_upload);

        firebaseAuth= FirebaseAuth.getInstance();
        storageReference = FirebaseStorage.getInstance().getReference();


        final String id = firebaseAuth.getCurrentUser().getUid();
        // Assign FirebaseDatabase instance with root database name.
        databaseReference = FirebaseDatabase.getInstance().getReference(Database_Path);

        progressDialog  = new ProgressDialog(this);

        postDetails = findViewById(R.id.enterText);
        postphoto = findViewById(R.id.imagePost);
        camera = findViewById(R.id.camera);
        submit = findViewById(R.id.submitPost);


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
                final String Database_Path_Fetch = "Doctor/";

                databaseReference_fetch = FirebaseDatabase.getInstance().getReference(Database_Path_Fetch);
                databaseReference_fetch.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                            DocUploadInfo docUploadInfo = postSnapshot.getValue(DocUploadInfo.class);
                            String idCheck = docUploadInfo.getId();
                            if (idCheck.equalsIgnoreCase(id))
                            {
                                String name = docUploadInfo.getName();
                                String spec = docUploadInfo.getSpeciality();
                                String docimage = docUploadInfo.imageURL;
                                UploadImageFileToFirebaseStorage(name, spec, docimage);
                            }
                           }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                        Toast.makeText(getApplicationContext(), "Failed", Toast.LENGTH_SHORT).show();
                    }
                });
            }
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

                Bitmap newBm = getResizedBitmap(bitmap, 300, 300);
                // Setting up bitmap selected image into ImageView.
                postphoto.setImageBitmap(newBm);

            }
            catch (IOException e) {

                e.printStackTrace();
            }
        }
    }


    public Bitmap getResizedBitmap(Bitmap bm, int newHeight, int newWidth) {
        int width = bm.getWidth();
        int height = bm.getHeight();
        float scaleWidth = ((float) newWidth) / width;
        float scaleHeight = ((float) newHeight) / height;

        // create a matrix for the manipulation
        Matrix matrix = new Matrix();

        // resize the bit map
        matrix.postScale(scaleWidth, scaleHeight);

        // recreate the new Bitmap
        Bitmap resizedBitmap = Bitmap.createBitmap(bm, 0, 0, width, height, matrix, false);

        return resizedBitmap;
    }

    public String GetFileExtension(Uri uri) {

        ContentResolver contentResolver = getContentResolver();

        MimeTypeMap mimeTypeMap = MimeTypeMap.getSingleton();

        // Returning the file Extension.
        return mimeTypeMap.getExtensionFromMimeType(contentResolver.getType(uri)) ;

    }

    //uploading Image
    public void UploadImageFileToFirebaseStorage(final String name, final String spec, final String profile) {

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

                            SimpleDateFormat sdf = new SimpleDateFormat("HH:mm", Locale.getDefault());
                            String currentDateandTime = sdf.format(new Date());

                            String id = firebaseAuth.getCurrentUser().getUid();
                            PostUploadInfo docUploadInfo = new PostUploadInfo(profile, name, spec, currentDateandTime, det, down, 4);

                            // Getting image upload ID.
                            // Adding image upload id s child element into databaseReference.
                            databaseReference.child(UUID.randomUUID().toString()).setValue(docUploadInfo);

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
