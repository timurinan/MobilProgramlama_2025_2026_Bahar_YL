package com.example.uygulama1;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class BenimAdapter extends RecyclerView.Adapter<BenimAdapter.ViewHolder> {

    ArrayList<Müşteri> müşteriler;
    LayoutInflater inflater;

    Veritabani_Islemleri vi;

    public BenimAdapter(ArrayList<Müşteri> müşteriler, Context context) {
        this.müşteriler = müşteriler;
        inflater=LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view=inflater.inflate(R.layout.activity_recyclerview_item,parent,false);
        ViewHolder viewHolder=new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Müşteri müşteri=müşteriler.get(position);
        holder.setMüşteri(müşteri);
    }

    @Override
    public int getItemCount() {
        return müşteriler.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView txt_adsoyad, txt_mail, txt_telefon;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            vi=new Veritabani_Islemleri(itemView.getContext());
            txt_adsoyad=itemView.findViewById(R.id.txt_adsoyad);
            txt_mail=itemView.findViewById(R.id.txt_mail);
            txt_telefon=itemView.findViewById(R.id.txt_telefon);

            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    AlertDialog.Builder diyalog=new AlertDialog.Builder(itemView.getContext());
                    diyalog.setTitle("İşlem Seçiniz");
                    diyalog.setMessage("Silmek için sil, güncellemek için güncelle butonuna basınız, iptal için iptale basınız");
                    //Güncelle butonu
                    diyalog.setPositiveButton("Güncelle", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            int position=getAdapterPosition();
                            int id=müşteriler.get(position).getId();
                            Intent guncelle_intent=new Intent(itemView.getContext(), GuncelleActivity.class);
                            guncelle_intent.putExtra("musteri_id",id);
                            itemView.getContext().startActivity(guncelle_intent);
                        }
                    });
                    //Sil butonu
                    diyalog.setNegativeButton("Sil", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            int position=getAdapterPosition();
                            int id=müşteriler.get(position).getId();
                            vi.sil(id);
                            Intent main_intent=new Intent(itemView.getContext(), MainActivity.class);
                            itemView.getContext().startActivity(main_intent);
                        }
                    });
                    //İptal butonu
                    diyalog.setNeutralButton("İptal", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    });
                    diyalog.show();
                    return true;
                }
            });
        }

        public void setMüşteri(Müşteri müşteri){
            txt_adsoyad.setText(müşteri.getAdsoyad());
            txt_telefon.setText(müşteri.getTelefon());
            txt_mail.setText(müşteri.getMail());
        }
    }


}
