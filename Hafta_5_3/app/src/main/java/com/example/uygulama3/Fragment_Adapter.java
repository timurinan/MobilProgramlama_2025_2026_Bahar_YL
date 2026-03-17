package com.example.uygulama3;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import java.util.ArrayList;

public class Fragment_Adapter extends FragmentStateAdapter {

    ArrayList<Fragment_Hesap> hesaplar=new ArrayList<Fragment_Hesap>();
    public Fragment_Adapter(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle) {
        super(fragmentManager, lifecycle);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        return hesaplar.get(position);
    }

    @Override
    public int getItemCount() {
        return hesaplar.size();
    }

    public void addHesap(Fragment_Hesap fh){
        hesaplar.add(fh);
    }
}
