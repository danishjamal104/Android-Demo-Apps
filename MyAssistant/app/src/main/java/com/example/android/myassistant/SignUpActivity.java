package com.example.android.myassistant;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignUpActivity extends AppCompatActivity {

    EditText username;
    EditText password;
    EditText confirmPassword;

    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        username = findViewById(R.id.userName_editText);
        password = findViewById(R.id.password_editText);
        confirmPassword = findViewById(R.id.confirmPassword_editText);

        signUp();
    }

    public void signUp(){
        if(password.getText().toString().trim() != confirmPassword.getText().toString().trim()){
            Toast.makeText(SignUpActivity.this, "Password didnt match", Toast.LENGTH_SHORT).show();
        }else{
            String email = username.getText().toString().trim();
            String password = confirmPassword.getText().toString().trim();

            mAuth.signInWithEmailAndPassword(email, password)
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {

                        }
                    })
                    .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                @Override
                public void onSuccess(AuthResult authResult) {

                }
            });
        }
    }

}
