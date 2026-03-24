package com.example.uygulama1;

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

        ArrayList<Müşteri> müşteriler=new ArrayList<Müşteri>();

        müşteriler.add(new Müşteri("Timur İnan","timurinan@hotmail.com","05376696627"));
        müşteriler.add(new Müşteri("Ahmet Toprak","ahmetoprak@hotmail.com","0212212211"));
        müşteriler.add(new Müşteri("Sevda İnan","sevda@gmail.com","4545454545454"));
        müşteriler.add(new Müşteri("Mehmet Yalnız","myalniz@yahoo.com","5689864457744757"));
        müşteriler.add(new Müşteri("Timur İnan","timurinan@hotmail.com","05376696627"));
        müşteriler.add(new Müşteri("Ahmet Toprak","ahmetoprak@hotmail.com","0212212211"));
        müşteriler.add(new Müşteri("Sevda İnan","sevda@gmail.com","4545454545454"));
        müşteriler.add(new Müşteri("Mehmet Yalnız","myalniz@yahoo.com","5689864457744757"));
        müşteriler.add(new Müşteri("Timur İnan","timurinan@hotmail.com","05376696627"));
        müşteriler.add(new Müşteri("Ahmet Toprak","ahmetoprak@hotmail.com","0212212211"));
        müşteriler.add(new Müşteri("Sevda İnan","sevda@gmail.com","4545454545454"));
        müşteriler.add(new Müşteri("Mehmet Yalnız","myalniz@yahoo.com","5689864457744757"));

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
}