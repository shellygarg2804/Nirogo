package com.example.nirogo.User;

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

import androidx.annotation.NonNull;

import com.example.nirogo.Doctor.DetailsDoctor;
import com.example.nirogo.Doctor.DocUploadInfo;
import com.example.nirogo.HomeActivity;
import com.example.nirogo.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.IOException;
import java.util.UUID;

public class DetailsUser extends Activity {

    // Folder path for Firebase Storage.
    String Storage_Path = "";

    // Root Database Name for Firebase Database.
    String Database_Path = "User/";

    Uri FilePathUri;

    StorageReference storageReference ;
    DatabaseReference databaseReference;

    int Image_Request_Code = 7;
    ProgressDialog progressDialog ;

    private static final int CAMERA_REQUEST = 1888;
    String name, age, city;
    EditText nameIn, ageIn, cityIn;
    ImageView cameraBut, cameraDisp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_user);


        storageReference = FirebaseStorage.getInstance().getReference();

        // Assign FirebaseDatabase instance with root database name.
        databaseReference = FirebaseDatabase.getInstance().getReference(Database_Path);

        nameIn = findViewById(R.id.nameUser);
        ageIn = findViewById(R.id.ageUser);
        cityIn = findViewById(R.id.cityUser);
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

        TextView submit  = findViewById(R.id.sumbitUser);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name = nameIn.getText().toString();
                age = ageIn.getText().toString();
                city = cityIn.getText().toString();

                if (name.isEmpty()){
                    nameIn.setError("Can't be empty");
                    return;
                }

                if (age.isEmpty()){
                    ageIn.setError("Can't be empty");
                    return;
                }

                if (city.isEmpty()){
                    cityIn.setError("Can't be empty");
                    return;
                }

                else
                UploadImageFileToFirebaseStorage();
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
            storageReference2nd.putFile(FilePathUri)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {


                            // Getting image name from EditText and store into string variable.
                            name = nameIn.getText().toString();
                            age = ageIn.getText().toString();
                            city = cityIn.getText().toString();

                            // Hiding the progressDialog after done uploading.
                            progressDialog.dismiss();

                            storageReference2nd.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                @Override
                                public void onSuccess(Uri uri) {
                                    String down = uri.toString();
                                    Toast.makeText(getApplicationContext(), down, Toast.LENGTH_LONG).show();
                                    String uniqueId = UUID.randomUUID().toString();

                                    UserUploadInfo userUploadInfo = new UserUploadInfo(name, age, city, down);

                                    // Getting image upload ID.
                                    // Adding image upload id s child element into databaseReference.
                                    databaseReference.child(uniqueId).setValue(userUploadInfo);

                                    Intent intent = new Intent(DetailsUser.this, HomeActivity.class);
                                    intent.putExtra("url",down);
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
                            Toast.makeText(DetailsUser.this, exception.getMessage(), Toast.LENGTH_LONG).show();
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

            Toast.makeText(DetailsUser.this, "Please Select Image or Add Image Name", Toast.LENGTH_LONG).show();

        }
    }
}



