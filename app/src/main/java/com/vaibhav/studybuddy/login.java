package com.vaibhav.studybuddy;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.GoogleAuthProvider;

import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.concurrent.TimeUnit;

public class login extends AppCompatActivity {
EditText usrpass,usrtxt;
Button button;
private SignInButton mGoogleBtn;
private static final int RC_SIGN_IN = 1;
GoogleApiClient mGoogleApiClient;
private FirebaseAuth.AuthStateListener authStateListener;

    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        usrpass = (EditText) findViewById(R.id.usrpass);
        usrtxt = (EditText) findViewById(R.id.editText3);
        button = (Button) findViewById(R.id.button3);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String pw = new String(usrpass.getText().toString());
                String usr = "AdminSbuddy";
                String pass = "SBuddyPortal101";

                if(usrtxt.getText().toString().equals(usr)&& usrpass.getText().toString().equals(pass))
                {
                    Intent j = new Intent(login.this,admin.class);
                    startActivity(j);
                    finish();
                    Toast.makeText(login.this,"You are in Admin Panel",Toast.LENGTH_LONG).show();
                }
                else
                {
                    Toast.makeText(login.this,"Incorrect input, please sign-in using Google",Toast.LENGTH_LONG).show();
                }
            }
        });





        mGoogleBtn = (SignInButton) findViewById(R.id.signInButton);

        mAuth = FirebaseAuth.getInstance();
       authStateListener =  new FirebaseAuth.AuthStateListener() {
    @Override
    public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
        if(firebaseAuth.getCurrentUser() != null)
        {
            Intent m = new Intent(login.this,user.class);
            startActivity(m);
        }
    }
};

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this, new GoogleApiClient.OnConnectionFailedListener() {
                    @Override
                    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
                    Toast.makeText(login.this,"ERROR: Unable to sign in",Toast.LENGTH_LONG).show();
                    }
                })
                .addApi(Auth.GOOGLE_SIGN_IN_API,gso)
        .build();

         mGoogleBtn.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 signIn();
             }
         });



    }

    public void admlogin(View view)
    {
        String pw = new String(usrpass.getText().toString());
        String usr = "AdminSbuddy";
        String pass = "SBuddyPortal101";

        if(usrtxt.getText().toString().equals(usr)&& usrpass.getText().toString().equals(pass))
        {
            Intent j = new Intent(login.this,admin.class);
            startActivity(j);
            finish();
            Toast.makeText(login.this,"You are in Admin Panel",Toast.LENGTH_LONG).show();
        }
        else
        {
            Toast.makeText(login.this,"Incorrect input, please sign-in using Google",Toast.LENGTH_LONG).show();
        }
    }
    private void signIn() {

        Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            if(result.isSuccess()){
                // Google Sign In was successful, authenticate with Firebase
                GoogleSignInAccount account = result.getSignInAccount();
                firebaseAuthWithGoogle(account);
            }
            else {
                // ...
            }
        }
    }

    private void firebaseAuthWithGoogle(GoogleSignInAccount account) {
        AuthCredential credential = GoogleAuthProvider.getCredential(account.getIdToken(), null);
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Intent m = new Intent(login.this,user.class);
                            startActivity(m);
                            Toast.makeText(login.this,"Authentication Successful",Toast.LENGTH_LONG).show();
                        } else {
                            // If sign in fails, display a message to the user.
Toast.makeText(login.this,"Authentication Failed",Toast.LENGTH_LONG).show();                        }

                        // ...
                    }
                });
    }

}
