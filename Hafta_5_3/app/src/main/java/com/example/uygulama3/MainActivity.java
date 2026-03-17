package com.example.uygulama3;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.viewpager2.widget.ViewPager2;

public class MainActivity extends AppCompatActivity {

    ViewPager2 viewPager2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        viewPager2=findViewById(R.id.viewpager);

        Hesap hesap1=new Hesap("Timur İnan","Euro",1000);
        Hesap hesap2=new Hesap("Ahmet Kuzu","Dolar",500);
        Hesap hesap3=new Hesap("Ayşe Sertel","Türk Lirası",100000);

        Fragment_Hesap fh1=new Fragment_Hesap(hesap1);
        Fragment_Hesap fh2=new Fragment_Hesap(hesap2);
        Fragment_Hesap fh3=new Fragment_Hesap(hesap3);

        Fragment_Adapter fa=new Fragment_Adapter(getSupportFragmentManager(),getLifecycle());

        fa.addHesap(fh1);
        fa.addHesap(fh2);
        fa.addHesap(fh3);

        viewPager2.setAdapter(fa);

    }
}