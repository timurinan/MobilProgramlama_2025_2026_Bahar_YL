package com.example.myloginapp.activities;

import android.content.Intent;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myloginapp.R;
import com.example.myloginapp.adapters.BenimAdapter;
import com.example.myloginapp.model.Müşteri;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ListelemeActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    FloatingActionButton fab;

    ArrayList<Müşteri> müşteriler=new ArrayList<Müşteri>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_listeleme);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        recyclerView=findViewById(R.id.recyclerview);

        BenimAdapter ba=new BenimAdapter(müşteriler, getApplicationContext());

        LinearLayoutManager llm=new LinearLayoutManager(getApplicationContext());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(llm);

        FirebaseDatabase veritabanı=FirebaseDatabase.getInstance();
        DatabaseReference referans=veritabanı.getReference("müşteriler");
        referans.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                müşteriler.clear();
                for (DataSnapshot ds:snapshot.getChildren()) {
                    Müşteri müşteri=ds.getValue(Müşteri.class);
                    müşteri.setAnahtar(ds.getKey());
                    müşteriler.add(müşteri);
                }
                ba.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        recyclerView.setAdapter(ba);

        recyclerView=findViewById(R.id.recyclerview);
        fab=findViewById(R.id.btn_floating_action);
        fab.setOnClickListener(view -> ekle());
    }

    private void ekle() {

        Intent ekle_intent=new Intent(ListelemeActivity.this, EklemeActivity.class);
        startActivity(ekle_intent);
    }
}