package com.example.firebasebenni;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserInfo;

public class MainActivity extends AppCompatActivity {
 Button button_login;
 EditText email,pass;
 TextView forgot_link,register;
 private FirebaseAuth mAuth;
 FirebaseUser mUser;
 String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
 ProgressDialog progressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );


        button_login= (Button) findViewById( R.id.buttonlogin );
        email = (EditText) findViewById( R.id.email );
        pass =(EditText) findViewById( R.id.pass );
        forgot_link =(TextView) findViewById( R.id.forgot );
        register = (TextView) findViewById( R.id.register );
        mAuth = FirebaseAuth.getInstance();
        mUser = mAuth.getCurrentUser();
        progressDialog = new ProgressDialog( this );

        register.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,Register.class);
                startActivity( intent );


            }
        } );

        forgot_link.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,ResetPassword.class);
                startActivity( intent );


            }
        } );
       button_login.setOnClickListener( new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               String Emails = email.getText().toString();
               String Pass = pass.getText().toString();



               if(TextUtils.isEmpty( Emails )){
                   email.setError( "Email Required" );
               }
               if (!Emails.matches( emailPattern )){
                   email.setError( "Incorrect format" );
               }
               if (Pass.length()<6){
                   pass.setError( "Characters should be >=5" );
               }
               if (TextUtils.isEmpty( Pass )){
                   pass.setError( "Pass Required" );
               }
               // Login the user in firebase
               progressDialog.setMessage( "Please Wait while Login in.." );
               progressDialog.setTitle( "Login." );
               progressDialog.setCanceledOnTouchOutside( false );
               progressDialog.show();


               mAuth.signInWithEmailAndPassword(Emails,Pass ).addOnCompleteListener( new OnCompleteListener<AuthResult>() {
                   @Override
                   public void onComplete(@NonNull Task<AuthResult> task) {
                       if (task.isSuccessful()){

                           FirebaseUser user =FirebaseAuth.getInstance().getCurrentUser();
                           if (user.isEmailVerified()) {

                               progressDialog.dismiss();
                               Toast.makeText( MainActivity.this, "Login Successfully", Toast.LENGTH_SHORT ).show();

                               Intent intent = new Intent( MainActivity.this, UserInformation.class );
                               intent.putExtra( "Email", Emails );
                               intent.putExtra( "Pass",Pass );
                               startActivity( intent );
                           }
                           else{
                               progressDialog.dismiss();
                               user.sendEmailVerification();
                               Toast.makeText( MainActivity.this, "Check Email to Verify", Toast.LENGTH_SHORT ).show();
                           }
                           }
                       else{
                           progressDialog.dismiss();
                           Toast.makeText( MainActivity.this, "Failed To Login", Toast.LENGTH_SHORT ).show();
                       }
                       }
               } );




           }
       } );

    }
}