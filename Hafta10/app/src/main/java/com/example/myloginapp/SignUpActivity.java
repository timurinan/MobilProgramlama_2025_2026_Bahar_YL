package com.example.myloginapp;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SignUpActivity extends AppCompatActivity {

    TextView txt_mail_signup, txt_şifre1_signup, txt_şifre2_signup;
    Button btn_kaydol;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_sign_up);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        txt_mail_signup=findViewById(R.id.txt_mail_signup);
        txt_şifre1_signup=findViewById(R.id.txt_şifre1_signup);
        txt_şifre2_signup=findViewById(R.id.txt_şifre2_signup);
        btn_kaydol=findViewById(R.id.btn_kaydol);

        btn_kaydol.setOnClickListener(view -> kaydol());
    }

    private void kaydol() {

        if(TextUtils.isEmpty(txt_mail_signup.getText())){
            Toast.makeText(this, "Mail alanı boş bırakılamaz...", Toast.LENGTH_SHORT).show();
        }else{
            if(TextUtils.isEmpty(txt_şifre1_signup.getText())||TextUtils.isEmpty(txt_şifre2_signup.getText())){
                Toast.makeText(this, "Şifre alanları boş bırakılamaz...", Toast.LENGTH_SHORT).show();
            }else{
                if(!Patterns.EMAIL_ADDRESS.matcher(txt_mail_signup.getText()).matches()){
                    Toast.makeText(this, "Mail formatı yanlıştır...", Toast.LENGTH_SHORT).show();
                }else{
                    if((txt_şifre2_signup.getText().equals(txt_şifre1_signup.getText()))){
                        Toast.makeText(this, "Şifreler eşleşmemektedir...", Toast.LENGTH_SHORT).show();
                    }else{
                        FirebaseAuth fba=FirebaseAuth.getInstance();
                        fba.createUserWithEmailAndPassword(txt_mail_signup.getText().toString(),txt_şifre1_signup.getText().toString())
                                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                    @Override
                                    public void onComplete(@NonNull Task<AuthResult> task) {
                                        if(task.isSuccessful()){
                                            FirebaseUser user=fba.getCurrentUser();
                                            user.sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
                                                @Override
                                                public void onComplete(@NonNull Task<Void> task) {
                                                    if(task.isSuccessful()){
                                                        Toast.makeText(SignUpActivity.this, "Kullanıcıya doğrulama maili gönderildi...", Toast.LENGTH_SHORT).show();
                                                    }else{
                                                        Toast.makeText(SignUpActivity.this, "Doğrulama maili gönderilemedi...", Toast.LENGTH_SHORT).show();
                                                    }
                                                }
                                            });
                                        }else{
                                            Toast.makeText(SignUpActivity.this, "Kullanıcı oluşturulamadı...", Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                });
                    }
                }
            }
        }
    }
}