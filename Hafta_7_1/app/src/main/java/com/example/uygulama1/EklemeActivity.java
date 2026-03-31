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

public class EklemeActivity extends AppCompatActivity {

    TextView txt_adsoyad_ekle, txt_mail_ekle,txt_telefon_ekle;

    ArrayList<Müşteri> müşteriler;

    Button btn_kaydet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_ekleme);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

         müşteriler= (ArrayList<Müşteri>) getIntent().getSerializableExtra("müşteriler");

        txt_adsoyad_ekle=findViewById(R.id.txt_adsoyad_ekle);
        txt_mail_ekle=findViewById(R.id.txt_mail_ekle);
        txt_telefon_ekle=findViewById(R.id.txt_telefon_ekle);
        btn_kaydet=findViewById(R.id.btn_kaydet);

        btn_kaydet.setOnClickListener(view -> müşteriKaydet());
    }

    private void müşteriKaydet() {

        if(TextUtils.isEmpty(txt_adsoyad_ekle.getText())||
                TextUtils.isEmpty(txt_mail_ekle.getText())||
                TextUtils.isEmpty(txt_telefon_ekle.getText())){
            Toast.makeText(this, "Alanlar Boş Bırakılamaz", Toast.LENGTH_SHORT).show();
        }else{
            if(!Patterns.EMAIL_ADDRESS.matcher(txt_mail_ekle.getText()).matches()){
                Toast.makeText(this, "E-mail formatı yanlış", Toast.LENGTH_SHORT).show();
            }else{
                if(!Patterns.PHONE.matcher(txt_telefon_ekle.getText()).matches()){
                    Toast.makeText(this, "Telefon Numarası Formatı Yanlış...", Toast.LENGTH_SHORT).show();
                }else{
                    Müşteri müşteri=new Müşteri(txt_adsoyad_ekle.getText().toString()
                            ,txt_mail_ekle.getText().toString()
                            , txt_telefon_ekle.getText().toString());
                    Intent listele_intent=new Intent(EklemeActivity.this, ListeleActivity.class);
                    müşteriler.add(müşteri);
                    listele_intent.putExtra("müşteriler",müşteriler);
                    listele_intent.putExtra("id",1);
                    startActivity(listele_intent);
                }
            }
        }
    }
}