package com.example.appquanlysinhvien;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class ChiTietSV extends AppCompatActivity {

    EditText updatemasv,updatetensv,updatelopsv,updatetoan,updatetin,updatetienganh,updatetrungbinh;
    SinhVien svien;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chi_tiet_sv);
        //nut back
        ImageView buttonback = findViewById(R.id.buttonback);
        buttonback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setResult(RESULT_CANCELED);
                finish();
            }
        });
        updatemasv =(EditText) findViewById(R.id.textmasv);
        updatetensv =(EditText) findViewById(R.id.texttensv);
        updatelopsv =(EditText) findViewById(R.id.textlopsv);
        updatetoan =(EditText) findViewById(R.id.texttoan);
        updatetin =(EditText) findViewById(R.id.texttin);
        updatetienganh =(EditText) findViewById(R.id.texttienganh);
        updatetrungbinh =(EditText) findViewById(R.id.texttrungbinh);

        Bundle bundle = getIntent().getBundleExtra("bundle");
        svien = (SinhVien) bundle.getSerializable("svdcchon");

        updatemasv.setText(svien.getMasv());
        updatetensv.setText(svien.getTensv()) ;
        updatelopsv.setText(svien.getLopsv())  ;
        updatetoan.setText(String.valueOf(svien.getdToan())) ;
        updatetin.setText(String.valueOf(svien.getdTin())) ;
        updatetienganh.setText(String.valueOf(svien.getdTiengAnh()));
        updatetrungbinh.setText(String.valueOf(svien.getTrungBinh()));

        Button updatebutton = findViewById(R.id.updatebutton);
        updatebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (    updatemasv.getText().toString().equals("") ||
                        updatetensv.getText().toString().equals("")  ||
                        updatelopsv.getText().toString().equals("") ||
                        updatetoan.getText().toString().equals("") ||
                        updatetin.getText().toString().equals("") ||
                        updatetienganh.getText().toString().equals("") )
                {
                    Toast.makeText(ChiTietSV.this,"Nhập thiếu thông tin",Toast.LENGTH_SHORT).show();
                }
                else {
                    SQLiteDatabase database = openOrCreateDatabase("quanlysinhvien.db", MODE_PRIVATE, null);
                    ContentValues sv = new ContentValues();
                    sv.put("masv",updatemasv.getText().toString());
                    sv.put("tensv",updatetensv.getText().toString());
                    sv.put("lopsv",updatelopsv.getText().toString());
                    sv.put("dtoan",Float.valueOf(updatetoan.getText().toString()));
                    sv.put("dtin",Float.valueOf(updatetin.getText().toString()));
                    sv.put("dtienganh",Float.valueOf(updatetienganh.getText().toString()));

                    if (database.update("sinhvien",sv,"masv=?",new String[]{svien.getMasv()}) == -1) {
                        Toast.makeText(ChiTietSV.this, "Fail to update", Toast.LENGTH_SHORT).show();
                    }
                    else
                    {
//                        SinhVien newsv= new SinhVien(
//                                updatemasv.getText().toString(),
//                                updatetensv.getText().toString(),
//                                updatelopsv.getText().toString(),
//                                Float.valueOf(updatetoan.getText().toString()),
//                                Float.valueOf(updatetin.getText().toString()),
//                                Float.valueOf(updatetienganh.getText().toString()));
//                        Bundle bundle = new Bundle();
//                        bundle.putSerializable("newsv",newsv);
//                        Intent resultIntent  = new Intent();
//                        resultIntent.putExtra("bundle",bundle);
                        setResult(RESULT_OK);
                        finish();
                    }
                }
            }
        });
    }
}