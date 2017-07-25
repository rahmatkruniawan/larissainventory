package com.inventory.larissa.larissainventory;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Calendar;

public class InputRetur extends AppCompatActivity {


    private DatePicker datePicker;
    private Calendar calendar;
    private TextView dateView;
    private int year, month, day;

    EditText noRetur, noBarang, namaBarang , kodeBarang, qtyBarang , keterangan;
    Button btnSave;
    ProgressDialog progressDialog;

    DatabaseReference mRootRef = FirebaseDatabase.getInstance().getReference();
    DatabaseReference mReturRef = mRootRef.child("retur");
    DatabaseReference mKaryawanRef = mRootRef.child("karyawan");
    private FirebaseAuth mAuth;

    String tanggal, bulan ,tahun;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_retur);

        mAuth = FirebaseAuth.getInstance();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Larissa Inventory");

        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser == null){
            Intent intent = new Intent(InputRetur.this, LoginActivity.class);
            startActivity(intent);
            finish();
        }

        dateView = (TextView) findViewById(R.id.tanggalMasuk);
        noBarang = (EditText) findViewById(R.id.noBarangMasuk);
        noRetur = (EditText) findViewById(R.id.noRetur);
        namaBarang = (EditText) findViewById(R.id.namaBarang);
        kodeBarang = (EditText) findViewById(R.id.kodeBarang);
        qtyBarang = (EditText) findViewById(R.id.qtyBarang);
        keterangan = (EditText) findViewById(R.id.keterang);
        btnSave = (Button) findViewById(R.id.saveBarang);
        calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);

        month = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DAY_OF_MONTH);
        showDate(year, month + 1, day);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                attempSubmit();
            }
        });

    }

    public void attempSubmit() {
        if (noRetur.getText().toString().trim().equals("")) {
            Toast.makeText(this, "No Retur harus disii", Toast.LENGTH_SHORT).show();
            return;
        }
        if (kodeBarang.getText().toString().trim().equals("")) {
            Toast.makeText(this, "Kode barang harus disii", Toast.LENGTH_SHORT).show();
            return;
        }

        if (namaBarang.getText().toString().trim().equals("")) {
            Toast.makeText(this, "Nama Barang harus disii", Toast.LENGTH_SHORT).show();
            return;
        }

        if (qtyBarang.getText().toString().trim().equals("")) {
            Toast.makeText(this, "Jumlah barang harus disii", Toast.LENGTH_SHORT).show();
            return;
        }


        if (keterangan.getText().toString().trim().equals("")) {
            Toast.makeText(this, "Keterangan harus disii", Toast.LENGTH_SHORT).show();
            return;
        }

        if (dateView.getText().toString().trim().equals("")) {
            Toast.makeText(this, "Tangal masuk barang harus disii", Toast.LENGTH_SHORT).show();
            return;
        }


        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading ...");
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.setCancelable(false);
        progressDialog.show();


        FirebaseUser currentUser = mAuth.getCurrentUser();
        String namaKaryawan = "admin";
        Log.d("USER LOGIN", currentUser.getEmail());
        if(!currentUser.getEmail().equals(getResources().getString(R.string.email_admin))){
            mKaryawanRef.orderByChild("email").equalTo(currentUser.getEmail()).addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {

                    if(dataSnapshot.hasChildren()) {
                    }
                    for (DataSnapshot children : dataSnapshot.getChildren()){

                        int i = 0;

                        if(i == 0){
                            saveBarang(children.child("email").getValue().toString());
                            i++;
                        }
                    }
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
        }
        else
            saveBarang(namaKaryawan);


    }

    public void saveBarang(String namaKaryawan) {


        Retur retur = new Retur(noRetur.getText().toString().trim(), kodeBarang.getText().toString().trim(), namaBarang.getText().toString().trim(), qtyBarang.getText().toString().trim(),
                dateView.getText().toString().trim(), keterangan.getText().toString().trim(), tanggal, bulan, tahun,namaKaryawan);

        mReturRef.child(noRetur.getText().toString().trim()).setValue(retur).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                progressDialog.dismiss();
                Toast.makeText(InputRetur.this, "Data berhasil diinputkan.", Toast.LENGTH_SHORT).show();
                finish();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                progressDialog.dismiss();
                Toast.makeText(InputRetur.this, "Data gagal diinputkan.", Toast.LENGTH_SHORT).show();
            }
        });

    }

    public void setDate(View view) {
        showDialog(999);
    }

    @Override
    protected Dialog onCreateDialog(int id) {
        // TODO Auto-generated method stub
        if (id == 999) {
            return new DatePickerDialog(this,
                    myDateListener, year, month, day);
        }
        return null;
    }

    private DatePickerDialog.OnDateSetListener myDateListener = new
            DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker arg0,
                                      int arg1, int arg2, int arg3) {
                    // TODO Auto-generated method stub
                    // arg1 = year
                    // arg2 = month
                    // arg3 = day
                    showDate(arg1, arg2 + 1, arg3);
                }
            };

    private void showDate(int year, int month, int day) {
        tanggal = day + "";
        bulan = month + "";
        tahun = year + "";
        dateView.setText(new StringBuilder().append(day).append("-")
                .append(month).append("-").append(year));
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