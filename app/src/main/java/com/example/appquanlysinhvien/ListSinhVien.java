package com.example.appquanlysinhvien;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.utils.widget.ImageFilterView;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;

public class ListSinhVien extends AppCompatActivity {

    ArrayList<SinhVien> listsv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_sinh_vien);
        ImageView back = findViewById(R.id.imageViewback);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        ImageFilterView update = findViewById(R.id.imageView5);
        ImageFilterView add = findViewById(R.id.imageView4);

        Intent datalopsv = getIntent();
        String lopsv = datalopsv.getStringExtra("class");

        SQLiteDatabase database = openOrCreateDatabase("quanlysinhvien.db",MODE_PRIVATE,null);
        Cursor cursor = database.rawQuery("select * from sinhvien Where lopsv="+lopsv, null);
        cursor.moveToFirst();
        listsv  = new ArrayList<>();
        while(cursor.isAfterLast()==false)
        {
            Toast.makeText(this, cursor.getString(1), Toast.LENGTH_SHORT).show();
            cursor.moveToNext();
        }
        cursor.close();
    }
}