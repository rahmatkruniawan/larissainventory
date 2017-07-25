package com.inventory.larissa.larissainventory;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;

public class InputKaryawan extends AppCompatActivity {

    EditText nik, nama, tempat, telp;
    private Spinner spinnerJK , spinnerStatus, spinnerAgama;
    private static final String[]paths = {"Laki-laki", "Perempuan"};
    private static final String[]statusList = {"Belum menikah", "Menikah"};
    private static final String[]agamaList = {"Islam", "Kristen", "Hindu", "Budha", "Konghuchu"};
    Button btnLanjutkan ;

    private Calendar calendar;
    private TextView dateView;
    private int year, month, day;

    String jenisKelamin= "Laki-laki", status = "Belum menikah", agama = "Islam";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_karyawan);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Larissa Inventory");

        spinnerJK = (Spinner)findViewById(R.id.spinnerKaryawan);
        spinnerStatus= (Spinner)findViewById(R.id.statusNikah);
        spinnerAgama = (Spinner)findViewById(R.id.agama);
        nik = (EditText) findViewById(R.id.nik);
        nama = (EditText) findViewById(R.id.nama);
        tempat = (EditText) findViewById(R.id.tempat);
        dateView = (TextView) findViewById(R.id.tanggalLahir);
        telp = (EditText) findViewById(R.id.telp);
        btnLanjutkan = (Button) findViewById(R.id.btnLanjutkan);

        calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);

        month = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DAY_OF_MONTH);
        showDate(year, month+1, day);


        setDropDownAdapterJK();
        setDropDownAdapterstatus();
        setDropDownAdapteragama();



        btnLanjutkan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                attemmpatData();
            }
        });
    }

    public void setDropDownAdapterJK(){
        ArrayAdapter<String>adapter = new ArrayAdapter<String>(InputKaryawan.this,
                android.R.layout.simple_spinner_item,paths);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerJK.setAdapter(adapter);
        spinnerJK.setSelection(getIndex(spinnerJK, jenisKelamin));
        spinnerJK.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                switch (position) {
                    case 0:
                        // Whatever you want to happen when the first item gets selected
                        jenisKelamin= "Laki-laki";
                        break;
                    case 1:
                        // Whatever you want to happen when the second item gets selected
                        jenisKelamin = "Perempuan";
                        break;

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    public void setDropDownAdapteragama(){
        ArrayAdapter<String>adapter = new ArrayAdapter<String>(InputKaryawan.this,
                android.R.layout.simple_spinner_item,agamaList);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerAgama.setAdapter(adapter);
        spinnerAgama.setSelection(getIndex(spinnerAgama, agama));
        spinnerAgama.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                switch (position) {
                    case 0:
                        // Whatever you want to happen when the first item gets selected
                        jenisKelamin= "Islam";
                        break;
                    case 1:
                        // Whatever you want to happen when the second item gets selected
                        jenisKelamin= "Kristen";
                        break;
                    case 2:
                        // Whatever you want to happen when the second item gets selected
                        jenisKelamin= "Hindu";
                        break;
                    case 3:
                        // Whatever you want to happen when the second item gets selected
                        jenisKelamin= "Budha";
                        break;
                    case 4:
                        // Whatever you want to happen when the second item gets selected
                        jenisKelamin= "Konghuchu";
                        break;

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
    public void setDropDownAdapterstatus(){
        ArrayAdapter<String>adapter = new ArrayAdapter<String>(InputKaryawan.this,
                android.R.layout.simple_spinner_item,statusList);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerStatus.setAdapter(adapter);
        spinnerStatus.setSelection(getIndex(spinnerStatus, status));
        spinnerStatus.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                switch (position) {
                    case 0:
                        // Whatever you want to happen when the first item gets selected
                        jenisKelamin= "Belum menikah";
                        break;
                    case 1:
                        // Whatever you want to happen when the second item gets selected
                        jenisKelamin = "Menikah";
                        break;

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }


    public void attemmpatData(){
        if(nik.getText().toString().trim().equals("")){
            Toast.makeText(this, "NIK harus dissi!", Toast.LENGTH_SHORT).show();
            return;
        }
        if(nama.getText().toString().trim().equals("")){
            Toast.makeText(this, "Nama harus dissi!", Toast.LENGTH_SHORT).show();
            return;
        }
        if(tempat.getText().toString().trim().equals("")){
            Toast.makeText(this, "Tempat lahir harus dissi!", Toast.LENGTH_SHORT).show();
            return;
        }

        if(telp.getText().toString().trim().equals("")){
            Toast.makeText(this, "No Hplahir harus dissi!", Toast.LENGTH_SHORT).show();
            return;
        }


        goToNext(nik.getText().toString().trim(),nama.getText().toString().trim(), tempat.getText().toString().trim(),telp.getText().toString().trim() );


    }

    public void goToNext(String nik, String nama, String tempat, String telp){
        Intent intent = new Intent(InputKaryawan.this, InputKaryawanNext1.class);
        intent.putExtra("nik", nik);
        intent.putExtra("nama", nama);
        intent.putExtra("tempatlahir", tempat);
        intent.putExtra("tanggallahir", dateView.getText().toString().trim());
        intent.putExtra("jeniskelamin", jenisKelamin);
        intent.putExtra("status", status);
        intent.putExtra("telp", telp);
        intent.putExtra("agama", agama);
        startActivity(intent);
    }



    //private method of your class
    private int getIndex(Spinner spinner, String myString)
    {
        int index = 0;

        for (int i=0;i<spinner.getCount();i++){
            if (spinner.getItemAtPosition(i).toString().equalsIgnoreCase(myString)){
                index = i;
                break;
            }
        }
        return index;
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

