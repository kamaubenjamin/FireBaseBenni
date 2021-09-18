package com.example.firebasebenni;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class ResetPassword extends AppCompatActivity {

    Button buttonReset;
    EditText E;
    private FirebaseAuth mAuth;
    FirebaseUser mUser;
    ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_reset );

        buttonReset= (Button) findViewById( R.id.buttonReset );
        E= (EditText) findViewById( R.id.E );
        mAuth = FirebaseAuth.getInstance();
        mUser = mAuth.getCurrentUser();
        progressDialog = new ProgressDialog( this );

        buttonReset.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Emails = E.getText().toString();


                if (TextUtils.isEmpty( Emails )) {
                    E.setError( "Email Required" );
                }

                // Reset the user pass in firebase
                progressDialog.setMessage( "Please Wait while Resetting .." );
                progressDialog.setTitle( "Reset." );
                progressDialog.setCanceledOnTouchOutside( false );
                progressDialog.show();

                mAuth.sendPasswordResetEmail( Emails ).addOnCompleteListener( new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            progressDialog.dismiss();
                            Toast.makeText( ResetPassword.this, "Reset Successfully!CheckYour Email", Toast.LENGTH_SHORT ).show();
                            Intent intent = new Intent( ResetPassword.this, MainActivity.class );

                        } else {

                            Toast.makeText( ResetPassword.this, "Re-Enter Email", Toast.LENGTH_SHORT ).show();
                        }
                    }
                });

            }
        });}
   }




