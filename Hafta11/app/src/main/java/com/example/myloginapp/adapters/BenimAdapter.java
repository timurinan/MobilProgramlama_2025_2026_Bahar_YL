package com.example.myloginapp.adapters;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myloginapp.R;
import com.example.myloginapp.activities.GuncelleActivity;
import com.example.myloginapp.model.Müşteri;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class BenimAdapter extends RecyclerView.Adapter<BenimAdapter.MyViewHolder> {

    ArrayList<Müşteri> müşteriler;
    Context context;

    public BenimAdapter(ArrayList<Müşteri> müşteriler, Context context) {
        this.müşteriler = müşteriler;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_view_item,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        Müşteri müşteri=müşteriler.get(position);
        holder.txt_telefon.setText(müşteri.getTelefon());
        holder.txt_mail.setText(müşteri.getMail());
        holder.txt_adsoyad.setText(müşteri.getAdsoyad());
    }

    @Override
    public int getItemCount() {
        return müşteriler.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        TextView txt_adsoyad, txt_mail,txt_telefon;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            txt_adsoyad=itemView.findViewById(R.id.txt_rv_item_adsoyad);
            txt_mail=itemView.findViewById(R.id.txt_rv_item_mail);
            txt_telefon=itemView.findViewById(R.id.txt_rv_item_telefon);
            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {

                    AlertDialog.Builder ab=new AlertDialog.Builder(view.getContext());
                    int position=getBindingAdapterPosition();
                    Log.d("pozisyon", position+"");
                    FirebaseDatabase veritabanı=FirebaseDatabase.getInstance();
                    ab.setTitle("Seçim Yapınız");
                    ab.setMessage("Silmek için sil, güncellemek için güncelle butonuna, devam etmek için vazgeç butonuna basınız.");
                    ab.setPositiveButton("Sil", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            DatabaseReference referans= veritabanı.getReference("müşteriler");
                            referans.child(müşteriler.get(position).getAnahtar()).removeValue();
                            Toast.makeText(view.getContext(), "Başarıyla Silindi", Toast.LENGTH_SHORT).show();
                            notifyDataSetChanged();
                        }
                    });
                    ab.setNegativeButton("Güncelle", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            Müşteri güncellenekmüşteri=müşteriler.get(getBindingAdapterPosition());
                            Intent guncelle_intent=new Intent(itemView.getContext(), GuncelleActivity.class);
                            guncelle_intent.putExtra("müşteri",güncellenekmüşteri);
                            view.getContext().startActivity(guncelle_intent);
                        }
                    });
                    ab.setNeutralButton("Vazgeç", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {

                        }
                    });
                    ab.show();
                    return false;
                }
            });
        }
    }
}
