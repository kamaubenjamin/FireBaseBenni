package com.example.firebasebenni;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.firebasebenni.models.users;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;

import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.auth.User;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;


public class Register extends Activity {

    //Declare Variables
    Button register;
    EditText name, email, pass, age;
    TextView login_link;
    ProgressBar progressBar;
    private FirebaseAuth mAuth;
    ProgressDialog progressDialog;
    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    FirebaseUser mUser;
    FirebaseDatabase firebaseDatabase;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );

        //Initialize Variables

        setContentView( R.layout.activity_register );
        register = (Button) findViewById( R.id.button_register );
        name = (EditText) findViewById( R.id.full_names );
        email = (EditText) findViewById( R.id.email );
        pass = (EditText) findViewById( R.id.pass );
        age = (EditText) findViewById( R.id.age );
        login_link = (TextView) findViewById( R.id.login_link );
        progressDialog = new ProgressDialog( this );
        mAuth = FirebaseAuth.getInstance();
        mUser = mAuth.getCurrentUser();


        register.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String full_name = name.getText().toString().trim();
                String Emails = email.getText().toString().trim();
                String Age = age.getText().toString().trim();
                String Pass = pass.getText().toString().trim();


                if (TextUtils.isEmpty( full_name )) {
                    name.setError( "Full Names Required!" );
                    return;
                }

                if (TextUtils.isEmpty( Emails )) {
                    email.setError( "Email is Required." );
                    return;
                }
                if (!Emails.matches( emailPattern )) {
                    email.setError( "incorrect email format" );
                }

                if (TextUtils.isEmpty( Age )) {
                    age.setError( "Age is Required." );
                    return;
                }

                if (TextUtils.isEmpty( Pass )) {
                    pass.setError( "Password is Required." );
                    return;
                }
                if (Pass.length() < 6) {
                    pass.setError( "Password should be more than 6 Characters" );
                }

                // register the user in firebase
                progressDialog.setMessage( "Please Wait while Registering.." );
                progressDialog.setTitle( "Registration." );
                progressDialog.setCanceledOnTouchOutside( false );
                progressDialog.show();

                mAuth.createUserWithEmailAndPassword( Emails, Pass ).addOnCompleteListener( new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
/*
                            progressDialog.dismiss();
                            //create an object to use to send to firebase
                            users user = new users( full_name,Emails ,Age);
                            FirebaseDatabase.getInstance().getReference("users")
                                    .child(( FirebaseAuth.getInstance().getCurrentUser() ).getUid() )
                                    .setValue(user).addOnCompleteListener( new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()){
                                    */
                                        progressDialog.dismiss();
                                        Toast.makeText( Register.this, "Registered Successfully", Toast.LENGTH_SHORT ).show();
                                        Intent intent = new Intent( Register.this, MainActivity.class );
                                        startActivity( intent );
                                    }
                                    else {
                                        progressDialog.dismiss();
                                        Toast.makeText( Register.this, "" + task.getException(), Toast.LENGTH_SHORT ).show();
                                    }
                                }
                            } );


                        }

                        }


                    );

       //     }
      //  } );



        login_link.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Register.this,MainActivity.class);
                startActivity( intent );
            }
        } );





    }
}






