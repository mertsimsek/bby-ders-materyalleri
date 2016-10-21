package com.bby.bbyders;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class LoginActivity extends AppCompatActivity {


    private EditText kullaniciAdiEditText;
    private EditText sifreEditText;

    private Button girisYapButton;
    private Button kaydolButton;

    private String kullaniciAdiString;
    private String sifreString;

    private SharedPreferences sp;
    private SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        sp = getSharedPreferences("bbyLogin", MODE_PRIVATE);

        if (sp.getBoolean("loginOlduMu",false) == true) {

            Intent i = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(i);
            finish();

        } else {
            kullaniciAdiEditText = (EditText) findViewById(R.id.kullaniciAdi);
            sifreEditText = (EditText) findViewById(R.id.sifre);

            girisYapButton = (Button) findViewById(R.id.giris);
            kaydolButton = (Button) findViewById(R.id.kaydol);

            girisYapButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    kullaniciAdiString = kullaniciAdiEditText.getText().toString();
                    sifreString = sifreEditText.getText().toString();

                    if (sp.getString(kullaniciAdiString, "").equals(sifreString)) {

                        editor = sp.edit();
                        editor.putBoolean("loginOlduMu", true);
                        editor.commit();

                        Intent i = new Intent(LoginActivity.this, MainActivity.class);
                        startActivity(i);
                        finish();

                    } else {
                        Toast.makeText(LoginActivity.this,"Kullanıcı adı ya da şifreyi hatalı girdiniz.",Toast.LENGTH_SHORT).show();
                    }


                }
            });

            kaydolButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(LoginActivity.this, KaydolActivity.class);
                    startActivity(i);
                }
            });
        }

    }
}
