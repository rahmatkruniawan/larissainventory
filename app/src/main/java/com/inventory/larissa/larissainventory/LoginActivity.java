package com.inventory.larissa.larissainventory;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {

    private static final String TAG = "LOGIN";
    private FirebaseAuth mAuth;
// ...

    EditText emailEdit , passwordEdit;
    LinearLayout btnLogin;
    ProgressDialog pgDialog;
    SharedPreferences settings;
    SharedPreferences.Editor prefEditor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mAuth = FirebaseAuth.getInstance();
        emailEdit = (EditText) findViewById(R.id.email);
        passwordEdit= (EditText) findViewById(R.id.password);
        btnLogin= (LinearLayout) findViewById(R.id.btnLogin);

        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null){
            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        }


        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                attemptLogin();
            }
        });
    }

    public void attemptLogin(){
        if(emailEdit.getText().toString().trim().equals("")){
            Toast.makeText(this, "Email harus diisi!", Toast.LENGTH_SHORT).show();
            return;
        }
        if(passwordEdit.getText().toString().trim().equals("")){
            Toast.makeText(this, "Password harus diisi!", Toast.LENGTH_SHORT).show();
            return;
        }

        singIn(emailEdit.getText().toString().trim(), passwordEdit.getText().toString().trim());
    }

    public void singIn(final String email, final String password){
        pgDialog = new ProgressDialog(this);
        pgDialog.setMessage("Loading ...");
        pgDialog.setCanceledOnTouchOutside(false);
        pgDialog.show();

        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();

                            settings = getSharedPreferences("LOGIN_ACCOUNT", MODE_PRIVATE);
                            prefEditor = settings.edit();

                            prefEditor.putString("email", email);
                            prefEditor.putString("password", password);
                            prefEditor.commit();

                            pgDialog.dismiss();
                            Toast.makeText(LoginActivity.this, "Selamat datang +"+user.getEmail(), Toast.LENGTH_SHORT).show();

                            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                            startActivity(intent);
                            finish();
                        } else {
                            // If sign in fails, display a message to the user.
                            pgDialog.dismiss();
                            Log.w(TAG, "signInWithEmail:failure", task.getException());
                            Toast.makeText(LoginActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        }

                        // ...
                    }
                });
    }
}
