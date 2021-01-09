package com.example.swasdektest;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
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

public class Register extends AppCompatActivity {
    EditText username,email,password;
    Button signUp;
    TextView logIn;
    FirebaseAuth firebaseAuth;
    @Override
    public void onBackPressed(){
        moveTaskToBack(true);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        
        username = findViewById(R.id.userNameCreate);
        email = findViewById(R.id.emailCreate);
        password = findViewById(R.id.passwordCreate);
        signUp = findViewById(R.id.signUpCreate);
        logIn = findViewById(R.id.logInCreate);

        firebaseAuth = FirebaseAuth.getInstance();

        if(firebaseAuth.getCurrentUser() != null){
            startActivity(new Intent(getApplicationContext(), MainActivity.class));
        }
        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String checkEmail = email.getText().toString().trim();
                String checkPassword = password.getText().toString().trim();

                if(TextUtils.isEmpty(checkEmail)){
                    email.setError("Email is empty");
                    return;
                }

                if(TextUtils.isEmpty(checkPassword)){
                    password.setError("password required");
                    return;
                }

                if(checkPassword.length() < 6){
                    password.setError("Password length > 6");
                    return;
                }

                //Firebase registration

                firebaseAuth.createUserWithEmailAndPassword(checkEmail,checkPassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(Register.this,"Sucessfully Registered",Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(), Register.class));
                        }else{
                            Toast.makeText(Register.this,"Error "+ task.getException().getMessage() ,Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });

        logIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),Login.class));
            }
        });
    }
}