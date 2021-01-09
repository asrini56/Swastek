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

public class Login extends AppCompatActivity {
    EditText emailLogin, passwordLogin;
    Button login;
    TextView registerLogin;
    FirebaseAuth firebaseAuth;
    @Override
    public void onBackPressed(){
        moveTaskToBack(true);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        emailLogin = findViewById(R.id.emailLogin);
        passwordLogin = findViewById(R.id.passwordLogin);
        registerLogin = findViewById(R.id.registerLogin);
        login = findViewById(R.id.loginLogin);
        firebaseAuth = FirebaseAuth.getInstance();

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String checkEmail = emailLogin.getText().toString().trim();
                String checkPassword = passwordLogin.getText().toString().trim();

                if(TextUtils.isEmpty(checkEmail)){
                    emailLogin.setError("Email is empty");
                    return;
                }

                if(TextUtils.isEmpty(checkPassword)){
                    passwordLogin.setError("password required");
                    return;
                }

                if(checkPassword.length() < 6){
                    passwordLogin.setError("Password length > 6");
                    return;
                }

                //Authentication
                firebaseAuth.signInWithEmailAndPassword(checkEmail,checkPassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(Login.this,"Login Sucessful",Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(), MainActivity.class));
                        }else{
                            Toast.makeText(Login.this,"Error "+ task.getException().getMessage() ,Toast.LENGTH_SHORT).show();
                        }
                    }
                });

            }
        });

        registerLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), Register.class));
            }
        });
    }
}