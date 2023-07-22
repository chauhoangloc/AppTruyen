package com.example.BTL_App_truyen_tranh.GUI.Home;

import static com.example.BTL_App_truyen_tranh.SQL.TheLoai.getall_tl;
import static com.example.BTL_App_truyen_tranh.SQL.TruyenTranh.getall_tt;
import static com.example.BTL_App_truyen_tranh.SQL.TruyenTranh.timkiem_tt;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.TextView;

import com.example.BTL_App_truyen_tranh.SQL.SQLite;
import com.example.BTL_App_truyen_tranh.R;

public class HomePage extends AppCompatActivity {
    private RecyclerView list_item_truyen;
    private RecyclerView list_the_loai;
    public static SQLite sqLite1;
    private TextView text_name;
    private EditText timkiem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        list_item_truyen = findViewById(R.id.list_item_truyen);
        list_the_loai = findViewById(R.id.list_the_loai);
        text_name = findViewById(R.id.text_name);
        sqLite1 = new SQLite(this);
        sqLite1.getdatatl();
        sqLite1.getdataTruyenTranh();
        sqLite1.getdataChap();
        sqLite1.getdataImgChap();
        Intent intent = getIntent();
        text_name.setText(intent.getStringExtra("Key_hoten"));
        timkiem = findViewById(R.id.timkiem);
        //Khởi tạo GridLayoutManager Có số cột là 2
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2);
        //Chuyền Layout Manager cho list_item_truyen
        list_item_truyen.setLayoutManager(gridLayoutManager);
        timkiem.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (timkiem.getText().toString().trim().isEmpty()) {
                    GetListTruyen();
                } else {
                    //Khởi tạo HomeQlItemTruyen
                    HomeItemTruyen homeItemTruyen = new HomeItemTruyen(timkiem_tt(timkiem.getText().toString().trim(), sqLite1),HomePage.this);
                    //Chuyền adapter cho list_item_truyen
                    list_item_truyen.setAdapter(homeItemTruyen);
                }
            }
        });



        //Khởi tạo LinearLayoutManager cuộn ngang
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        //Chuyền linearLayoutManager cho list_item_truyen
        list_the_loai.setLayoutManager(linearLayoutManager);
        //Khởi tạo HomeItemTheloai
        HomeItemTheloai homeItemTheloai = new HomeItemTheloai(getall_tl(sqLite1), this);
        //Chuyền Adapter homeItemTheloai cho list_the_loai
        list_the_loai.setAdapter(homeItemTheloai);

        GetListTruyen();
    }

    public void GetListTruyen() {
        //Khởi tạo HomeItemTruyen
        HomeItemTruyen homeItemTruyen = new HomeItemTruyen(getall_tt(sqLite1), this);
        //Chuyền adapter cho list_item_truyen
        list_item_truyen.setAdapter(homeItemTruyen);
    }


}