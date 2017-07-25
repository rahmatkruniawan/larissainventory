package com.inventory.larissa.larissainventory;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;

public class InputBarang extends AppCompatActivity {


    private DatePicker datePicker;
    private Calendar calendar;
    private TextView dateView;
    private int year, month, day;

    EditText noBarang, namaBarang , kodeBarang, qtyBarang, namaSupplier, kodeSupplier, telpSuplier;
    Button btnSave;
    ProgressDialog progressDialog;

    DatabaseReference mRootRef = FirebaseDatabase.getInstance().getReference();
    DatabaseReference mBarangRef = mRootRef.child("barang");

    String tanggal, bulan ,tahun;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_barang);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Larissa Inventory");

        dateView = (TextView) findViewById(R.id.tanggalMasuk);
        noBarang = (EditText) findViewById(R.id.noBarangMasuk);
        namaBarang = (EditText) findViewById(R.id.namaBarang);
        kodeBarang= (EditText) findViewById(R.id.kodeBarang);
        qtyBarang= (EditText) findViewById(R.id.qtyBarang);
        kodeSupplier= (EditText) findViewById(R.id.kodeSupplier);
        namaSupplier= (EditText) findViewById(R.id.namaSupplier);
        telpSuplier= (EditText) findViewById(R.id.telpSupplier);
        btnSave = (Button) findViewById(R.id.saveBarang);
        calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);

        month = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DAY_OF_MONTH);
        showDate(year, month+1, day);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                attempSubmit();
            }
        });

    }

    public void attempSubmit(){
        if(noBarang.getText().toString().trim().equals("")){
            Toast.makeText(this, "No Barang harus disii", Toast.LENGTH_SHORT).show();
            return;
        }
        if(namaBarang.getText().toString().trim().equals("")){
            Toast.makeText(this, "Nama Barang harus disii", Toast.LENGTH_SHORT).show();
            return;
        }

        if(kodeBarang.getText().toString().trim().equals("")){
            Toast.makeText(this, "Nama Barang harus disii", Toast.LENGTH_SHORT).show();
            return;
        }

        if(qtyBarang.getText().toString().trim().equals("")){
            Toast.makeText(this, "Jumlah barang harus disii", Toast.LENGTH_SHORT).show();
            return;
        }
        if(namaSupplier.getText().toString().trim().equals("")){
            Toast.makeText(this, "Nama suplier harus disii", Toast.LENGTH_SHORT).show();
            return;
        }
        if(kodeSupplier.getText().toString().trim().equals("")){
            Toast.makeText(this, "Kode suplier harus disii", Toast.LENGTH_SHORT).show();
            return;
        }

        if(telpSuplier.getText().toString().trim().equals("")){
            Toast.makeText(this, "Telp suplier harus disii", Toast.LENGTH_SHORT).show();
            return;
        }

        if(dateView.getText().toString().trim().equals("")){
            Toast.makeText(this, "Tangal masuk barang harus disii", Toast.LENGTH_SHORT).show();
            return;
        }

        saveBarang();

    }

    public void saveBarang(){
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading ...");
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.setCancelable(false);
        progressDialog.show();

        Barang barang = new Barang(kodeBarang.getText().toString().trim(),noBarang.getText().toString().trim(),kodeBarang.getText().toString().trim(), namaBarang.getText().toString().trim(),
                qtyBarang.getText().toString().trim(),dateView.getText().toString().trim(),kodeSupplier.getText().toString().trim(),namaSupplier.getText().toString().trim(),telpSuplier.getText().toString().trim(),
                tanggal, bulan , tahun);

        mBarangRef.child(kodeBarang.getText().toString().trim()).setValue(barang).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                progressDialog.dismiss();
                Toast.makeText(InputBarang.this, "Data berhasil diinputkan.", Toast.LENGTH_SHORT).show();
                finish();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                progressDialog.dismiss();
                Toast.makeText(InputBarang.this, "Data gagal diinputkan.", Toast.LENGTH_SHORT).show();
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
                    showDate(arg1, arg2+1, arg3);
                }
            };

    private void showDate(int year, int month, int day) {
        tanggal = day+"";
        bulan = month+"";
        tahun = year+"";
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
