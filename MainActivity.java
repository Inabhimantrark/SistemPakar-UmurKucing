package com.example.sistempakar_umurkucing;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    AppCompatButton btnBack;
    LinearLayout btnKriteria, btnBantuan, btnKonsultasi, btnTips;

    TextView fullname;
    TextView status;
    TextView phone;
    TextView username;
    TextView alamat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnBack = findViewById(R.id.back);
        btnKriteria = findViewById(R.id.kriteria);
        btnBantuan = findViewById(R.id.bantuan);
        btnKonsultasi = findViewById(R.id.konsultasi);
        btnTips = findViewById(R.id.tips);

        fullname = findViewById(R.id.txtNama);
        phone = findViewById(R.id.txtPhone);

        String name = String.valueOf(fullname);
        String number = String.valueOf(phone);

        showAllUserData();

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, WelcomeScreen.class));
            }
        });

        btnKriteria.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, KriteriaUmur.class));
            }
        });

        btnBantuan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, Bantuan.class));
            }
        });

        btnKonsultasi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(MainActivity.this, Konsultasi.class));

            }
        });

        btnTips.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, Tips.class));
            }
        });
    }

    private void showAllUserData() {

        Intent intent = getIntent();
        String user_name = intent.getStringExtra("name");
        String user_status = intent.getStringExtra("status");
        String user_phone = intent.getStringExtra("phone");
        String user_username = intent.getStringExtra("username");
        String user_alamat = intent.getStringExtra("alamat");

        fullname.setText("HI, " + user_name);
        phone.setText(user_phone);
    }
}