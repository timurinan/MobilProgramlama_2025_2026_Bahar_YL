package com.example.uygulama1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class GuncelleActivity extends AppCompatActivity {

    TextView txt_adsoyad,txt_mail,txt_telefon;
    Button btn_guncelle;

    Veritabani_Islemleri vi=new Veritabani_Islemleri(GuncelleActivity.this);

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guncelle);

        int id=getIntent().getIntExtra("musteri_id",0);

        txt_adsoyad=findViewById(R.id.txt_adsoyad_guncelle);
        txt_mail=findViewById(R.id.txt_mail_guncelle);
        txt_telefon=findViewById(R.id.txt_telefon_guncelle);
        btn_guncelle=findViewById(R.id.btn_guncelle);

        Müşteri müşteri=vi.getMüşteri(id);

        txt_adsoyad.setText(müşteri.getAdsoyad());
        txt_mail.setText(müşteri.getMail());
        txt_telefon.setText(müşteri.getTelefon());

        btn_guncelle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String adsoyad=txt_adsoyad.getText().toString();
                String mail=txt_mail.getText().toString();
                String telefon=txt_telefon.getText().toString();
                Müşteri güncellenecek_müşteri=new Müşteri(adsoyad,telefon,mail);
                güncellenecek_müşteri.setId(id);
                vi.guncelle(güncellenecek_müşteri);

                Intent main_intent=new Intent(GuncelleActivity.this,MainActivity.class);
                startActivity(main_intent);
            }
        });


    }
}
