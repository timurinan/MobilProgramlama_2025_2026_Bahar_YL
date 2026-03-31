package com.example.uygulama1;

import android.content.Intent;
import android.os.Bundle;
import android.widget.SearchView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class ListeleActivity extends AppCompatActivity {

    SearchView sv;

    ArrayList<Müşteri> müşteriler;
    RecyclerView rv;
    FloatingActionButton fab;

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

        sv=findViewById(R.id.searchview);
        rv=findViewById(R.id.recyclerview);
        fab=findViewById(R.id.floatingActionButton);

        fab.setOnClickListener(view -> eklemeEkranınıAç());

        müşteriler=new ArrayList<Müşteri>();

        if(getIntent().getIntExtra("id",0)==1){
            müşteriler= (ArrayList<Müşteri>) getIntent().getSerializableExtra("müşteriler");
        }

        BenimAdapter ba=new BenimAdapter(getLayoutInflater(),müşteriler);

        rv.setAdapter(ba);

        LinearLayoutManager llm=new LinearLayoutManager(getApplicationContext());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        rv.setLayoutManager(llm);

        sv.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextChange(String s) {

                ArrayList<Müşteri> filtrelenenmüşteriler=new ArrayList<Müşteri>();
                for (Müşteri müşteri:müşteriler ) {
                    if(müşteri.getAdsoyad().toLowerCase().contains(s.toLowerCase())){
                        filtrelenenmüşteriler.add(müşteri);
                    }
                }
                if(filtrelenenmüşteriler.size()==0){
                    ba.setFiltrelenenMüşteriler(müşteriler);
                }else {
                    ba.setFiltrelenenMüşteriler(filtrelenenmüşteriler);
                }
                return false;
            }

            @Override
            public boolean onQueryTextSubmit(String s) {
                Toast.makeText(ListeleActivity.this, "Metot Çalıştı...", Toast.LENGTH_SHORT).show();

                return true;
            }
        });


    }

    private void eklemeEkranınıAç() {

        Intent ekle_intent=new Intent(ListeleActivity.this, EklemeActivity.class);
        ekle_intent.putExtra("müşteriler",müşteriler);
        startActivity(ekle_intent);
    }
}