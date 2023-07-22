package com.example.BTL_App_truyen_tranh.SQL;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class SQLite extends SQLiteOpenHelper {
    public SQLite(@Nullable Context context) {
        super(context, "DATASQLITE", null, 1);
    }

    public void get_data(String sql) {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        sqLiteDatabase.execSQL(sql);
    }

    public Cursor doc_data(String sql) {
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        return sqLiteDatabase.rawQuery(sql, null);
    }

    public void getdatatk() {
        get_data("create Table IF NOT EXISTS taikhoan(idtk INTEGER PRIMARY KEY AUTOINCREMENT,tk nvarchar(50), hoten nvarchar(50),mk nvarchar(50))");
    }

    public void getdatatl() {
        get_data("CREATE TABLE IF NOT EXISTS theloai(idtl INTEGER PRIMARY KEY AUTOINCREMENT,tentl text)");
    }

    public void getdataTruyenTranh() {
        get_data("create Table IF NOT EXISTS truyentranh(idtt INTEGER PRIMARY KEY AUTOINCREMENT,tenTruyen text,ngayDang text , tinhTrang text, theLoai text ,gioiThieu text, img blob)");
    }


    public void getdataChap() {
        get_data("create Table IF NOT EXISTS chap(idChap INTEGER PRIMARY KEY AUTOINCREMENT,idtt INTEGER,tenChap text)");
    }
    public void getdataImgChap() {
        get_data("create Table IF NOT EXISTS imgchap(idimgChap INTEGER PRIMARY KEY AUTOINCREMENT,idtt INTEGER,tenChap text,img blob)");
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

}