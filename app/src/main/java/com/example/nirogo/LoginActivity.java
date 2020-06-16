package com.example.nirogo;

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

import com.example.nirogo.Doctor.DoctorActivity;
import com.example.nirogo.Supplier.SupplierActivity;
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
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;

import static android.content.ContentValues.TAG;

public class LoginActivity extends Activity {

    private EditText email;
    private EditText password;
    private TextView signin;
    private ImageView googleLogin;
    private ImageView facebookLogin;
    private TextView signupfromlogin;
    private final static int RC_SIGN_IN = 123;
    private FirebaseAuth mAuth;
    private Intent intent;
    private String Type;
    private GoogleSignInClient mGoogleSignInClient;


    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser user= mAuth.getCurrentUser();

        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(this);
        if(user!=null){
            Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
            intent.putExtra("type",getIntent().getStringExtra("type"));
            startActivity(intent);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ScreenSize screenSize = new ScreenSize();
        String size = screenSize.screenCheck(LoginActivity.this);
        if (size.equalsIgnoreCase("Small")) {
            setContentView(R.layout.activity_login_small);
            Log.i("Screen Return Value","Small");
        }
        else
            setContentView(R.layout.activity_login);

        Button skip = findViewById(R.id.skipBtn);
        skip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                intent.putExtra("type",getIntent().getStringExtra("type"));
                startActivity(intent);
            }
        });

        Button back = findViewById(R.id.Loginback);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(LoginActivity.this, OptionActivity.class);
                startActivity(intent);
            }
        });


        mAuth = FirebaseAuth.getInstance();
        email= findViewById(R.id.loginEmail);
        password= findViewById(R.id.loginpassword);
        intent= getIntent();
        Type= intent.getStringExtra("type");

        googleLogin = findViewById(R.id.logingoogle);
        signin= findViewById(R.id.Signinbutton);
        signupfromlogin= findViewById(R.id.signupfromlogin);

        creategooglerequest();

        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String EmailText= email.getText().toString().trim();
                String PassWordText= password.getText().toString().trim();
                if(TextUtils.isEmpty(EmailText)){
                    Toast.makeText(LoginActivity.this,"Enter valid email",Toast.LENGTH_SHORT);
                    return;

                }

                if(TextUtils.isEmpty(PassWordText)){
                    Toast.makeText(LoginActivity.this,"Enter Password",Toast.LENGTH_SHORT);
                    return;

                }

                setemailLogin(EmailText,PassWordText);
            }
        });

       googleLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signIn();
            }
        });

        signupfromlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Type.equals("Doctor")){
                    Intent i= new Intent(LoginActivity.this, DoctorActivity.class);
                    i.putExtra("type", "Doctor");
                    startActivity(i);
                    return;
                }
                 if(Type.equals("User")){
                    Intent i= new Intent(LoginActivity.this, UserActivity.class);
                     i.putExtra("type", "User");
                     startActivity(i);
                    return;
                }

                if(Type.equals("Supplier")){
                    Intent i= new Intent(LoginActivity.this, SupplierActivity.class);
                    startActivity(i);
                    return;
                }
            }
        });
    }

    private void setemailLogin(String email, String password){
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information

                            FirebaseUser user = mAuth.getCurrentUser();
                            String uid= user.getUid();
                            Log.i("LOGIN USER UID",uid);
                            Toast.makeText(LoginActivity.this,"Signin Successful",Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                            intent.putExtra("type",getIntent().getStringExtra("type"));
                            intent.putExtra("USER UID",uid);
                            startActivity(intent);

                        } else {
                            // If sign in fails, display a message to the user.
                            Toast.makeText(LoginActivity.this, "wrong Email or password", Toast.LENGTH_SHORT).show();
                            Toast.makeText(LoginActivity.this, "Please Register", Toast.LENGTH_SHORT).show();
                            Log.w("TAG", "signInWithEmail:failure", task.getException());

                        }

                        // ...
                    }
                });
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

                Log.d("LOG_TAG", "firebaseAuthWithGoogle:" + account.getId());

                firebaseAuthWithGoogle(account.getIdToken());

            }
            catch (ApiException e) {

                Toast.makeText(this, "signup failed", Toast.LENGTH_SHORT).show();

                Log.e ("LOG_TAG","failed status code:"+ e.getStatusCode());

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
                            Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                            intent.putExtra("type",getIntent().getStringExtra("type"));
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

    }


}
