package com.example.uygulama3;

import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    TextView txt_vize_not, txt_vize_ağırlık, txt_proje_not, txt_proje_ağırlık, txt_final_not, txt_final_ağırlık,txt_ortalama;
    Button btn_hesapla;

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

        txt_vize_not = findViewById(R.id.txt_vize_not);
        txt_vize_ağırlık = findViewById(R.id.txt_vize_ağırlık);
        txt_proje_not = findViewById(R.id.txt_proje_not);
        txt_proje_ağırlık = findViewById(R.id.txt_proje_ağırlık);
        txt_final_not = findViewById(R.id.txt_final_not);
        txt_final_ağırlık = findViewById(R.id.txt_final_ağırlık);
        txt_ortalama=findViewById(R.id.textView);

        btn_hesapla = findViewById(R.id.btn_hesapla);

        btn_hesapla.setOnClickListener(view -> hesapla());

    }

    public void hesapla() {

        if (TextUtils.isEmpty(txt_vize_not.getText().toString())) {
            Toast.makeText(this, "Vize notu boş geçilemez", Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(txt_vize_ağırlık.getText().toString())) {
            Toast.makeText(this, "Vize ağırlığı boş geçilemez", Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(txt_proje_not.getText().toString())) {
            Toast.makeText(this, "Proje notu boş geçilemez", Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(txt_proje_ağırlık.getText().toString())) {
            Toast.makeText(this, "Proje ağırlığı boş geçilemez", Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(txt_final_not.getText().toString())) {
            Toast.makeText(this, "Final notu boş geçilemez", Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(txt_final_ağırlık.getText().toString())) {
            Toast.makeText(this, "Final ağırlığı boş geçilemez", Toast.LENGTH_SHORT).show();
        } else {
            if (!(Double.valueOf(txt_vize_not.getText().toString()) >= 0 && Double.valueOf(txt_vize_not.getText().toString()) <= 100)) {
                Toast.makeText(this, "Vize notu 0 ile 100 arasında değildir.", Toast.LENGTH_SHORT).show();
            }else if(!(Double.valueOf(txt_vize_ağırlık.getText().toString()) >= 0 && Double.valueOf(txt_vize_ağırlık.getText().toString()) <= 100)){
                Toast.makeText(this, "Vize ağırlığı 0 ile 100 arasında değildir.", Toast.LENGTH_SHORT).show();
            }else if(!(Double.valueOf(txt_proje_not.getText().toString()) >= 0 && Double.valueOf(txt_proje_not.getText().toString()) <= 100)){
                Toast.makeText(this, "Proje notu 0 ile 100 arasında değildir.", Toast.LENGTH_SHORT).show();
            }else if(!(Double.valueOf(txt_proje_ağırlık.getText().toString()) >= 0 && Double.valueOf(txt_proje_ağırlık.getText().toString()) <= 100)){
                Toast.makeText(this, "Proje ağırlığı 0 ile 100 arasında değildir.", Toast.LENGTH_SHORT).show();
            }else if(!(Double.valueOf(txt_final_not.getText().toString()) >= 0 && Double.valueOf(txt_final_not.getText().toString()) <= 100)){
                Toast.makeText(this, "Final notu 0 ile 100 arasında değildir.", Toast.LENGTH_SHORT).show();
            }else if(!(Double.valueOf(txt_final_ağırlık.getText().toString()) >= 0 && Double.valueOf(txt_final_ağırlık.getText().toString()) <= 100)){
                Toast.makeText(this, "Final ağırlığı 0 ile 100 arasında değildir.", Toast.LENGTH_SHORT).show();
            }else{
                double vize_ağırlık=Double.valueOf(txt_vize_ağırlık.getText().toString());
                double proje_ağırlık=Double.valueOf(txt_proje_ağırlık.getText().toString());
                double final_ağırlık=Double.valueOf(txt_final_ağırlık.getText().toString());
                if(vize_ağırlık+proje_ağırlık+final_ağırlık!=100){
                    Toast.makeText(this, "Ağırlıklar toplamı 100 olmalıdır.", Toast.LENGTH_SHORT).show();
                }else{

                    double finalnot=Double.valueOf(txt_final_not.getText().toString());
                    if(finalnot<35){
                        Toast.makeText(this, "Notunuzun hesaplanması için final notunuzun 35 ve üstü olması gerekir", Toast.LENGTH_SHORT).show();
                    }else{
                        double vizenot=Double.valueOf(txt_vize_not.getText().toString());
                        double projenot=Double.valueOf(txt_proje_not.getText().toString());
                        double ortalamanot=(vizenot*vize_ağırlık/100)+(projenot*proje_ağırlık/100)+(finalnot*final_ağırlık/100);
                        txt_ortalama.setText("Ortalama Notunuz:"+ortalamanot);
                    }

                }

            }
        }
    }
}