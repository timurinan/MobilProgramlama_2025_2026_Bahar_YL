package com.example.uygulama1;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class GüncellemeActivity extends AppCompatActivity {

    TextView txt_adsoyad_güncelle, txt_mail_güncelle,txt_telefon_güncelle;

    ArrayList<Müşteri> müşteriler;

    Button btn_güncelle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_guncelleme);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        txt_adsoyad_güncelle=findViewById(R.id.txt_adsoyad_güncelle);
        txt_mail_güncelle=findViewById(R.id.txt_mail_güncelle);
        txt_telefon_güncelle=findViewById(R.id.txt_telefon_güncelle);

        btn_güncelle=findViewById(R.id.btn_güncelle);

        müşteriler= (ArrayList<Müşteri>) getIntent().getSerializableExtra("müşteriler");
        int pozisyon=getIntent().getIntExtra("pozisyon",0);

        txt_adsoyad_güncelle.setText(müşteriler.get(pozisyon).getAdsoyad());
        txt_mail_güncelle.setText(müşteriler.get(pozisyon).getMail());
        txt_telefon_güncelle.setText(müşteriler.get(pozisyon).getTelefon());

        btn_güncelle.setOnClickListener(view -> güncelle(pozisyon));
    }

    private void güncelle(int pozisyon) {

        if(TextUtils.isEmpty(txt_adsoyad_güncelle.getText())||
                TextUtils.isEmpty(txt_mail_güncelle.getText())||
                TextUtils.isEmpty(txt_telefon_güncelle.getText())){
            Toast.makeText(this, "Alanlar Boş Bırakılamaz", Toast.LENGTH_SHORT).show();
        }else{
            if(!Patterns.EMAIL_ADDRESS.matcher(txt_mail_güncelle.getText()).matches()){
                Toast.makeText(this, "E-mail formatı yanlış", Toast.LENGTH_SHORT).show();
            }else{
                if(!Patterns.PHONE.matcher(txt_telefon_güncelle.getText()).matches()){
                    Toast.makeText(this, "Telefon Numarası Formatı Yanlış...", Toast.LENGTH_SHORT).show();
                }else{
                    Müşteri müşteri=new Müşteri(txt_adsoyad_güncelle.getText().toString()
                            ,txt_mail_güncelle.getText().toString()
                            , txt_telefon_güncelle.getText().toString());
                    Intent listele_intent=new Intent(GüncellemeActivity.this, ListeleActivity.class);
                    müşteriler.set(pozisyon,müşteri);
                    listele_intent.putExtra("müşteriler",müşteriler);
                    listele_intent.putExtra("id",1);
                    startActivity(listele_intent);
                }
            }
        }
    }

}