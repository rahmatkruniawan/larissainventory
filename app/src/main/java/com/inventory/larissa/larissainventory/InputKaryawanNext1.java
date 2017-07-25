package com.inventory.larissa.larissainventory;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.Serializable;

public class InputKaryawanNext1 extends AppCompatActivity {

    EditText alamat, kelurahan , kota, provinsi , kodepos, pendidikan ,department, jabatan;
    Intent i ;
    Button btnLanjutkan ;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_karyawan_next1);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Larissa Inventory");

        alamat = (EditText) findViewById(R.id.alamat);
        kelurahan= (EditText) findViewById(R.id.kelurahan);
        kota= (EditText) findViewById(R.id.kota);
        provinsi= (EditText) findViewById(R.id.provinsi);
        kodepos= (EditText) findViewById(R.id.kodepos);
        pendidikan= (EditText) findViewById(R.id.pendidikan);
        department= (EditText) findViewById(R.id.department);
        jabatan= (EditText) findViewById(R.id.jabaran);
        btnLanjutkan= (Button) findViewById(R.id.btnLanjutkan);
        i = getIntent();
        if (i.getStringExtra("nik").toString() == null || i.getStringExtra("nik").equals("") ){
            finish();
        }

        btnLanjutkan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                attemptData();
            }
        });

    }

    public void attemptData(){
        if(alamat.getText().toString().trim().equals("")){
            Toast.makeText(this, "Alamat hars diisi!", Toast.LENGTH_SHORT).show();
            return;
        }
        if(kelurahan.getText().toString().trim().equals("")){
            Toast.makeText(this, "Alamat hars diisi!", Toast.LENGTH_SHORT).show();
            return;
        }

        if(kota.getText().toString().trim().equals("")){
            Toast.makeText(this, "Alamat hars diisi!", Toast.LENGTH_SHORT).show();
            return;
        }

        if(provinsi.getText().toString().trim().equals("")){
            Toast.makeText(this, "Alamat hars diisi!", Toast.LENGTH_SHORT).show();
            return;
        }

        if(kodepos.getText().toString().trim().equals("")){
            Toast.makeText(this, "Alamat hars diisi!", Toast.LENGTH_SHORT).show();
            return;
        }

        if(pendidikan.getText().toString().trim().equals("")){
            Toast.makeText(this, "Alamat hars diisi!", Toast.LENGTH_SHORT).show();
            return;
        }

        if(department.getText().toString().trim().equals("")){
            Toast.makeText(this, "Alamat hars diisi!", Toast.LENGTH_SHORT).show();
            return;
        }

        if(jabatan.getText().toString().trim().equals("")){
            Toast.makeText(this, "Alamat hars diisi!", Toast.LENGTH_SHORT).show();
            return;
        }

        gotoNext(i.getStringExtra("nik"),i.getStringExtra("nama"), i.getStringExtra("tanggallahir"),i.getStringExtra("tempatlahir"), i.getStringExtra("jeniskelamin"), i.getStringExtra("telp"),i.getStringExtra("agama"),
                alamat.getText().toString().trim(), kelurahan.getText().toString().trim(), kota.getText().toString().trim(), provinsi.getText().toString().trim(), kodepos.getText().toString().trim(),i.getStringExtra("status").toString().trim(), pendidikan.getText().toString().trim(), department.getText().toString().trim(), jabatan.getText().toString().trim());
    }

    private void gotoNext(String NIK, String nama, String tgl, String templatlahir, String jk, String nohp, String agama, String alamat, String kelurahan, String kota, String provinsi, String kodepos, String status, String pendidikan, String department, String jabatan) {

        Karyawan karyawan = new Karyawan(NIK, nama,tgl, templatlahir, jk, nohp,agama, alamat, kelurahan, kota, provinsi, kodepos, status, pendidikan, department, jabatan, "");

        Intent intent = new Intent(InputKaryawanNext1.this, InputKarywanNext2.class);
        intent.putExtra("sp", (Serializable) karyawan);
        startActivity(intent);
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
