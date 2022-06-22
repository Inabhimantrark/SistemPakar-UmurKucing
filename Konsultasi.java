package com.example.sistempakar_umurkucing;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.Toolbar;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;

import java.io.File;

public class Konsultasi extends AppCompatActivity {

    AppCompatButton btnDiagnosa, btnRiwayat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_konsultasi);

        Toolbar mMyToolbar = (Toolbar) findViewById(R.id.toolbar);

        btnDiagnosa = findViewById(R.id.ListDiagnosa);
        btnRiwayat = findViewById(R.id.ListRiwayat);

        mMyToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (getFragmentManager().getBackStackEntryCount() > 0) {
                    getFragmentManager().popBackStack();
                    return;
                }
                Konsultasi.super.onBackPressed();

            }
        });

        btnDiagnosa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Konsultasi.this, DiagnosaPakar.class));
            }
        });

//        btnRiwayat.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                openfolder();
//            }
//        });


    }

//    private void openfolder() {
//        String filename = "hasildiagnosa.pdf";
//
//        File file = new File(getExternalFilesDir(null).getAbsolutePath() +"/"+ filename);
//        Intent target = new Intent(Intent.ACTION_VIEW);
//        target.setDataAndType(Uri.fromFile(file),"application/pdf");
//        target.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
//
//        Intent intent = Intent.createChooser(target, "Open File");
//        try {
//            startActivity(intent);
//        } catch (ActivityNotFoundException e) {
//            // Instruct the user to install a PDF reader here, or something
//        }
//    }

//    public void onBackPressed() {
//        Intent startMain = new Intent(Intent.ACTION_MAIN);
//        startMain.addCategory(Intent.CATEGORY_HOME);
//        startMain.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//        startActivity(startMain);
//    }
}