package com.example.uygulama1;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

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

            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    int position=getBindingAdapterPosition();

                    AlertDialog.Builder ab= new AlertDialog.Builder(itemView.getContext());
                    ab.setTitle("İşlem Seçiniz");
                    ab.setMessage("Müşteri kaydını silmek veya güncellemek için seçim yapınız.");
                    ab.setPositiveButton("Sil", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {

                            müşteriler.remove(position);
                            notifyDataSetChanged();

                        }
                    });
                    ab.setNegativeButton("Güncelle", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            Intent güncelle_intent=new Intent(itemView.getContext(), GüncellemeActivity.class);
                            güncelle_intent.putExtra("müşteriler",müşteriler);
                            güncelle_intent.putExtra("pozisyon",position);
                            itemView.getContext().startActivity(güncelle_intent);
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

        public void setData(Müşteri müşteri){
            txt_adsoyad.setText(müşteri.getAdsoyad());
            txt_mail.setText(müşteri.getMail());
            txt_telefon.setText(müşteri.getTelefon());
        }
    }
}
