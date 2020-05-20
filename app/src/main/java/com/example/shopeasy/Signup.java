package com.example.shopeasy;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Signup extends AppCompatActivity {
    Context context;

    public void init(Context ctx){
        this.context = ctx;
    }

    private EditText email;
    private EditText name;
    private EditText mobile;
    private EditText password;
    private EditText cnf_password;

    private Button signup;
    private String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+.[a-z]+";

    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        email = findViewById(R.id.reg_email);
        name = findViewById(R.id.reg_name);
        mobile = findViewById(R.id.reg_mobile);
        password = findViewById(R.id.reg_pwd);
        cnf_password = findViewById(R.id.reg_cnf_pwd);
        signup = findViewById(R.id.signup_btn);

        firebaseAuth = FirebaseAuth.getInstance();

        email.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                checkInputs();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        name.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                checkInputs();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        mobile.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                checkInputs();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        password.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                checkInputs();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        cnf_password.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                checkInputs();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    public void signup_btn_click(View v){
                cross_check();
    }

    public void click2(View v){
        Intent intent = new Intent(Signup.this,Login.class);
        startActivity(intent);
        finish();
    }

     public void checkInputs(){
         if(!TextUtils.isEmpty(name.getText())){
             if(!TextUtils.isEmpty(email.getText())){
                 if(!TextUtils.isEmpty(mobile.getText())){
                     if(!TextUtils.isEmpty(password.getText()) && password.length()>=8) {
                         if (!TextUtils.isEmpty(cnf_password.getText())) {
                             signup.setEnabled(true);
                         }
                         else {signup.setEnabled(false);
                         }
                     }
                     else{signup.setEnabled(false);
                         signup.setTextColor(Color.argb(100, 255, 255, 255));}
                 }
                 else{signup.setEnabled(false);}
             }
             else{signup.setEnabled(false);}
         }
         else{signup.setEnabled(false);}
     }

     public void cross_check(){
        if(email.getText().toString().matches(emailPattern)){
            if(password.getText().toString().equals(cnf_password.getText().toString())){
                signup.setEnabled(false);
                firebaseAuth.createUserWithEmailAndPassword(email.getText().toString(),password.getText().toString())
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if(task.isSuccessful()){
                                     Intent loginIntent = new Intent(Signup.this,Login.class);
                                     startActivity(loginIntent);
                                }else{
                                    signup.setEnabled(true);
                                    String error = task.getException().getMessage();
                                    Toast.makeText(context,error,Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }else{
                cnf_password.setError("Password does not match");
            }
        }else{
            email.setError("Invalid Email");
        }
     }
}