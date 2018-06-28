package com.vaibhav.studybuddy;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class user extends AppCompatActivity {
Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        //button = (Button) findViewById(R.id.button);

    }
     public void navia(View view)
     {
         Intent w = new Intent(user.this,vlecs.class);
         startActivity(w);
     }
    public void navib(View view)
    {
        Intent e = new Intent(user.this,pbl.class);
        startActivity(e);
    }
    public void navic(View view)
    {
        Toast.makeText(user.this,"This Feature will support your device soon",Toast.LENGTH_LONG).show();
    }
    public void navid(View view)
    {
        Intent r = new Intent(user.this,fdback.class);
        startActivity(r);
    }
}
