package com.example.uygulama1;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class EkleActivity extends AppCompatActivity {

    TextView txt_adsoyad,txt_mail,txt_telefon;
    Button btn_ekle;

    Veritabani_Islemleri vi=new Veritabani_Islemleri(EkleActivity.this);

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ekle);

        txt_adsoyad=findViewById(R.id.txt_adsoyad_ekle);
        txt_mail=findViewById(R.id.txt_mail_ekle);
        txt_telefon=findViewById(R.id.txt_telefon_ekle);
        btn_ekle=findViewById(R.id.btn_ekle);

        btn_ekle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(TextUtils.isEmpty(txt_adsoyad.getText())||TextUtils.isEmpty(txt_mail.getText())||TextUtils.isEmpty(txt_telefon.getText())){
                    Toast.makeText(EkleActivity.this, "Alanlar boş bırakılamaz", Toast.LENGTH_SHORT).show();
                }else{
                    if(Patterns.EMAIL_ADDRESS.matcher(txt_mail.getText()).matches()){
                        String adsoyad=txt_adsoyad.getText().toString();
                        String mail=txt_mail.getText().toString();
                        String telefon=txt_telefon.getText().toString();

                        Müşteri eklenecek_müşteri=new Müşteri(adsoyad,telefon,mail);
                        if(vi.ekle(eklenecek_müşteri)){
                            Intent main_intent=new Intent(EkleActivity.this,MainActivity.class);
                            startActivity(main_intent);
                        }else{
                            Toast.makeText(EkleActivity.this, "Girilen mail zaten kayıtlı", Toast.LENGTH_SHORT).show();
                        }
                    }else{
                        Toast.makeText(EkleActivity.this, "Mail adresi formatı yanlış...", Toast.LENGTH_SHORT).show();
                    }

                }

            }
        });

    }
}
