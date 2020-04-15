package com.example.cip;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity  implements View.OnClickListener{
    private EditText useremail;
    private EditText userpassword;
    private FirebaseAuth mauth;
    private ProgressDialog mDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.register).setOnClickListener(this);
        findViewById(R.id.login).setOnClickListener(this);
        useremail=findViewById(R.id.userid);
        userpassword=findViewById(R.id.password);
        mauth=FirebaseAuth.getInstance();
        mDialog=new ProgressDialog(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.register:
                startActivity(new Intent(this,Register.class));
                break;
            case R.id.login:
                String email=useremail.getText().toString().trim();
                String password=userpassword.getText().toString().trim();
                if (TextUtils.isEmpty(email)) {
                    useremail.setError("Field required");
                    return;
                }
                if (TextUtils.isEmpty(password)) {
                    userpassword.setError("Field Required");
                    return;
                }
                mDialog.setMessage("Processing");
                 mDialog.show();
                 mauth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                     @Override
                     public void onComplete(@NonNull Task<AuthResult> task) {
                         if (task.isSuccessful()) {
                             startActivity(new Intent(getApplicationContext(),Home_Page.class));
                         } else
                             Toast.makeText(getApplicationContext(), "Username Password Not matched", Toast.LENGTH_SHORT).show();
                         mDialog.dismiss();
                         }

                 });
        }

    }
}
