package com.example.firebasebenni.models;

import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class users extends AppCompatActivity {

    String name,email,pass,age;

    public users(){

    }

    public users(String name,String email,String pass,String age){

        this.name = name;
        this.email = email;
        this.age = age;
        this.pass = pass;
    }
    public users (String email, String pass,String age)
    {
        this.email = email;
        this.pass = pass;
        this.age= age;
    }


}
