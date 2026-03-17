package com.example.uygulama3;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class Fragment_Hesap extends Fragment {

    Hesap hesap;

    TextView txt_hesapsahibi, txt_hesaptürü,txt_hesapbakiyesi;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View myView=inflater.inflate(R.layout.hesap_fragment_ui,container,false);

        txt_hesapbakiyesi=myView.findViewById(R.id.txt_hesapbakiyesi);
        txt_hesapsahibi=myView.findViewById(R.id.txt_hesapsahibi);
        txt_hesaptürü=myView.findViewById(R.id.txt_hesaptürü);

        txt_hesaptürü.setText(this.hesap.getHesaptürü());
        txt_hesapsahibi.setText(this.hesap.getHesapsahibi());
        txt_hesapbakiyesi.setText(String.valueOf(this.hesap.getHesapbakiyesi()));

        return myView;
    }

    public Fragment_Hesap(Hesap hesap) {
        this.hesap = hesap;
    }
}
