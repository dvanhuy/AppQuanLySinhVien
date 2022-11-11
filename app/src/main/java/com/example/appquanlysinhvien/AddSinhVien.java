package com.example.appquanlysinhvien;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;

public class AddSinhVien extends AppCompatActivity {

    ImageView back;
    EditText masv,tensv,lopsv,diemtoan,diemtin,diemta;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_sinh_vien);

        back = findViewById(R.id.buttonback);
        masv = findViewById(R.id.addmasv);
        tensv = findViewById(R.id.addtensv);
        lopsv = findViewById(R.id.addlopsv);
        diemtoan = findViewById(R.id.adddiemtoan);
        diemtin = findViewById(R.id.adddiemtin);
        diemta = findViewById(R.id.adddiemta);

    }
}