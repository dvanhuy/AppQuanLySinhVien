package com.example.appquanlysinhvien;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class ListLop extends AppCompatActivity {

    SQLiteDatabase database;
    ListView listclass;
    ArrayList<String> strings;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_lop);
        database = openOrCreateDatabase("quanlysinhvien.db",MODE_PRIVATE,null);
        if (!isTableExists(database,"sinhvien")){
            doCreateDB(database);
        }
        Cursor cursor = database.rawQuery("select DISTINCT lopsv from sinhvien", null);
        cursor.moveToFirst();
        strings  = new ArrayList<>();
        while(cursor.isAfterLast()==false)
        {
            strings.add(cursor.getString(0));
            cursor.moveToNext();
        }
        cursor.close();
        listclass= findViewById(R.id.listclass);
        ArrayAdapter adapter = new ArrayAdapter(
                ListLop.this,
                android.R.layout.simple_list_item_1,
                strings);
        listclass.setAdapter(adapter);

        listclass.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(ListLop.this,ListSinhVien.class);
                intent.putExtra("class",strings.get(i));
            }
        });

    }

    public boolean isTableExists(SQLiteDatabase database, String tableName) {
        Cursor cursor = database.rawQuery("select DISTINCT tbl_name " +
                "from sqlite_master where tbl_name = '"+tableName+"'", null);
        if(cursor!=null) {
            if(cursor.getCount()>0) {
                cursor.close();
                return true;
            }
            cursor.close();
        }
        return false;
    }

    public void doCreateDB(SQLiteDatabase data){
        String init_table = "create table sinhvien (" +
                "masv varchar(50) primary key," +
                "tensv text," +
                "lopsv varchar(50)," +
                "dtoan float," +
                "dtin float," +
                "dtienganh float)";

        data.execSQL(init_table);

        addRowSinhVien("2050531200157","Đinh Văn Huy","20T1",8,7,5);
        addRowSinhVien("2050531200123","Lê Đức Duy","20T1",5,7,5);
        addRowSinhVien("2050531200131","Nguyễn Trí Đức","20T1",9,6,9);
        addRowSinhVien("2050531200121","Nguyễn Tiến An","20T1",10,10,9);
        addRowSinhVien("2050531200101","Nguyễn Vãn A","20T2",6,9,7);
        addRowSinhVien("2050531200102","Phạm Văn B","20T2",7,7,5);
        addRowSinhVien("2050531200103","Phạm Văn C","20T2",9,4,5);
        addRowSinhVien("2050531200104","Phạm Đình Bé","20T3",7,8,5);
        addRowSinhVien("2050531200105","Võ Văn B","20T3",7,9,5);
        addRowSinhVien("2050531200106","Trần Văn B","20T3",7,7,5);
        addRowSinhVien("2050531200107","Văn Huy","20T3",8,7,5);
    }

    public void addRowSinhVien(String masv, String tensv, String lopsv, float dToan, float dTin, float dTiengAnh){
        ContentValues sv = new ContentValues();
        sv.put("masv",masv);
        sv.put("tensv",tensv);
        sv.put("lopsv",lopsv);
        sv.put("dtoan",dToan);
        sv.put("dtin",dTin);
        sv.put("dtienganh",dTiengAnh);
        if (database.insert("sinhvien",null,sv) == -1) {
            Toast.makeText(this, "Fail to insert record", Toast.LENGTH_SHORT).show();
        }
    }

//    public void initdata(){
//        SQLiteDatabase database = openOrCreateDatabase("quanlymaytinh.db", MODE_PRIVATE, null);
//        Cursor cur=database.query("computer", null, null, null, null, null, null);
//        cur.moveToFirst();
//        arrayBook = new ArrayList();
//        while(cur.isAfterLast()==false)
//        {
//            Book b=new Book();
//            b.setTen(cur.getString(0));
//            b.setCpu(cur.getString(1));
//            b.setRam(cur.getString(2));
//            b.setLoai(cur.getString(3));
//            arrayBook.add(b);
//            cur.moveToNext();
//        }
//        cur.close();
//    }
}