package com.example.uygulama2;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.view.accessibility.AccessibilityManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    TextView txt_mail,txt_sifre;
    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        txt_mail=findViewById(R.id.txt_mail);
        txt_sifre=findViewById(R.id.txt_sifre);
        btn=findViewById(R.id.btn_giris_yap);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        btn.setOnClickListener(view -> girisYap());
    }

    public void girisYap() {

        if (TextUtils.isEmpty(txt_mail.getText())) {
            Toast.makeText(this, "Mail Alanı Boş Bırakılamaz....", Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(txt_sifre.getText())) {
            Toast.makeText(this, "Şifre Alanı Boş Bırakılamaz...", Toast.LENGTH_SHORT).show();
        } else if (!Patterns.EMAIL_ADDRESS.matcher(txt_mail.getText()).matches()) {
            Toast.makeText(this, "Email uygun girilmedi...", Toast.LENGTH_SHORT).show();
        }else{
            Intent hosgeldinintent=new Intent(MainActivity.this, HosGeldin.class);
            hosgeldinintent.putExtra("mail",txt_mail.getText().toString());
            startActivity(hosgeldinintent);
        }
    }
}