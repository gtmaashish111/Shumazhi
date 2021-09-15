package com.example.shumazhi;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class RegisterUserActivity extends AppCompatActivity {

    private EditText userFullName, userAddress, userPhone, userDOB, userPassword, userConfirmPassword, userEmail, userName;
    private Button btnSignUp;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_user);

        userFullName = (EditText) findViewById(R.id.txtFullName);
        userAddress = (EditText) findViewById(R.id.txtAddress);
        userPhone = (EditText) findViewById(R.id.txtPhone);
        userDOB = (EditText) findViewById(R.id.txtDOB);
        userEmail = (EditText) findViewById(R.id.txtEmail);
        userName = (EditText) findViewById(R.id.txtUser);
        userPassword = (EditText) findViewById(R.id.txtPassword);
        userConfirmPassword = (EditText) findViewById(R.id.txtConfirmPassword);
        btnSignUp = (Button) findViewById(R.id.btnSignUp);
        firebaseAuth = FirebaseAuth.getInstance();


        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String fullname = userFullName.getText().toString();
                final String address = userAddress.getText().toString();
                final String phone = userPhone.getText().toString();
                final String dob = userDOB.getText().toString();
                final String username = userName.getText().toString();
                final String email = userEmail.getText().toString();
                final String password = userPassword.getText().toString();
                final String confirmpassword = userConfirmPassword.getText().toString();


                if (fullname.isEmpty() || address.isEmpty() || phone.isEmpty() || dob.isEmpty() || username.isEmpty() || password.isEmpty() || confirmpassword.isEmpty()) {
                    // Display an Error message

                    showMessage("Please Insert all Fields");
                    btnSignUp.setVisibility(View.VISIBLE);

                } else {
                    // every thing is fine
                    // CreateUSerAccount method will try to create the user if the email is valid.
                    CreateUserAccount(fullname, address, phone, dob, username, email, password);
                }


            }
        });
    }

    private void CreateUserAccount(final String fullname,final String address,final String phone,final String dob,final String username,final String email, String password) {
        // this method create user Account with specific email and password
        firebaseAuth.createUserWithEmailAndPassword(email,password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful())
                        {
                            // user account created successfully


                            User us = new User(fullname,address,phone,dob,username,email);
                            FirebaseDatabase.getInstance().getReference("Users")
                                    .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                    .setValue(us).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {

                                    showMessage("Account Created");
                                    UpdateUI();

                                }
                            });



                        }
                        else
                        {
                            showMessage("Account creation failed" + task.getException().getMessage());
                            btnSignUp.setVisibility(View.VISIBLE);
                        }
                    }
                })    ;

    }

        private void UpdateUI() {
            Intent homeActivity = new Intent(getApplicationContext(),ScanCode.class);
            startActivity(homeActivity);
            finish();
        }

        // Simple method to show Toast Message


    private void showMessage(String text) {
            Toast.makeText(getApplicationContext(),text,Toast.LENGTH_LONG).show();
        }

}
