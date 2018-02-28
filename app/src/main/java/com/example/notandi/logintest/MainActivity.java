package com.example.notandi.logintest;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    private EditText username;
    private EditText password;
    private TextView loginAttempts;
    private Button loginButton;
    private int counter = 5;
    private TextView signupRedirection;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        username = (EditText)findViewById(R.id.username);
        password = (EditText)findViewById(R.id.password);
        loginAttempts = (TextView)findViewById(R.id.loginAttempts);
        loginButton = (Button)findViewById(R.id.loginButton);
        signupRedirection = (TextView)findViewById(R.id.signupRedirection);

        signupRedirection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, RegistrationActivity.class));
            }
        });
    }

    private void validate(String username, String userPassword){
    }
}
