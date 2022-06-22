package com.example.sistempakar_umurkucing;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.AppCompatRadioButton;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Register extends AppCompatActivity {

    //Variable
    AppCompatEditText inputName, inputPhone, inputAlamat, inputUsername, inputPassword;
    AppCompatButton bDaftar, bLogin;
    RadioGroup radioGroup;
    AppCompatRadioButton radioButton;

    FirebaseDatabase firebaseDatabase;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        //Mengarahkan Fungsi sesuai XML
        inputName = findViewById(R.id.name);
        inputPhone = findViewById(R.id.phone);
        inputAlamat = findViewById(R.id.alamat);
        inputUsername = findViewById(R.id.username);
        inputPassword = findViewById(R.id.password);

        bDaftar = findViewById(R.id.sign_up_button);
        bLogin = findViewById(R.id.sign_in_button);

        radioGroup = findViewById(R.id.Rgrup);

        final LoadingDialog loadingDialog = new LoadingDialog(Register.this);

        bLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadingDialog.startLoadingDialog();
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        loadingDialog.dismissDialog();
                        startActivity(new Intent(Register.this, Login.class));
                    }
                }, 3000);
            }
        });

        //Menyimpan data ke firebase setelah click
        bDaftar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                firebaseDatabase = FirebaseDatabase.getInstance();
                reference = firebaseDatabase.getReference("Users");

                int radioId = radioGroup.getCheckedRadioButtonId();

                radioButton = findViewById(radioId);

                //Mendapatkan semua Nilai
                String name = inputName.getText().toString();
                String phone = inputPhone.getText().toString();
                String alamat = inputAlamat.getText().toString();
                String status = radioButton.getText().toString();
                String username = inputUsername.getText().toString();
                String password = inputPassword.getText().toString();

                if (name.isEmpty()) {
                    inputName.setError(getString(R.string.eror_name));
                    inputName.requestFocus();
                    return;
                }
                if (phone.isEmpty()) {
                    inputPhone.setError(getString(R.string.eror_phone));
                    inputPhone.requestFocus();
                    return;
                }
                if (phone.length() < 10) {
                    inputPhone.setError(getString(R.string.eror_phone2));
                    inputPhone.requestFocus();
                    return;
                }
                if (alamat.isEmpty()) {
                    inputAlamat.setError(getString(R.string.alamat_eror));
                    inputAlamat.requestFocus();
                    return;
                }
                if (username.isEmpty()) {
                    inputUsername.setError(getString(R.string.eror_username));
                    inputUsername.requestFocus();
                    return;
                }
                if (password.isEmpty()) {
                    inputPassword.setError(getString(R.string.hint_password));
                    inputPassword.requestFocus();
                    return;
                }
                if (password.length() < 6) {
                    inputPassword.setError(getString(R.string.minimum_password));
                    inputPassword.requestFocus();
                    return;
                }

                UserHelperClass helperClass = new UserHelperClass(name, phone, alamat, status, username, password);
                reference.child(username).setValue(helperClass);

                loadingDialog.startLoadingDialog();
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        loadingDialog.dismissDialog();
                        startActivity(new Intent(Register.this, Login.class));
                    }
                }, 3000);
            }

        });
    }

    public void checkButton(View view) {
        int radioId = radioGroup.getCheckedRadioButtonId();

        radioButton = findViewById(radioId);

        Toast.makeText(this, "Status Kunjungan Anda: " + radioButton.getText(), Toast.LENGTH_SHORT).show();
    }
}