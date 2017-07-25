package com.inventory.larissa.larissainventory;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

public class DetailRetur extends AppCompatActivity {

    TextView noBarang , kodeBarang, namaBarang, qtyBarang, tanggalBarang,
            keteranga, petugas;

    Retur retur;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_retur);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Larissa Inventory");

        noBarang = (TextView) findViewById(R.id.noBarangDetail);
        kodeBarang = (TextView) findViewById(R.id.kodeBarangDetail);
        namaBarang = (TextView) findViewById(R.id.namaBarangDetail);
        qtyBarang= (TextView) findViewById(R.id.qtyBarangDetail);
        tanggalBarang = (TextView) findViewById(R.id.tanggalMasukDetail);
        keteranga= (TextView) findViewById(R.id.keterang);
        petugas= (TextView) findViewById(R.id.petugas);

        Intent i = getIntent();
        if(i.getSerializableExtra("sp") != null) {
            retur = (Retur) i.getSerializableExtra("sp");

            noBarang.setText(retur.getNoRetur());
            kodeBarang.setText(retur.getKodeBarang());
            namaBarang.setText(retur.getNamaBarang());
            qtyBarang.setText(retur.getQty());
            tanggalBarang.setText(retur.getTanggal());
            keteranga.setText(retur.getKeterangan());
            petugas.setText(retur.getNamaKaryawan());
        }
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
