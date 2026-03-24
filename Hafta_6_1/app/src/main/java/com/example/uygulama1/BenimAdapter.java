package com.example.uygulama1;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class BenimAdapter extends RecyclerView.Adapter<BenimAdapter.ViewHolder>{

    ArrayList<Müşteri> müşteriler=new ArrayList<Müşteri>();
    LayoutInflater inflater;

    public void setFiltrelenenMüşteriler(ArrayList<Müşteri> filtrelenenler){
        müşteriler=filtrelenenler;
        notifyDataSetChanged();
    }

    public BenimAdapter(LayoutInflater inflater, ArrayList<Müşteri> müşteriler) {
        this.inflater = inflater;
        this.müşteriler = müşteriler;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view=inflater.inflate(R.layout.recyclerview_item,parent,false);
        ViewHolder vh=new ViewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Müşteri müşteri=müşteriler.get(position);
        holder.setData(müşteri);
    }

    @Override
    public int getItemCount() {
        return müşteriler.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView txt_adsoyad, txt_mail,txt_telefon;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txt_adsoyad=itemView.findViewById(R.id.txt_adsoyad);
            txt_mail=itemView.findViewById(R.id.txt_mail);
            txt_telefon=itemView.findViewById(R.id.txt_telefon);
        }

        public void setData(Müşteri müşteri){
            txt_adsoyad.setText(müşteri.getAdsoyad());
            txt_mail.setText(müşteri.getMail());
            txt_telefon.setText(müşteri.getTelefon());
        }
    }
}
