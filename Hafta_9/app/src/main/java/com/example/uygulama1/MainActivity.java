package com.example.uygulama1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    FloatingActionButton fab;

    ArrayList<Müşteri> müşteriler=new ArrayList<Müşteri>();
    Veritabani_Islemleri vi=new Veritabani_Islemleri(MainActivity.this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView=findViewById(R.id.recyclerView);
        fab=findViewById(R.id.floatingActionButton);

        müşteriler= (ArrayList<Müşteri>) vi.listele();

        BenimAdapter benimAdapter=new BenimAdapter(müşteriler,MainActivity.this);

        recyclerView.setLongClickable(true);

        recyclerView.setAdapter(benimAdapter);

        LinearLayoutManager manager=new LinearLayoutManager(getApplicationContext());
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(manager);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ekle_intent=new Intent(MainActivity.this, EkleActivity.class);
                startActivity(ekle_intent);
            }
        });



    }
}