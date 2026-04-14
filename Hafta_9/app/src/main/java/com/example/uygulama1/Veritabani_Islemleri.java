package com.example.uygulama1;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class Veritabani_Islemleri extends SQLiteOpenHelper {

    private static final String veritabani_ismi="Müsteri_Bilgileri";
    private static final String tablo_ismi="tablo_musteri";
    private static final String sutun_id="id";
    private static final String sutun_ad_soyad="ad_soyad";
    private static final String sutun_mail="mail";
    private static final String sutun_telefon="telefon";

    public Veritabani_Islemleri(@Nullable Context context) {
        super(context,veritabani_ismi,null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String crate_sorgusu="CREATE TABLE "+tablo_ismi+
                " ("+sutun_id+" INTEGER PRIMARY KEY,"+sutun_ad_soyad+" TEXT,"+ sutun_mail+" TEXT,"+sutun_telefon+" TEXT"+")";
        db.execSQL(crate_sorgusu);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public boolean ekle(Müşteri müşteri){

        ArrayList<Müşteri> müşteriler=new ArrayList<>();
        müşteriler= (ArrayList<Müşteri>) listele();
        for (int i = 0; i <müşteriler.size() ; i++) {
            Log.d("Durum",müşteriler.get(i).getMail());
            if(müşteriler.get(i).getMail().equals(müşteri.getMail())){
                Log.d("Durum",müşteriler.get(i).getMail());
                return false;
            }
        }
        SQLiteDatabase db=getWritableDatabase();
        ContentValues values=new ContentValues();//insert edilecek değerler bunun içine yerleştiriliyor.
        values.put(sutun_ad_soyad,müşteri.getAdsoyad());
        values.put(sutun_mail,müşteri.getMail());
        values.put(sutun_telefon,müşteri.getTelefon());
        db.insert(tablo_ismi,null,values);
        db.close();
        return true;
    }

    public List<Müşteri> listele(){

        SQLiteDatabase db=getWritableDatabase();
        String select_sorgusu="select * from "+tablo_ismi;
        Cursor cursor=db.rawQuery(select_sorgusu,null);
        ArrayList<Müşteri> müşteri_listesi=new ArrayList<Müşteri>();

        if(cursor.moveToFirst()){
            do{
                int id=cursor.getInt(0);
                String adsoyad=cursor.getString(1);
                String mail=cursor.getString(3);
                String telefon=cursor.getString(2);
                Müşteri müşteri=new Müşteri(adsoyad,mail,telefon);
                müşteri.setId(id);
                müşteri_listesi.add(müşteri);
            }while(cursor.moveToNext());
        }
        cursor.close();
        db.close();
        //müşteri_listesi.add(new Müşteri("Timur İnan","timurinan@hotmail.com","09989849389"));
        return müşteri_listesi;
    }

    public void guncelle(Müşteri müşteri){

        SQLiteDatabase db=getWritableDatabase();
        ContentValues values=new ContentValues();//insert edilecek değerler bunun içine yerleştiriliyor.
        values.put(sutun_ad_soyad,müşteri.getAdsoyad());
        values.put(sutun_mail,müşteri.getMail());
        values.put(sutun_telefon,müşteri.getTelefon());
        String[] whereArgs={String.valueOf(müşteri.getId())};
        db.update(tablo_ismi,values,"id=?",whereArgs);
        db.close();
    }

    public Müşteri getMüşteri(int id){
        SQLiteDatabase db=getWritableDatabase();
        String sorgu= "select * from "+tablo_ismi+" where id="+id;
        Cursor cursor=db.rawQuery(sorgu,null);
        cursor.moveToFirst();
        String adsoyad=cursor.getString(1);
        String mail=cursor.getString(3);
        String telefon=cursor.getString(2);
        Müşteri müşteri=new Müşteri(adsoyad,mail,telefon);
        müşteri.setId(cursor.getInt(0));
        return müşteri;
    }

    public void sil(int id){
        SQLiteDatabase db=getWritableDatabase();
        String delete_sorgusu=" delete from "+tablo_ismi+ " where id="+id;
        db.execSQL(delete_sorgusu);
        db.close();
    }
}
