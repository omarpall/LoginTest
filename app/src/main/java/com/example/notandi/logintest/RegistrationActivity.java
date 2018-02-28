package com.example.notandi.logintest;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegistrationActivity extends AppCompatActivity {


    private EditText signupUsername, signupEmail, signupPassword, signupPassword2;
    private TextView signupLogin;
    private Button signupButton;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        setupUIViews();

        firebaseAuth = FirebaseAuth.getInstance();

        signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(validate()){
                    //Upload data to the database
                    String email = signupEmail.getText().toString().trim();
                    String password = signupPassword.getText().toString().trim();
                    System.out.println(email + " + " + password);
                    firebaseAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()) {
                                Toast.makeText(RegistrationActivity.this, "Registration successful", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(RegistrationActivity.this, MainActivity.class));
                            }
                            else{
                                Toast.makeText(RegistrationActivity.this, "Registration failed", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }
        });

        signupLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(RegistrationActivity.this, MainActivity.class));
            }
        });

    }

    private void setupUIViews() {
        signupUsername = (EditText) findViewById(R.id.signupUsername);
        signupEmail = (EditText) findViewById(R.id.signupEmail);
        signupPassword = (EditText) findViewById(R.id.signupPassword);
        signupPassword2 = (EditText) findViewById(R.id.signupPassword2);
        signupLogin = (TextView) findViewById(R.id.signupLogin);
        signupButton = (Button) findViewById(R.id.signupButton);
    }

    private Boolean validate(){
        Boolean result = false;
        String username = signupUsername.getText().toString();
        String password = signupPassword.getText().toString();
        String password2 = signupPassword2.getText().toString();
        String email = signupEmail.getText().toString();
        if(username.isEmpty() || password.isEmpty() || password2.isEmpty() || email.isEmpty()){
            Toast.makeText(this, "Please enter all the details", Toast.LENGTH_LONG).show();
        } else if(!password.equals(password2)){
            System.out.println(password + " " + password2);
            Toast.makeText(this, "The passwords have to match", Toast.LENGTH_SHORT).show();
        } else{
            result = true;
        }
        System.out.println("result " + result);
        return result;
    }
}
