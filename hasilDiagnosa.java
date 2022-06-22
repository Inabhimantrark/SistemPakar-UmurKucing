package com.example.sistempakar_umurkucing;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.graphics.pdf.PdfDocument;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import static android.Manifest.permission.READ_EXTERNAL_STORAGE;
import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;

public class hasilDiagnosa extends AppCompatActivity {

    final LoadingDialog loadingDialog = new LoadingDialog(hasilDiagnosa.this);
    private static final int PERMISSION_REQUEST_CODE = 200;
    TextView tvUsia, tvKategori, tvPenjelasan;
    String usia=" ";
    String kategori=" ";
    String penjelasan=" ";
    AppCompatButton btnToPDF;
    Bitmap bmp, scaledbmp;
    int pageWidth = 1200;

    Date dateObj;
    DateFormat dateFormat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hasil_diagnosa);

        tvUsia = findViewById(R.id.hasilUsia);
        tvKategori = findViewById(R.id.kategoriUsia);
        tvPenjelasan = findViewById(R.id.Penjelasan);

        usia = getIntent().getStringExtra("usiaKucing");
        kategori = getIntent().getStringExtra("kategoriUsia");
        penjelasan = getIntent().getStringExtra("penjelasan");

        tvUsia.setText(usia);
        tvKategori.setText(kategori);
        tvPenjelasan.setText(penjelasan);

        btnToPDF = findViewById(R.id.btnPdf);

        bmp = BitmapFactory.decodeResource(getResources(), R.drawable.ic_cat_header);
        scaledbmp = Bitmap.createScaledBitmap(bmp, 1200, 518, false);

        Toolbar mMyToolbar = (Toolbar) findViewById(R.id.toolbar);

        mMyToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                Intent intent = new Intent(hasilDiagnosa.this, MainActivity.class);
//                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//                startActivity(intent);
                if (getFragmentManager().getBackStackEntryCount() > 0) {
                    getFragmentManager().popBackStack();
                    return;
                }
                hasilDiagnosa.super.onBackPressed();

            }
        });

        // below code is used for
        // checking our permissions.
        if (checkPermission()) {
            Toast.makeText(this, "Permission Granted", Toast.LENGTH_SHORT).show();
        } else {
            requestPermission();
        }

        btnToPDF.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                loadingDialog.startLoadingDialog();
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        // calling method to
                        // generate our PDF file.
                        generatePDF();
                        startActivity(new Intent(hasilDiagnosa.this,WelcomeScreen.class));
                    }
                },3000);



            }
        });
        
    }

    private void generatePDF() {

        dateObj = new Date();

        PdfDocument myPdfDocument = new PdfDocument();
        Paint myPaint = new Paint();
        Paint titlePaint = new Paint();

        PdfDocument.PageInfo myPageInfo = new PdfDocument.PageInfo.Builder(1200, 2010, 1).create();
        PdfDocument.Page myPage1 = myPdfDocument.startPage(myPageInfo);
        Canvas canvas = myPage1.getCanvas();

        canvas.drawBitmap(scaledbmp, 0,0, myPaint);

        titlePaint.setTextAlign(Paint.Align.CENTER);
        titlePaint.setTypeface(Typeface.create(Typeface.DEFAULT, Typeface.ITALIC));
        titlePaint.setTextSize(70);
        canvas.drawText("Surat Keterangan Diagnosa", pageWidth/2, 500, titlePaint);

        dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        titlePaint.setTextAlign(Paint.Align.CENTER);
        titlePaint.setTextSize(24);
        canvas.drawText("Date: "+ dateFormat.format(dateObj), pageWidth/2, 640, titlePaint);

        dateFormat = new SimpleDateFormat("HH:mm:ss");
        titlePaint.setTextAlign(Paint.Align.CENTER);
        titlePaint.setTextSize(24);
        canvas.drawText("Time: "+ dateFormat.format(dateObj),pageWidth/2, 690, titlePaint);

        titlePaint.setTextAlign(Paint.Align.CENTER);
        titlePaint.setTextSize(40);
        canvas.drawText("Usia Kucing Anda", pageWidth/2, 900, titlePaint);

        myPaint.setTextAlign(Paint.Align.CENTER);
        myPaint.setTypeface(Typeface.create(Typeface.DEFAULT, Typeface.BOLD_ITALIC));
        myPaint.setTextSize(40);
        canvas.drawText(String.valueOf(usia), pageWidth/2, 950, myPaint);

        titlePaint.setTextAlign(Paint.Align.CENTER);
        titlePaint.setTextSize(40);
        canvas.drawText("Kategori Kucing Anda", pageWidth/2, 1050, titlePaint);

        myPaint.setTextAlign(Paint.Align.CENTER);
        myPaint.setTypeface(Typeface.create(Typeface.DEFAULT, Typeface.BOLD_ITALIC));
        myPaint.setTextSize(40);
        canvas.drawText(String.valueOf(kategori), pageWidth/2, 1100, myPaint);

//        myPaint.setTextAlign(Paint.Align.CENTER);
//        titlePaint.setTextSize(40);
//        canvas.drawText(String.valueOf(penjelasan), pageWidth/2, 1000, titlePaint);

        myPdfDocument.finishPage(myPage1);

        // below line is used to set the name of
        // our PDF file and its path.
        String filename = "HasilDiagnosa_";
        dateFormat = new SimpleDateFormat("ddMMyyyy-HH:mm:ss");
        String final_filename = filename + dateFormat.format(dateObj);
        File file = new File(getExternalFilesDir(null), final_filename+".pdf");

        try {
            // after creating a file name we will
            // write our PDF file to that location.
            myPdfDocument.writeTo(new FileOutputStream(file));

            // below line is to print toast message
            // on completion of PDF generation.
            Toast.makeText(hasilDiagnosa.this, "PDF file generated successfully.", Toast.LENGTH_SHORT).show();
            Toast.makeText(hasilDiagnosa.this, "Location: /storage/emulated/0/Android/data/hu.abisoft.lm/files/com.example.sistempakar_umurkucing", Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            // below line is used
            // to handle error
            e.printStackTrace();
        }
        // after storing our pdf to that
        // location we are closing our PDF file.
        myPdfDocument.close();

    }

    private boolean checkPermission() {
        // checking of permissions.
        int permission1 = ContextCompat.checkSelfPermission(getApplicationContext(), WRITE_EXTERNAL_STORAGE);
        int permission2 = ContextCompat.checkSelfPermission(getApplicationContext(), READ_EXTERNAL_STORAGE);
        return permission1 == PackageManager.PERMISSION_GRANTED && permission2 == PackageManager.PERMISSION_GRANTED;
    }
    private void requestPermission() {
        // requesting permissions if not provided.
        ActivityCompat.requestPermissions(this, new String[]{WRITE_EXTERNAL_STORAGE, READ_EXTERNAL_STORAGE}, PERMISSION_REQUEST_CODE);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == PERMISSION_REQUEST_CODE) {
            if (grantResults.length > 0) {

                // after requesting permissions we are showing
                // users a toast message of permission granted.
                boolean writeStorage = grantResults[0] == PackageManager.PERMISSION_GRANTED;
                boolean readStorage = grantResults[1] == PackageManager.PERMISSION_GRANTED;

                if (writeStorage && readStorage) {
                    Toast.makeText(this, "Permission Granted..", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this, "Permission Denined.", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }
}