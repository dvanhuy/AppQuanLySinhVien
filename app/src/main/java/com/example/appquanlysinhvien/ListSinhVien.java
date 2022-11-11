package com.example.appquanlysinhvien;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.utils.widget.ImageFilterView;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class ListSinhVien extends AppCompatActivity {

    ListView dssinhvien;
    ArrayList<SinhVien> listsv;
    ListViewAdapter listViewAdapter;
    String lopsinhvien;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_sinh_vien);

        ImageView back = findViewById(R.id.imageViewback);
        ImageFilterView update = findViewById(R.id.imageView5);
        ImageFilterView add = findViewById(R.id.imageView4);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ListSinhVien.this,AddSinhVien.class);
                getResultAdd.launch(intent);
            }
        });

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(ListSinhVien.this, "Nhấn vào sinh viên để sửa", Toast.LENGTH_SHORT).show();
            }
        });

        dssinhvien = findViewById(R.id.listsinhvien);
        listsv  = new ArrayList<>();

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        Intent datalopsv = getIntent();
        lopsinhvien = datalopsv.getStringExtra("class");
        SQLiteDatabase database = openOrCreateDatabase("quanlysinhvien.db",MODE_PRIVATE,null);
//        getdatatheolop
        Cursor cursor = database.rawQuery("select * from sinhvien where lopsv='"+lopsinhvien+"'", null);
        cursor.moveToFirst();
        while(cursor.isAfterLast()==false)
        {
            SinhVien newsv = new SinhVien();
            newsv.setMasv(cursor.getString(0));
            newsv.setTensv(cursor.getString(1));
            newsv.setLopsv(cursor.getString(2));
            newsv.setdToan(cursor.getFloat(3));
            newsv.setdTin(cursor.getFloat(4));
            newsv.setdTiengAnh(cursor.getFloat(5));
            listsv.add(newsv);
            cursor.moveToNext();
        }
        cursor.close();
        listViewAdapter = new ListViewAdapter(this,R.layout.item_sinhvien,listsv);
        dssinhvien.setAdapter(listViewAdapter);

        dssinhvien.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(ListSinhVien.this,ChiTietSV.class);

            }
        });
    }

    private ActivityResultLauncher<Intent> getResultAdd =registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if (result.getResultCode() == Activity.RESULT_OK){
                        Intent data = result.getData();
                        Bundle dataacc =  data.getBundleExtra("bundle");
                        SinhVien newsv = (SinhVien) dataacc.getSerializable("newsv");
                        if (lopsinhvien.equals(newsv.getLopsv()))
                        {
                            listsv.add(newsv);
                        }
                        listViewAdapter.notifyDataSetChanged();
                    }
                    if (result.getResultCode() == Activity.RESULT_CANCELED){
                    }
                }
            }
    );
}