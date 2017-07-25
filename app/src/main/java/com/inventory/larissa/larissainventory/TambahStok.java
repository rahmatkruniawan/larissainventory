package com.inventory.larissa.larissainventory;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class TambahStok extends AppCompatActivity {

    DatabaseReference mRootRef = FirebaseDatabase.getInstance().getReference();
    DatabaseReference mBarangRef = mRootRef.child("barang");

    EditText qtyBarang;
    Button btnSave;

    Barang barang;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah_stok);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Larissa Inventory");

        qtyBarang= (EditText) findViewById(R.id.qtyBarang);
        btnSave = (Button) findViewById(R.id.saveBarang);

        Intent i = getIntent();
        if(i.getSerializableExtra("sp") != null) {
            barang = (Barang) i.getSerializableExtra("sp");

        }else{
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
        if(qtyBarang.getText().toString().equals("")){
            Toast.makeText(this, "Jumlah stok harus diisi!.", Toast.LENGTH_SHORT).show();
            return;
        }

        int totalStok = Integer.parseInt(barang.getQty())+ Integer.parseInt(qtyBarang.getText().toString().trim());
        mBarangRef.child(barang.getKodeBarang()).child("qty").setValue(totalStok+"");
    }
}
