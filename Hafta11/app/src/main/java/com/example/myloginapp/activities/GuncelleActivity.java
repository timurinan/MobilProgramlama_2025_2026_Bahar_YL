package com.example.myloginapp.activities;

import android.content.Intent;
import android.os.Build;
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

public class GuncelleActivity extends AppCompatActivity {

    TextView txt_guncelle_adsoyad, txt_guncelle_mail,txt_guncelle_telefon;
    Button btn_guncelle;

    Müşteri güncellenekmüşteri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_guncelle);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        txt_guncelle_adsoyad=findViewById(R.id.txt_guncelle_adsoyad);
        txt_guncelle_mail=findViewById(R.id.txt_guncelle_mail);
        txt_guncelle_telefon=findViewById(R.id.txt_guncelle_telefon);
        btn_guncelle=findViewById(R.id.btn_guncelle);



        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            güncellenekmüşteri=getIntent().getSerializableExtra("müşteri", Müşteri.class);
            txt_guncelle_telefon.setText(güncellenekmüşteri.getTelefon());
            txt_guncelle_mail.setText(güncellenekmüşteri.getMail());
            txt_guncelle_adsoyad.setText(güncellenekmüşteri.getAdsoyad());
        }
        btn_guncelle.setOnClickListener(view -> guncelle());
    }

    private void guncelle() {

        if(TextUtils.isEmpty(txt_guncelle_adsoyad.getText().toString())){
            Toast.makeText(this, "Ad soyad alanı boş bırakılamaz.", Toast.LENGTH_SHORT).show();
        }else{
            if(TextUtils.isEmpty(txt_guncelle_mail.getText().toString())){
                Toast.makeText(this, "Mail alanı boş bırakılamaz.", Toast.LENGTH_SHORT).show();
            }else{
                if(TextUtils.isEmpty(txt_guncelle_telefon.getText().toString())){
                    Toast.makeText(this, "Telefon alanı boş bırakılamaz.", Toast.LENGTH_SHORT).show();
                }else{
                    if(!Patterns.EMAIL_ADDRESS.matcher(txt_guncelle_mail.getText().toString()).matches()){
                        Toast.makeText(this, "Mail formatı yanlış.", Toast.LENGTH_SHORT).show();
                    }else{
                        if(!Patterns.PHONE.matcher(txt_guncelle_telefon.getText().toString()).matches()){
                            Toast.makeText(this, "Telefon formatı yanlış.", Toast.LENGTH_SHORT).show();
                        }else{
                            String adsoyad=txt_guncelle_adsoyad.getText().toString();
                            String mail=txt_guncelle_mail.getText().toString();
                            String telefon=txt_guncelle_telefon.getText().toString();
                            Müşteri güncelmüşteri=new Müşteri(adsoyad,mail,telefon);
                            FirebaseDatabase veritabanı=FirebaseDatabase.getInstance();
                            DatabaseReference dr=veritabanı.getReference("müşteriler");
                            güncelmüşteri.setAnahtar(güncellenekmüşteri.getAnahtar());
                            dr.child(güncelmüşteri.getAnahtar()).setValue(güncelmüşteri);
                            Toast.makeText(this, "Güncelleme Başarılı", Toast.LENGTH_SHORT).show();
                            Intent intent_signup=new Intent(GuncelleActivity.this, ListelemeActivity.class);
                            startActivity(intent_signup);
                        }
                    }
                }
            }
        }




    }
}