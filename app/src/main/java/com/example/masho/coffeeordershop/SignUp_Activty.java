package com.example.masho.coffeeordershop;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Gravity;
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

public class SignUp_Activty extends AppCompatActivity {

    private EditText mEmail;
    private EditText mMemberShipNumber;
    private EditText mPassword;
    private Button mSignupBtn;
    private FirebaseAuth mAuths;
    private FirebaseUser user;
    private TextView signin;
    private DatabaseReference databaseReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up__activty);


        mEmail = (EditText) findViewById(R.id.email);
        mAuths = FirebaseAuth.getInstance();
        mMemberShipNumber = (EditText) findViewById(R.id.MembershipNo);
        databaseReference = FirebaseDatabase.getInstance().getReference("Users").push().child("Sign-up");
        mPassword = (EditText) findViewById(R.id.password);
        mSignupBtn = (Button) findViewById(R.id.email_sign_up_button);
        signin = (TextView)findViewById(R.id.Signintxt);

        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SignUp_Activty.this, LoginScreen.class));
            }
        });

        // it creates user by enter email, membership no and pass and we make user to remember email and pasw to login to the app.
        mSignupBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String email = mEmail.getText().toString();
                final String MemberNo = mMemberShipNumber.getText().toString();
                final  String Pass = mPassword.getText().toString();

                user = mAuths.getCurrentUser();

                //if any of the field is empty it'll pop up the toast.
                if (TextUtils.isEmpty(email) && TextUtils.isEmpty(MemberNo) && TextUtils.isEmpty(Pass)) {
                    Toast toast = Toast.makeText(getApplicationContext(),"Please Insert all Fields",Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.CENTER| Gravity.BOTTOM, 0,0);
                    toast.show();
                }
                //if email field doesnt conatin @ or .com, it'll pop up the toast.
                else if(!email.contains("@")||!email.contains(".com"))
                {
                    Toast toast = Toast.makeText(getApplicationContext(),"Check your Email",Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.CENTER| Gravity.BOTTOM, 0,0);
                    toast.show();
                }
                //if pasw length must be 6
                else if(Pass.length()!=6)
                {

                    Toast toast = Toast.makeText(getApplicationContext(),"Your Password length must be equal to six",Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.CENTER| Gravity.BOTTOM, 0,0);
                    toast.show();
                }
                else {

                    /*if(email.equals(user.getEmail()))
                    {
                       Toast toast = Toast.makeText(getApplicationContext(),"Email already exists,Try another",Toast.LENGTH_SHORT);
                        toast.setGravity(Gravity.CENTER| Gravity.BOTTOM, 0,0);
                        toast.show();
                    }
                    else {*/

                    // here we create the user with email and password and also save the data of user into firebase database with node signup
                    mAuths.createUserWithEmailAndPassword(email, Pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(getApplicationContext(), "Registered Succesfully", Toast.LENGTH_LONG).show();
                                startActivity(new Intent(SignUp_Activty.this, Coffee_Shop.class));
                                FirebaseUser user = mAuths.getCurrentUser();
                                if (user != null) {
                                    String uId = user.getUid();
                                    databaseReference.child("Email").setValue(email);
                                    databaseReference.child("UId").setValue(uId);
                                    databaseReference.child("MembershipNo").setValue(MemberNo);
                                }

                            } else {
                                Toast.makeText(getApplicationContext(), "User Already Exists", Toast.LENGTH_LONG).show();
                            }
                        }
                    });
                }
                //}
            }
        });
    }
}
