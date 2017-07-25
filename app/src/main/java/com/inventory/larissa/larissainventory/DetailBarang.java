package com.inventory.larissa.larissainventory;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.Serializable;

public class DetailBarang extends AppCompatActivity {

    TextView noBarang , kodeBarang, namaBarang, qtyBarang, tanggalBarang,
        kodeSuppllier, namaSupplier, telpSupplier;
    ImageView iconCall;
    Barang barang;

    DatabaseReference mRootRef = FirebaseDatabase.getInstance().getReference();
    DatabaseReference mBarangRef = mRootRef.child("barang");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_barang);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Larissa Inventory");

        noBarang = (TextView) findViewById(R.id.noBarangDetail);
        kodeBarang = (TextView) findViewById(R.id.kodeBarangDetail);
        namaBarang = (TextView) findViewById(R.id.namaBarangDetail);
        qtyBarang= (TextView) findViewById(R.id.qtyBarangDetail);
        tanggalBarang = (TextView) findViewById(R.id.tanggalMasukDetail);
        kodeSuppllier = (TextView) findViewById(R.id.kodeSuplierDetail);
        namaSupplier= (TextView) findViewById(R.id.namaSuplierDetail);
        telpSupplier= (TextView) findViewById(R.id.telpSuplierDetail);
        iconCall = (ImageView) findViewById(R.id.iconCall);

        Intent i = getIntent();
        if(i.getSerializableExtra("sp") != null){
            barang = (Barang) i.getSerializableExtra("sp");

            noBarang.setText(barang.getNoBarang());
            kodeBarang.setText(barang.getKodeBarang());
            namaBarang.setText(barang.getNamaBarang());
            qtyBarang.setText(barang.getQty());
            tanggalBarang.setText(barang.getTglMasuk());
            kodeSuppllier.setText(barang.getKodeSuplier());
            namaSupplier.setText(barang.getNamaSuplier());
            telpSupplier.setText(barang.getTelpSuplier());

            if(!barang.getTelpSuplier().equals("")){
                iconCall.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String dial = "tel:" + barang.getTelpSuplier();
                        startActivity(new Intent(Intent.ACTION_DIAL, Uri.parse(dial)));
                    }
                });

            }
        }
    }



    public void deleteBarang(){
        new AlertDialog.Builder(this)
                .setTitle("Larissa Inventory")
                .setMessage("Yakin menghapus barang ini?")
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        mBarangRef.child(barang.getId()).removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                Toast.makeText(DetailBarang.this, "Data barang berhasil dihapus!", Toast.LENGTH_SHORT).show();
                                finish();
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(DetailBarang.this, "Gagal hapus!.", Toast.LENGTH_SHORT).show();

                            }
                        });
                    }})
                .setNegativeButton(android.R.string.no, null).show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.option_menu, menu); //your file name
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // handle arrow click here
        if (item.getItemId() == android.R.id.home) {
            finish(); // close this activity and return to preview activity (if there is any)
        }
        else if(item.getItemId() == R.id.edit){
            Intent intent = new Intent(DetailBarang.this, EditBarang.class);
            intent.putExtra("sp", (Serializable) barang);
            startActivity(intent);

        }
        else if(item.getItemId() == R.id.hapus){
            deleteBarang();
        }
        else if(item.getItemId() == R.id.tambah_stok){

            Intent intent = new Intent(DetailBarang.this, TambahStok.class);
            intent.putExtra("sp", (Serializable) barang);
            startActivity(intent);
        }


        return super.onOptionsItemSelected(item);
    }
}
