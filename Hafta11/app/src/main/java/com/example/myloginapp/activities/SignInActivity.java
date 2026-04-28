package com.example.myloginapp.activities;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.myloginapp.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignInActivity extends AppCompatActivity {

    TextView txt_mail, txt_şifre, txt_kaydol, txt_şifremiunuttum;
    Button btn_girişyap;

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
        txt_kaydol=findViewById(R.id.txt_kaydol);
        txt_mail=findViewById(R.id.txt_mail);
        txt_şifre=findViewById(R.id.txt_şifre);
        txt_şifremiunuttum=findViewById(R.id.txt_şifremi_unuttum);
        btn_girişyap=findViewById(R.id.btn_girişyap);

        txt_şifremiunuttum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth fba=FirebaseAuth.getInstance();
                fba.sendPasswordResetEmail(txt_mail.getText().toString()).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(SignInActivity.this, "Resetleme maili gönderildi...", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });

        txt_kaydol.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent_kaydol=new Intent(SignInActivity.this, SignUpActivity.class);
                startActivity(intent_kaydol);
            }
        });

        btn_girişyap.setOnClickListener(view -> girişYap());
    }

    private void girişYap() {

        if(TextUtils.isEmpty(txt_mail.getText())){
            Toast.makeText(this, "Mail alanı boş bırakılamaz..", Toast.LENGTH_SHORT).show();
        }else{
            if(TextUtils.isEmpty(txt_şifre.getText())){
                Toast.makeText(this, "Şifre boş bırakılamaz...", Toast.LENGTH_SHORT).show();
            }else{
                if(!Patterns.EMAIL_ADDRESS.matcher(txt_mail.getText()).matches()){
                    Toast.makeText(this, "Mail uygun formatta değildir", Toast.LENGTH_SHORT).show();
                }else{
                    FirebaseAuth fba=FirebaseAuth.getInstance();

                    fba.signInWithEmailAndPassword(txt_mail.getText().toString(), txt_şifre.getText().toString())
                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if(task.isSuccessful()){
                                        if(fba.getCurrentUser().isEmailVerified()){
                                            Toast.makeText(SignInActivity.this, "Başarıyla Giriş Yapıldı..", Toast.LENGTH_SHORT).show();
                                            Intent listele_intent=new Intent(SignInActivity.this, ListelemeActivity.class);
                                            startActivity(listele_intent);
                                        }else{
                                            Toast.makeText(SignInActivity.this, "Email doğrulaması yapılmamıştır...", Toast.LENGTH_SHORT).show();
                                            AlertDialog.Builder diyalog=new AlertDialog.Builder(SignInActivity.this);
                                            diyalog.setTitle("Doğrulanmamış Mail");
                                            diyalog.setMessage("Doğrulama maili gönderilmiş ancak kabul edilmemiş böyle bir mail mevcuttur.\n" +
                                                    "Maili iptal ederek tekrardan mail ile kaydolmak için tamam'a\n" +
                                                    "Mailinize giderek doğrulama yapmak için iptal e basınız.");
                                            diyalog.setPositiveButton("Tamam", new DialogInterface.OnClickListener() {
                                                @Override
                                                public void onClick(DialogInterface dialogInterface, int i) {
                                                    fba.getCurrentUser().delete();
                                                    Toast.makeText(SignInActivity.this, "Mail iptal edilmiştir.", Toast.LENGTH_SHORT).show();
                                                    Intent intent_signup=new Intent(SignInActivity.this, SignUpActivity.class);
                                                    startActivity(intent_signup);
                                                }
                                            });
                                            diyalog.setNegativeButton("İptal", new DialogInterface.OnClickListener() {
                                                @Override
                                                public void onClick(DialogInterface dialogInterface, int i) {

                                                }
                                            });
                                            diyalog.show();
                                        }
                                    }else{
                                        Toast.makeText(SignInActivity.this, "Giriş Yapılamadı....", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                }
            }
        }
    }
}