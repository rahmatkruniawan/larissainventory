package com.inventory.larissa.larissainventory;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

public class ListKaryawan extends AppCompatActivity {

    DatabaseReference mRootRef = FirebaseDatabase.getInstance().getReference();
    DatabaseReference mKaryawanRef = mRootRef.child("karyawan");


    ProgressDialog pgDialog;

    List<Karyawan> karyawanList = new LinkedList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_karyawan);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Larissa Inventory");

        ListView eventListView = (ListView) findViewById(R.id.eventListView);

        final ArrayAdapter<Karyawan> adapter = new ArrayAdapter<Karyawan>(
                this, R.layout.item_barang, karyawanList
        ){
            @Override
            public View getView(int position, View view, ViewGroup parent) {
                if( view == null){
                    view = ((Activity)getContext()).getLayoutInflater().inflate(R.layout.item_barang, parent, false);
                }

                Karyawan karyawan = karyawanList.get(position);

                ImageView image = (ImageView) view.findViewById(R.id.imageIcon);

                TextView Text1 = (TextView) view.findViewById(R.id.testingText1);
                TextView Text2 = (TextView) view.findViewById(R.id.testingText2);



                Text1.setText(karyawan.getNama());

                Text2.setText(karyawan.getJabatan());
                return view;
            }
        };
        eventListView.setAdapter(adapter);
        eventListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {

                Karyawan sp = karyawanList.get(position);
                Intent intent = new Intent(ListKaryawan.this, DetailKaryawan.class);
                intent.putExtra("sp", (Serializable) sp);
                startActivity(intent);
            }
        });



        pgDialog = new ProgressDialog(this);
        pgDialog.setCanceledOnTouchOutside(false);
        pgDialog.setMessage("Loading ..");
        pgDialog.show();

        mKaryawanRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                karyawanList.clear();
                adapter.notifyDataSetChanged();

                for (DataSnapshot child : dataSnapshot.getChildren()){

                    Karyawan sp = child.getValue(Karyawan.class);
                    karyawanList.add(sp);
                    Log.i("CHILD", child+"");
                    adapter.notifyDataSetChanged();
                }

                pgDialog.dismiss();

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

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
