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

public class AddSinhVien extends AppCompatActivity {

    ImageView back;
    EditText masv,tensv,lopsv,diemtoan,diemtin,diemta;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_sinh_vien);

        back = findViewById(R.id.buttonback);
        masv = findViewById(R.id.textmasv);
        tensv = findViewById(R.id.texttensv);
        lopsv = findViewById(R.id.textlopsv);
        diemtoan = findViewById(R.id.adddiemtoan);
        diemtin = findViewById(R.id.adddiemtin);
        diemta = findViewById(R.id.adddiemta);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setResult(RESULT_CANCELED);
                finish();
            }
        });

        Button add = findViewById(R.id.updatebutton);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText tensv = findViewById(R.id.texttensv);
                EditText masv = findViewById(R.id.textmasv);
                EditText lopsv = findViewById(R.id.textlopsv);
                EditText diemtoan = findViewById(R.id.adddiemtoan);
                EditText diemtin = findViewById(R.id.adddiemtin);
                EditText diemta = findViewById(R.id.adddiemta);
                if (    tensv.getText().toString().equals("") ||
                        masv.getText().toString().equals("")  ||
                        lopsv.getText().toString().equals("") ||
                        diemtoan.getText().toString().equals("") ||
                        diemtin.getText().toString().equals("") ||
                        diemta.getText().toString().equals("") )
                {
                    Toast.makeText(AddSinhVien.this,"Nhập thiếu thông tin",Toast.LENGTH_SHORT).show();
                }
                else {
                    SQLiteDatabase database = openOrCreateDatabase("quanlysinhvien.db", MODE_PRIVATE, null);
                    ContentValues sv = new ContentValues();
                    sv.put("masv",masv.getText().toString());
                    sv.put("tensv",tensv.getText().toString());
                    sv.put("lopsv",lopsv.getText().toString());
                    sv.put("dtoan",Float.valueOf(diemtoan.getText().toString()));
                    sv.put("dtin",Float.valueOf(diemtin.getText().toString()));
                    sv.put("dtienganh",Float.valueOf(diemta.getText().toString()));
                    if (database.insert("sinhvien",null,sv) == -1) {
                        Toast.makeText(AddSinhVien.this, "Fail to insert record", Toast.LENGTH_SHORT).show();
                    }
                    else
                    {
                        SinhVien newsv= new SinhVien(
                                masv.getText().toString(),
                                tensv.getText().toString(),
                                lopsv.getText().toString(),
                                Float.valueOf(diemtoan.getText().toString()),
                                Float.valueOf(diemtin.getText().toString()),
                                Float.valueOf(diemta.getText().toString()));
                        Bundle bundle = new Bundle();
                        bundle.putSerializable("newsv",newsv);
                        Intent resultIntent  = new Intent();
                        resultIntent.putExtra("bundle",bundle);
                        setResult(RESULT_OK,resultIntent);
                        finish();
                    }
                }

            }
        });



    }
}