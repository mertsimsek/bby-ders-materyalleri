package com.bby.bbyders;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private EditText edtNot;
    private Button btnEkle;
    private TextView txtNotlar;

    private Button btnCikis;
    private Button btnSil;

    private String eklenecekNot;
    private String varolanNot;

    private SharedPreferences sp;
    private SharedPreferences.Editor editor;


    private SharedPreferences spNotlar;
    private SharedPreferences.Editor editorNotlar;


    // mert@4pps.co

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtNot = (EditText) findViewById(R.id.editTextNot);
        btnEkle = (Button) findViewById(R.id.butonEkle);
        txtNotlar = (TextView) findViewById(R.id.textViewNotlar);
        btnCikis = (Button) findViewById(R.id.cikisYap);
        btnSil = (Button) findViewById(R.id.sil);

        sp = getSharedPreferences("bbyLogin", MODE_PRIVATE);

        // Buradaki bbyNotlar anahtarını, kullanıcı adı ile değiştirirseniz,
        // her kullanıcı kendine özel notları görmüş olur.
        spNotlar = getSharedPreferences("bbyNotlar", MODE_PRIVATE);

        txtNotlar.setText(spNotlar.getString("notlar", ""));


        btnEkle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                varolanNot = txtNotlar.getText().toString();
                eklenecekNot = edtNot.getText().toString();
                txtNotlar.setText("*\t" + eklenecekNot + "\n" + varolanNot);
                editorNotlar = spNotlar.edit();
                editorNotlar.putString("notlar", txtNotlar.getText().toString());
                editorNotlar.commit();
            }
        });


        btnCikis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                editor = sp.edit();
                editor.putBoolean("loginOlduMu", false);
                editor.commit();

                Intent i = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(i);
                finish();

            }
        });

        btnSil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                varolanNot = txtNotlar.getText().toString();
                String satirlar[] = varolanNot.split("\n");
                String silindiktenSonrakiNot = "";
                for (int i = 1; i < satirlar.length; i++) {
                    silindiktenSonrakiNot = silindiktenSonrakiNot + satirlar[i] + "\n";

                }

                txtNotlar.setText(silindiktenSonrakiNot);
                editorNotlar = spNotlar.edit();
                editorNotlar.putString("notlar", silindiktenSonrakiNot);
                editorNotlar.commit();
                /*else if (satirlar.length == 1) {
                    silindiktenSonrakiNot = "";
                    txtNotlar.setText(silindiktenSonrakiNot);
                    editorNotlar = spNotlar.edit();
                    editorNotlar.putString("notlar", silindiktenSonrakiNot);
                }*/
            }
        });


    }
}
