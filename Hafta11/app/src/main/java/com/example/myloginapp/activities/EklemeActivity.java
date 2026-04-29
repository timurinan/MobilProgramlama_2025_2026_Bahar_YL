package com.example.myloginapp.activities;

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

import com.example.myloginapp.R;
import com.example.myloginapp.model.Müşteri;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class EklemeActivity extends AppCompatActivity {

    TextView txt_ekle_adsoyad, txt_ekle_mail, txt_ekle_telefon;
    Button btn_ekle;

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

        txt_ekle_adsoyad=findViewById(R.id.txt_ekle_adsoyad);
        txt_ekle_mail=findViewById(R.id.txt_ekle_mail);
        txt_ekle_telefon=findViewById(R.id.txt_ekle_telefon);
        btn_ekle=findViewById(R.id.btn_ekle);

        btn_ekle.setOnClickListener(view -> müşteriEkle());
    }

    private void müşteriEkle() {

        if(TextUtils.isEmpty(txt_ekle_adsoyad.getText().toString())){
            Toast.makeText(this, "Ad soyad alanı boş bırakılamaz.", Toast.LENGTH_SHORT).show();
        }else{
            if(TextUtils.isEmpty(txt_ekle_mail.getText().toString())){
                Toast.makeText(this, "Mail alanı boş bırakılamaz.", Toast.LENGTH_SHORT).show();
            }else{
                if(TextUtils.isEmpty(txt_ekle_telefon.getText().toString())){
                    Toast.makeText(this, "Telefon alanı boş bırakılamaz.", Toast.LENGTH_SHORT).show();
                }else{
                    if(!Patterns.EMAIL_ADDRESS.matcher(txt_ekle_mail.getText().toString()).matches()){
                        Toast.makeText(this, "Mail formatı yanlış.", Toast.LENGTH_SHORT).show();
                    }else{
                        if(!Patterns.PHONE.matcher(txt_ekle_telefon.getText().toString()).matches()){
                            Toast.makeText(this, "Telefon formatı yanlış.", Toast.LENGTH_SHORT).show();
                        }else{
                            FirebaseDatabase veritabanı=FirebaseDatabase.getInstance();
                            DatabaseReference referans=veritabanı.getReference("müşteriler");
                            String anahtar=referans.push().getKey();
                            DatabaseReference nesnereferansı=veritabanı.getReference("müşteriler/"+anahtar);
                            Müşteri müşteri=new Müşteri(txt_ekle_adsoyad.getText().toString(), txt_ekle_mail.getText().toString(),txt_ekle_telefon.getText().toString());
                            müşteri.setAnahtar(anahtar);
                            nesnereferansı.setValue(müşteri);
                            Toast.makeText(this, "Müşteri Başarıyla Eklendi", Toast.LENGTH_SHORT).show();
                            Intent listele_intent=new Intent(EklemeActivity.this, ListelemeActivity.class);
                            startActivity(listele_intent);
                        }
                    }
                }
            }
        }


    }
}