package com.example.firebasebenni;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class UserInformation extends AppCompatActivity{

    Button logout;
    private FirebaseAuth mAuth;
    FirebaseUser mUser;
    ProgressDialog progressDialog;
    TextView email,pass;


        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate( savedInstanceState );
            setContentView( R.layout.activity_users );

            email = (TextView) findViewById( R.id.email );
            pass =(TextView) findViewById( R.id.pass );

            String Email =getIntent().getStringExtra( "Email" );
            String Pass = getIntent().getStringExtra( "Pass" );
            Intent intent = new Intent(UserInformation.this,UserInformation.class);
            email.setText( Email );
            pass.setText( Pass );

            logout= (Button)findViewById( R.id.Logout );

            logout.setOnClickListener( new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                 FirebaseAuth.getInstance().signOut();
                    Toast.makeText( UserInformation.this, "Logged out Successfully", Toast.LENGTH_SHORT ).show();
                    Intent intent = new Intent(UserInformation.this,MainActivity.class);
                    startActivity( intent );
                }
            } );


        }

    }
