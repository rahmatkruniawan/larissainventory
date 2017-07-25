package com.inventory.larissa.larissainventory;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class DetailKaryawan extends AppCompatActivity {

    TextView nik, jabatan , department, pendidikan ;
    TextView nama, ttl, agama, jk , status;
    TextView alamat, kodepos, telp;
    ImageView iconCall , iconAlamat;

    Intent i ;
    Karyawan karyawan;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_karyawan);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Larissa Inventory");

        nik = (TextView) findViewById(R.id.nik);
        jabatan = (TextView) findViewById(R.id.jabatan);
        department= (TextView) findViewById(R.id.department);
        pendidikan = (TextView) findViewById(R.id.pendidikan);
        nama= (TextView) findViewById(R.id.nama);
        ttl = (TextView) findViewById(R.id.ttl);
        agama = (TextView) findViewById(R.id.agama);
        jk= (TextView) findViewById(R.id.jk);
        status= (TextView) findViewById(R.id.status);
        alamat= (TextView) findViewById(R.id.alamat);
        kodepos= (TextView) findViewById(R.id.kodepos);
        telp= (TextView) findViewById(R.id.telp);
        iconCall= (ImageView) findViewById(R.id.iconCall);
        iconAlamat= (ImageView) findViewById(R.id.iconalamat);

        i = getIntent();
        if(i.getSerializableExtra("sp") != null){
            karyawan = (Karyawan) i.getSerializableExtra("sp");

            nik.setText(karyawan.getNik());
            jabatan .setText(karyawan.jabatan);
            department.setText(karyawan.getDepartment());
            pendidikan.setText(karyawan.getPendidikan());
            nama.setText(karyawan.getNama());
            ttl.setText(karyawan.getTemplatlahir()+", "+karyawan.getTgl());
            agama.setText(karyawan.getAgama());
            jk.setText(karyawan.getJk());
            status.setText(karyawan.getStatus());
            alamat.setText(karyawan.getAlamat()+", "+karyawan.getKelurahan()+", "+karyawan.getKota()+", "+karyawan.getProvinsi());
            kodepos.setText(karyawan.kodepos);
            telp.setText(karyawan.getNohp());


            if(!karyawan.equals("")){
                iconCall.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String dial = "tel:" + karyawan.getNohp();
                        startActivity(new Intent(Intent.ACTION_DIAL, Uri.parse(dial)));
                    }
                });

            }
            if(!alamat.getText().toString().equals("")){
                iconAlamat.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String map = "http://maps.google.co.in/maps?q=" + alamat.getText().toString().trim();
                        Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse(map));
                        startActivity(i);
                    }
                });

            }
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

