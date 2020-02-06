package com.example.firebasedemoactivity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Signupactivity extends AppCompatActivity implements View.OnClickListener {

    EditText edittextemail, edittextpassword;
    Button signupButton;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign);

        edittextemail = (EditText) findViewById(R.id.editTextEmail);
        edittextpassword = (EditText) findViewById(R.id.editTextPassword);
        signupButton = findViewById(R.id.btnsignup);


        mAuth = FirebaseAuth.getInstance();

        signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerUser();
            }
        });

    }

    private void registerUser() {
        String email = edittextemail.getText().toString().trim();
        String password = edittextpassword.getText().toString().trim();

        if (email.isEmpty()) {
            edittextemail.setError("Email is required");
            edittextemail.requestFocus();
            return;
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            edittextemail.setError("Please enter valid email");
            edittextemail.requestFocus();
            return;

        }

        if (password.isEmpty()) {
            edittextpassword.setError("password is required");
            edittextpassword.requestFocus();
            return;
        }

        if(password.length()<6){

            edittextpassword.setError("Minimum length of password should be 6");
            edittextpassword.requestFocus();
            return;
        }

        mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Toast.makeText(getApplicationContext(),"User register successfull",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnsignup:
                registerUser();
                break;

            case R.id.edittextsingup:
                startActivity(new Intent(this, MainActivity.class));
                break;
        }

    }
}

