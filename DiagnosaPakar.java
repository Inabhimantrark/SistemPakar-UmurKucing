package com.example.sistempakar_umurkucing;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.Toolbar;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.CheckBox;
import android.widget.Switch;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.gson.Gson;

public class DiagnosaPakar extends AppCompatActivity {

    final LoadingDialog loadingDialog = new LoadingDialog(DiagnosaPakar.this);

    CheckBox checkGigiSusuDepan, checkGigiTaring, checkGigiSusuLengkap, checkGigiPermanen, checkSeluruhGigi, checkKarangGigi, checkMulai;

    AppCompatButton startDiagnosa;

    FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    DatabaseReference reference = firebaseDatabase.getReference();

    Integer angka;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diagnosa_pakar);

        checkMulai = findViewById(R.id.cb_mulai);
        checkGigiSusuDepan = findViewById(R.id.cb_gigi_susu_depan);
        checkGigiTaring = findViewById(R.id.cb_gigi_taring);
        checkGigiSusuLengkap = findViewById(R.id.cb_gigi_susu_lengkap);
        checkGigiPermanen = findViewById(R.id.cb_gigi_permanen);
        checkSeluruhGigi = findViewById(R.id.cb_seluruh_gigi_permanen);
        checkKarangGigi = findViewById(R.id.cb_karang_gigi);

        startDiagnosa = findViewById(R.id.mulaiDiagnosa);

        Toolbar mMyToolbar = (Toolbar) findViewById(R.id.toolbar);

        mMyToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (getFragmentManager().getBackStackEntryCount() > 0) {
                    getFragmentManager().popBackStack();
                    return;
                }
                DiagnosaPakar.super.onBackPressed();
            }
        });


        startDiagnosa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if (checkMulai.isChecked() && checkGigiSusuDepan.isChecked()) {

                    String usiaKucing, kategoriUsia, penjelasan;

                    usiaKucing = "2 Sampai 4 Minggu";
                    kategoriUsia = "Kitten";
                    penjelasan = "Anak kucing akan melakukan upaya pertama untuk berdiri pada tahapan ini,  Selain itu 90% dari waktu yang mereka miliki akan dihabiskan untuk tidur yang penting untuk mendukung pertumbuhannya.";

                    Intent intent = new Intent(DiagnosaPakar.this, hasilDiagnosa.class);
                    intent.putExtra("usiaKucing", String.valueOf(usiaKucing));
                    intent.putExtra("kategoriUsia", String.valueOf(kategoriUsia));
                    intent.putExtra("penjelasan",String.valueOf(penjelasan));

                    loadingDialog.startLoadingDialog();
                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            startActivity(intent);
                        }
                    },3000);

                }

                if (checkGigiSusuDepan.isChecked() && checkGigiTaring.isChecked()) {

                    String usiaKucing, kategoriUsia, penjelasan;

                    usiaKucing = "3 sampai 4 Minggu";
                    kategoriUsia = "Kitten";
                    penjelasan = "Periode ketika kucing muda tumbuh dengan cepat dan biasanya belum cukup dewasa secara seksual.";


                    Intent intent = new Intent(DiagnosaPakar.this, hasilDiagnosa.class);
                    intent.putExtra("usiaKucing", String.valueOf(usiaKucing));
                    intent.putExtra("kategoriUsia", String.valueOf(kategoriUsia));
                    intent.putExtra("penjelasan",String.valueOf(penjelasan));

                    loadingDialog.startLoadingDialog();
                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            startActivity(intent);
                        }
                    },3000);
                }

                if (checkGigiSusuDepan.isChecked() && checkGigiTaring.isChecked() && checkGigiSusuLengkap.isChecked()) {

                    String usiaKucing, kategoriUsia, penjelasan;

                    usiaKucing = "8 Minggu";
                    kategoriUsia = "Kitten";
                    penjelasan = "Pada fase ini, kucing mencapai ukuran penuh dan belajar tentang kehidupan dan cara bertahan hidup.";


                    Intent intent = new Intent(DiagnosaPakar.this, hasilDiagnosa.class);
                    intent.putExtra("usiaKucing", String.valueOf(usiaKucing));
                    intent.putExtra("kategoriUsia", String.valueOf(kategoriUsia));
                    intent.putExtra("penjelasan",String.valueOf(penjelasan));

                    loadingDialog.startLoadingDialog();
                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            startActivity(intent);
                        }
                    },3000);

                }

                if (checkGigiSusuDepan.isChecked() && checkGigiTaring.isChecked() && checkGigiSusuLengkap.isChecked()
                        && checkGigiPermanen.isChecked()) {

                    String usiaKucing, kategoriUsia, penjelasan;

                    usiaKucing = "12 sampai 14 Minggu";
                    kategoriUsia = "Kitten";
                    penjelasan = "Kucing dewasa secara fisik dan perilaku, dan biasanya masih sehat dan aktif, terlihat ramping dan berkilau serta membuat kehidupan yang terbaik.";


                    Intent intent = new Intent(DiagnosaPakar.this, hasilDiagnosa.class);
                    intent.putExtra("usiaKucing", String.valueOf(usiaKucing));
                    intent.putExtra("kategoriUsia", String.valueOf(kategoriUsia));
                    intent.putExtra("penjelasan",String.valueOf(penjelasan));

                    loadingDialog.startLoadingDialog();
                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            startActivity(intent);
                        }
                    },3000);

                }

                if (checkGigiSusuDepan.isChecked() && checkGigiTaring.isChecked() && checkGigiSusuLengkap.isChecked()
                        && checkGigiPermanen.isChecked() && checkSeluruhGigi.isChecked()) {

                    String usiaKucing, kategoriUsia, penjelasan;

                    usiaKucing = "20 Sampai 28 Minggu";
                    kategoriUsia = "Kitten";
                    penjelasan = "kehidupan ini seiring dengan kematangan seksual. Pastikan untuk melakukan interaksi yang sesuai dengan kucing Anda.";


                    Intent intent = new Intent(DiagnosaPakar.this, hasilDiagnosa.class);
                    intent.putExtra("usiaKucing", String.valueOf(usiaKucing));
                    intent.putExtra("kategoriUsia", String.valueOf(kategoriUsia));
                    intent.putExtra("penjelasan",String.valueOf(penjelasan));

                    loadingDialog.startLoadingDialog();
                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            startActivity(intent);
                        }
                    },3000);

                }

                if (checkGigiSusuDepan.isChecked() && checkGigiTaring.isChecked() && checkGigiSusuLengkap.isChecked()
                        && checkGigiPermanen.isChecked() && checkSeluruhGigi.isChecked() && checkKarangGigi.isChecked()) {

                    String usiaKucing, kategoriUsia, penjelasan;

                    usiaKucing = "Lebih Dari 52 Minggu";
                    kategoriUsia = "Kucing Junior";
                    penjelasan = "Aktivitas bermain mulai berkurang pada usia ini, dan kucing akan cenderung bertambah gemuk.";


                    Intent intent = new Intent(DiagnosaPakar.this, hasilDiagnosa.class);
                    intent.putExtra("usiaKucing", String.valueOf(usiaKucing));
                    intent.putExtra("kategoriUsia", String.valueOf(kategoriUsia));
                    intent.putExtra("penjelasan", String.valueOf(penjelasan));

                    loadingDialog.startLoadingDialog();
                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            startActivity(intent);
                        }
                    }, 3000);

                }

                //else {

                //startActivity(new Intent(DiagnosaPakar.this, TidakDiketahui.class));

                //}




            }
        });

    }
    public void onClickChecked(View view) {
        boolean checked = ((CheckBox) view).isChecked();
        String str = "";

        switch (view.getId()) {
            case R.id.cb_mulai:
                str = checked ? "Rule 0 Terpilih " :
                        "Rule 0 Dibatalkan ";
                break;

            case R.id.cb_gigi_susu_depan:
                str = checked ? "Rule 1 Terpilih " :
                        "Rule 1 Dibatalkan ";
                break;

            case R.id.cb_gigi_taring:
                str = checked ? "Rule 2 Terpilih " :
                        "Rule 2 Dibatalkan ";
                break;

            case R.id.cb_gigi_susu_lengkap:
                str = checked ? "Rule 3 Terpilih " :
                        "Rule 3 Dibatalkan ";
                break;

            case R.id.cb_gigi_permanen:
                str = checked ? "Rule 4 Terpilih " :
                        "Rule 4 Dibatalkan ";
                break;

            case R.id.cb_seluruh_gigi_permanen:
                str = checked ? "Rule 4 Terpilih " :
                        "Rule 4 Dibatalkan ";
                break;

            case R.id.cb_karang_gigi:
                str = checked ? "Rule 5 Terpilih " :
                        "Rule 5 Dibatalkan ";
                break;

        }
        Toast.makeText(getApplicationContext(), str, Toast.LENGTH_SHORT).show();
    }
}