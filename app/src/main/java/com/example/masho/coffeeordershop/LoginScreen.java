package com.example.masho.coffeeordershop;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
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
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class LoginScreen extends AppCompatActivity {
    private Button signin;
    private EditText mEmail;
    private EditText mPassword;
    private TextView signup;
    private DatabaseReference databaseReference;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private FirebaseUser mUser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_screen);
        signin = findViewById(R.id.email_sign_in_button);
        mEmail = findViewById(R.id.email);
        mPassword = findViewById(R.id.password);
        signup = findViewById(R.id.signuptxt);
        mAuth = FirebaseAuth.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference("Users").push().child("Login");

        //this is used when user is signin and authenticate as well so it'll lead to the menu activity.
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {

                //if user is not null i.e user is signed in.
                if(firebaseAuth.getCurrentUser() !=null)
                {
                    Intent i = new Intent(LoginScreen.this,Coffee_Shop.class);
                    startActivity(i);
                }
            }
        };

        //will go to signup activity page.
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(LoginScreen.this,SignUp_Activty.class);
                startActivity(i);
            }
        });

        //signin method will start.
        signin.setOnClickListener(  new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startSignin();
            }
        });

    }



    @Override
    protected void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }

    //signin method
    private void startSignin()
    {
        final String email = mEmail.getText().toString();
        String pasw = mPassword.getText().toString();
        if(TextUtils.isEmpty(email) || TextUtils.isEmpty(pasw))
        {
            Toast.makeText(getApplicationContext(),"Fields are required",Toast.LENGTH_LONG).show();
        }
        else
        {
            //it is signed in with email and password user have and add on complete listener means if user is completely signin to the app
            mAuth.signInWithEmailAndPassword(email,pasw).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful())
                    {
                        mUser =mAuth.getCurrentUser();
                        if(mUser !=null)
                        {
                            //saving user id and email into database having top node as login.
                            String uId = mUser.getUid();
                            String email = mUser.getEmail();
                            databaseReference.child("User-ID").setValue(uId);
                            databaseReference.child("Email").setValue(email);
                        }
                    }
                    else {
                        Toast.makeText(getApplicationContext(),"Cant sign in",Toast.LENGTH_LONG).show();
                    }
                }
            });
        }
    }
}
