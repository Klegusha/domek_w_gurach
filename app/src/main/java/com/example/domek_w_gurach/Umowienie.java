package com.example.domek_w_gurach;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Umowienie extends AppCompatActivity {

    Button about, back, send;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_umowienie);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        about = findViewById(R.id.onas);
        back = findViewById(R.id.wroc);
        send = findViewById(R.id.wyslij);
        /*Obsługa zdarzenia kliknięcia przycisku "O nas"
         * Przechodzi do nowej aktywności "about" po kliknięciu przycisku
         *
         * @param view - widok reprezentujący kliknięty przycisk
         * @author Artem Prykhoda
         * */
        about.setOnClickListener(v -> {
            finish();
            startActivity(new Intent(Umowienie.this, about.class));
        });
        /*Obsługa zdarzenia kliknięcia przycisku "Wróc"
         * Kończy aktualna aktywność i przechodzi do poprzedniej aktywności po kliknięciu przycisku
         *
         * @param view - widok reprezentujący kliknięty przycisk
         * @author Artem Prykhoda
         * */

        back.setOnClickListener(v->{
            finish();
        });

        send.setOnClickListener(v->{
           Toast.makeText(Umowienie.this, "Zapytanie zostało wysłane! Oczekuj na kontakt!", Toast.LENGTH_SHORT).show();
        });
    }
}