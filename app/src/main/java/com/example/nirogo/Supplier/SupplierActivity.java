package com.example.nirogo.Supplier;

import androidx.annotation.NonNull;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nirogo.HomeActivity;
import com.example.nirogo.OptionActivity;
import com.example.nirogo.R;
import com.example.nirogo.User.UserActivity;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;

import static android.content.ContentValues.TAG;

public class SupplierActivity extends Activity {

    private GoogleSignInClient mGoogleSignInClient;
    private ImageView googleimage;
    private final static int RC_SIGN_IN = 123;
    private FirebaseAuth mAuth;
    private String LOG_TAG= SupplierActivity.class.getSimpleName();
    private TextView signup;
    private EditText email;
    private EditText password;

    @Override
    public void onStart() {

        super.onStart();
        FirebaseUser user= mAuth.getCurrentUser();
        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(this);
        if(user!=null){
            Intent intent = new Intent(SupplierActivity.this, HomeActivity.class);
            startActivity(intent);
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_supplier);

        Button back = findViewById(R.id.backSup);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SupplierActivity.this, OptionActivity.class);
                startActivity(intent);
            }
        });

        mAuth= FirebaseAuth.getInstance();
        googleimage = findViewById(R.id.googleSup);
        signup= findViewById(R.id.signupSupplier);
        email= findViewById(R.id.usernameSup);
        password= findViewById(R.id.passwordSup);

        //setting up google request

        creategooglerequest();


        //setting up onclick Listener
        googleimage.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                signIn();   //calling method to send intent to google client
            }
        });

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String emailtext= email.getText().toString().trim();
                String passwordtext= password.getText().toString();

                //check constraints that email and password shouldnot be empty
                if(TextUtils.isEmpty(emailtext)){
                    Toast.makeText(getApplicationContext(),"Please Enter Email",Toast.LENGTH_SHORT).show();
                    return;
                }
                if(TextUtils.isEmpty(passwordtext)){
                    Toast.makeText(getApplicationContext(),"Please Enter Password",Toast.LENGTH_SHORT).show();
                    return;
                }
                if(password.length()<6){
                    Toast.makeText(getApplicationContext(),"PAssword too short",Toast.LENGTH_SHORT).show();
                    return;
                }
                createrequestusingEmailPassword(emailtext,passwordtext);
            }});


    }


    private  void creategooglerequest(){
        // Configure Google Sign In
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();



        // getting the GoogleSignInClient object from GoogleSignIn class
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);
    }

    private void signIn() {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {


        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {


            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                // Google Sign In was successful, authenticate with Firebase
                GoogleSignInAccount account = task.getResult(ApiException.class);

                Log.d(LOG_TAG, "firebaseAuthWithGoogle:" + account.getId());

                firebaseAuthWithGoogle(account.getIdToken());

            } catch (ApiException e) {

                Toast.makeText(this, "signup failed", Toast.LENGTH_SHORT).show();

                Log.e (LOG_TAG,"failed status code:"+ e.getStatusCode());

            }
        }
    }

    private void firebaseAuthWithGoogle(String idToken) {

        AuthCredential credential = GoogleAuthProvider.getCredential(idToken, null);
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {

                            Log.d(TAG, "signInWithCredential:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            Toast.makeText(SupplierActivity.this,"SignIn Successful",Toast.LENGTH_SHORT).show();

                            Intent intent = new Intent(SupplierActivity.this, HomeActivity.class);
                            startActivity(intent);
                        }

                        else {
                            Log.w(TAG, "signInWithCredential:failure", task.getException());
                            Toast.makeText(getApplicationContext(), "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        }

                        // ...
                    }
                });

        TextView signup = findViewById(R.id.signupSupplier);
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SupplierActivity.this, HomeActivity.class);
                startActivity(intent);
            }
        });

    }

    private void createrequestusingEmailPassword(String email, String password){

        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(SupplierActivity.this,"Signup Successful",Toast.LENGTH_SHORT);
                            FirebaseUser user = mAuth.getCurrentUser();
                            Intent intent = new Intent(SupplierActivity.this, HomeActivity.class);
                            startActivity(intent);

                        }
                        else {
                            try {
                                throw task.getException();
                            }
                            // if user enters wrong email.

                            catch (FirebaseAuthInvalidCredentialsException malformedEmail) {
                                Log.d(TAG, "onComplete: malformed_email");
                                Toast.makeText(SupplierActivity.this, "Enter Correct Email", Toast.LENGTH_SHORT).show();
                                return;

                            } catch (FirebaseAuthUserCollisionException existEmail) {
                                Log.d(TAG, "onComplete: exist_email");
                                Toast.makeText(SupplierActivity.this, "Email already Exist", Toast.LENGTH_SHORT).show();
                                return;


                            } catch (Exception e) {
                                Log.d(TAG, "onComplete: " + e.getMessage());
                            }
                        }
                    }
                });
    }
}
