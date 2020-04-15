package com.example.cip;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import java.util.EventListener;

public class Register extends AppCompatActivity implements View.OnClickListener{
    EditText editTextusername,editTextregisternumber,editTextcgpa,editTextuserid,editTextpassword;
    private FirebaseAuth mauth;
    FirebaseDatabase rootNode;
    DatabaseReference databaseReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        findViewById(R.id.alreadyuser).setOnClickListener(this);
        findViewById(R.id.login).setOnClickListener(this);
        editTextusername=findViewById(R.id.username);
        editTextregisternumber=findViewById(R.id.registerNumber);
        editTextcgpa=findViewById(R.id.usercgpa);
        editTextuserid=findViewById(R.id.userid);
        editTextpassword=findViewById(R.id.password);
        mauth=FirebaseAuth.getInstance();

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.alreadyuser:
                startActivity(new Intent(this, MainActivity.class));
                break;
            case R.id.login:
                registerUser();
                startActivity(new Intent(this,Home_Page.class));
                break;
        }
    }

    private void registerUser() {
        String username = editTextusername.getText().toString().trim();
        String registerNumber=editTextregisternumber.getText().toString().trim();
        String cgpa=editTextcgpa.getText().toString().trim();
        String email= editTextuserid.getText().toString().trim();
        String password=editTextpassword.getText().toString().trim();
        if(username.isEmpty()){
            editTextusername.setError("Username cannot be Empty!!");
            editTextusername.requestFocus();
            return;
        }
        if (registerNumber.isEmpty()) {
            editTextregisternumber.setError("Register Number cannot be Empty!!");
            editTextregisternumber.requestFocus();
            return;
        }
        if (cgpa.isEmpty()) {
            editTextcgpa.setError("CGPA cannot be Empty!!");
            editTextcgpa.requestFocus();
            return;
        }

        if (email.isEmpty()) {
            editTextuserid.setError("User Id cannot be Empty!!");
            editTextuserid.requestFocus();
            return;
        }
        if (password.isEmpty()) {
            editTextpassword.setError("Password cannot be Empty!!");
            editTextpassword.requestFocus();
            return;
        }
        if(password.length()<6){
            editTextpassword.setError("Password length less than 6");
            editTextpassword.requestFocus();
            return;
        }
        mauth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(getApplicationContext(), "Successfully Registeres", Toast.LENGTH_SHORT).show();

                } else {
                    Log.d("harish","Error "+task.getException());
                    Toast.makeText(getApplicationContext(), "Failed to Register", Toast.LENGTH_SHORT).show();
                }
            }

        });
        rootNode=FirebaseDatabase.getInstance();
        databaseReference =rootNode.getReference("users");
        UserHelperClass helperClass =new UserHelperClass(username,registerNumber,cgpa,email,password);
        databaseReference.child(registerNumber).setValue(helperClass);

        DatabaseReference dBRefe = databaseReference.child("username");
        dBRefe.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                long numChildren = dataSnapshot.getChildrenCount();
                if(dataSnapshot.exists()){
                    //data exists

                }
              //  System.out.println(count.get() + " == " + numChildren);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {}
        });


    }
}
