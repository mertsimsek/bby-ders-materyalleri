package com.bby.bbyders;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class KaydolActivity extends AppCompatActivity {

    private EditText kullaniciAdiEditText;
    private EditText sifreEditText;
    private EditText sifreTekrarEditText;
    private Button kaydolButton;


    private String kullaniciAdiString;
    private String sifreString;
    private String sifreTekrarString;

    private SharedPreferences sp;
    private SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kaydol);

        kullaniciAdiEditText = (EditText) findViewById(R.id.kullaniciAdiKayit);
        sifreEditText = (EditText) findViewById(R.id.sifreKayit);
        sifreTekrarEditText = (EditText) findViewById(R.id.sifreTekrarKayit);
        kaydolButton = (Button) findViewById(R.id.kaydolKayit);

        sp = getSharedPreferences("bbyLogin", MODE_PRIVATE);

        kaydolButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                kullaniciAdiString = kullaniciAdiEditText.getText().toString();
                sifreString = sifreEditText.getText().toString();
                sifreTekrarString = sifreTekrarEditText.getText().toString();

                if (kullaniciAdiString.length() >= 5) {

                    if (sifreString.length() >= 5) {

                        if (sifreString.equals(sifreTekrarString)) {

                            if (sp.getString(kullaniciAdiString, "").equals("")) {
                                editor = sp.edit();
                                editor.putBoolean("loginOlduMu", true);

                                editor.putString(kullaniciAdiString,sifreString);

                                editor.commit();

                                Toast.makeText(KaydolActivity.this, "Başarıyla kayıt oldunuz.", Toast.LENGTH_SHORT).show();

                                Intent i = new Intent(KaydolActivity.this, MainActivity.class);
                                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                startActivity(i);
                                finish();
                            } else {
                                Toast.makeText(KaydolActivity.this, "Bu kullanıcı adı ile daha önce kayıt olunmuş.", Toast.LENGTH_SHORT).show();
                            }

                        } else {
                            Toast.makeText(KaydolActivity.this, "Şifre ve şifre tekrar birbirinden farklı.", Toast.LENGTH_SHORT).show();
                        }

                    } else {
                        Toast.makeText(KaydolActivity.this, "Şifre 5 karakterden az olamaz.", Toast.LENGTH_SHORT).show();
                    }

                } else {
                    Toast.makeText(KaydolActivity.this, "Kullanıcı adı 5 karakterden az olamaz.", Toast.LENGTH_SHORT).show();
                }


            }
        });

    }
}
