package com.example.sistempakar_umurkucing;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class Bantuan extends AppCompatActivity {

    AppCompatButton btnWa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bantuan);

        Toolbar mMyToolbar = (Toolbar) findViewById(R.id.toolbar);
        btnWa = findViewById(R.id.sendWa);

        final String num = "+6281548597011";
        final String text = "Halo Admin, saya perlu bantuan terkait sistem pakar ... ";

        mMyToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (getFragmentManager().getBackStackEntryCount() > 0) {
                    getFragmentManager().popBackStack();
                    return;
                }
                Bantuan.super.onBackPressed();
            }
        });

        btnWa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("http://api.whatsapp.com/send?phone="+num+"&text="+text));
                startActivity(intent);
            }
        });

    }

}