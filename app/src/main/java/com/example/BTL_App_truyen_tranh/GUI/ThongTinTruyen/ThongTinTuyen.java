package com.example.BTL_App_truyen_tranh.GUI.ThongTinTruyen;

import static com.example.BTL_App_truyen_tranh.SQL.ChapTruyen.getall_chap;
import static com.example.BTL_App_truyen_tranh.SQL.TruyenTranh.get_truyentranh;
import static com.example.BTL_App_truyen_tranh.GUI.Home.HomePage.sqLite1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.BTL_App_truyen_tranh.BUS.Utils;
import com.example.BTL_App_truyen_tranh.DTO.Chap;
import com.example.BTL_App_truyen_tranh.DTO.TruyenTranh;
import com.example.BTL_App_truyen_tranh.R;

import java.util.List;

public class ThongTinTuyen extends AppCompatActivity {
    private TextView text_ten_truyen, text_time, text_tinhtrang, text_slchap, textTenTheLoai, text_gioithieu;
    private ImageView image_truyen,img_back;
    private TruyenTranh truyenTranh;
    private ListView list_item;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thong_tin_tuyen);
        Intent intent = getIntent();
        text_ten_truyen = findViewById(R.id.text_ten_truyen);
        text_time = findViewById(R.id.text_time);
        text_tinhtrang = findViewById(R.id.text_tinhtrang);
        text_slchap = findViewById(R.id.text_slchap);
        textTenTheLoai = findViewById(R.id.textTenTheLoai);
        text_gioithieu = findViewById(R.id.text_gioithieu);
        image_truyen = findViewById(R.id.image_truyen);
        list_item = findViewById(R.id.list_item);
        img_back = findViewById(R.id.img_back);
        img_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        if (intent.getIntExtra("Key_idTruyen", 0) != 0) {
            truyenTranh = get_truyentranh(intent.getIntExtra("Key_idTruyen", 0), sqLite1);
        }
        List<Chap> list = getall_chap(truyenTranh.getIdTruyen(), sqLite1);
        text_ten_truyen.setText(truyenTranh.getTenTruyen());
        text_time.setText(truyenTranh.getNgayDang());
        text_tinhtrang.setText(truyenTranh.getTinhTrang());
        text_slchap.setText(list.size() + " Chap");
        textTenTheLoai.setText(truyenTranh.getTheLoai());
        text_gioithieu.setText(truyenTranh.getGioiThieu());
        image_truyen.setImageBitmap(Utils.getImage(truyenTranh.getImg()));

        ArrayAdapter<Chap> arrayAdapter
                = new ArrayAdapter<Chap>(this, android.R.layout.simple_list_item_1, list);

        list_item.setAdapter(arrayAdapter);
        list_item.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Chap chap=list.get(i);
                Intent intent = new Intent(ThongTinTuyen.this, ChapTruyenTranh.class);
                intent.putExtra("Key_tenChap", chap.getTenChap());
                intent.putExtra("Key_idTruyen", chap.getIdtt());
                startActivity(intent);
            }
        });

    }
}