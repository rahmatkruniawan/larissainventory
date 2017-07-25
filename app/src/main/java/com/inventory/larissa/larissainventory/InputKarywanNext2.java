package com.inventory.larissa.larissainventory;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class InputKarywanNext2 extends AppCompatActivity {

    private static final String TAG = "CREATE ACCOUNT";
    EditText email, pass, repass;
    Button btnSave;
    ProgressDialog pgDialog;

    Intent i;
    Karyawan karywan;

    DatabaseReference mRootRef = FirebaseDatabase.getInstance().getReference();
    DatabaseReference mKarywanRef = mRootRef.child("karyawan");
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_karywan_next2);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Larissa Inventory");

        email = (EditText) findViewById(R.id.email);
        pass= (EditText) findViewById(R.id.pass);
        repass= (EditText) findViewById(R.id.repass);
        btnSave = (Button) findViewById(R.id.btnSave);
        mAuth = FirebaseAuth.getInstance();


        i = getIntent();
        if(i.getSerializableExtra("sp") == null){
            finish();
        }

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                attempData();
            }
        });


    }

    private void attempData() {
        if(email.getText().toString().trim().equals("")){
            Toast.makeText(this, "Email hars diisi!", Toast.LENGTH_SHORT).show();
            return;
        }

        if(pass.getText().toString().trim().equals("")){
            Toast.makeText(this, "Password hars diisi!", Toast.LENGTH_SHORT).show();
            return;
        }
        if(pass.getText().toString().trim().length() < 6){
            Toast.makeText(this, "Password minimal 6 karakter!", Toast.LENGTH_SHORT).show();
            return;
        }
        if(repass.getText().toString().trim().equals("") || !pass.getText().toString().trim().equals(repass.getText().toString().trim())){
            Toast.makeText(this, "Password harus sama!", Toast.LENGTH_SHORT).show();
            return;
        }

        simpanData();
    }

    private void simpanData() {
        pgDialog = new ProgressDialog(this);
        pgDialog.setMessage("Loading ..");
        pgDialog.setCanceledOnTouchOutside(false);
        pgDialog.setCancelable(false);
        pgDialog.show();
        karywan= (Karyawan) i.getSerializableExtra("sp");
        karywan.setEmail(email.getText().toString().trim());
        mKarywanRef.child(karywan.getNik()).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if(!dataSnapshot.exists()){
                    mKarywanRef.child(karywan.getNik()).setValue(karywan).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            creatAcoount(email.getText().toString().trim(), pass.getText().toString().trim());
                        }
                    });
                }else{
                    Toast.makeText(InputKarywanNext2.this, "Maaf data karywan dengan NIK itu sudah ada!", Toast.LENGTH_SHORT).show();
                    pgDialog.dismiss();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    public  void creatAcoount(String email, String password){
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "createUserWithEmail:success");

                            FirebaseUser user = mAuth.getCurrentUser();
                            mAuth.signOut();

                            SharedPreferences sp = getSharedPreferences("LOGIN_ACCOUNT", MODE_PRIVATE);
                            String email = sp.getString("email", "");
                            String password = sp.getString("password", "");

                            Log.d("EMAIL LOCAL", email +" & "+password);

                            if(!email.equals("")&& !password.equals("") ){
                                mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                    @Override
                                    public void onComplete(@NonNull Task<AuthResult> task) {
                                        pgDialog.dismiss();
                                        Toast.makeText(InputKarywanNext2.this, "Data Karywan dan Akun login Berhasl Dimasukkan !", Toast.LENGTH_SHORT).show();
                                        Intent intent = new Intent(InputKarywanNext2.this, MainActivity.class);
                                        startActivity(intent);
                                        finish();
                                    }
                                }).addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        pgDialog.dismiss();
                                        Toast.makeText(InputKarywanNext2.this, "Data Karywan Berhasl Dimasukkan, Tapi Akun karyawan gagal dibuat !", Toast.LENGTH_SHORT).show();
                                        Intent intent = new Intent(InputKarywanNext2.this, MainActivity.class);
                                        startActivity(intent);
                                        finish();
                                    }
                                });
                            }else{
                                pgDialog.dismiss();
                                Toast.makeText(InputKarywanNext2.this, "Data Karywan Berhasl Dimasukkan, Tapi Akun karyawan gagal dibuat !", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(InputKarywanNext2.this, MainActivity.class);
                                startActivity(intent);
                                finish();
                            }



                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "createUserWithEmail:failure", task.getException());
                            pgDialog.dismiss();
                            Toast.makeText(InputKarywanNext2.this, "Membuat akun karywan gagal",
                                    Toast.LENGTH_SHORT).show();

                        }

                        // ...
                    }
                });

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // handle arrow click here
        if (item.getItemId() == android.R.id.home) {
            finish(); // close this activity and return to preview activity (if there is any)
        }

        return super.onOptionsItemSelected(item);
    }
}

