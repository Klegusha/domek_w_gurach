package com.example.domek_w_gurach;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    int likes = 0;
    TextView likesTextView;
    Button polub, usun, zapisz, umow;
    String NAZWA_PREFERENCJI = "licznik_polubien";
    String KLUCZ_ILOSC_LIKE = "KLUCZ_ILOSC_LIKE";
    int ILOSC_LIKE = 0;
    private SharedPreferences preferencje;
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

        polub = findViewById(R.id.polub);
        usun = findViewById(R.id.usun);
        zapisz = findViewById(R.id.zapisz);
        likesTextView = findViewById(R.id.ilosc_polubien);
        umow = findViewById(R.id.umow);

        preferencje = getSharedPreferences(NAZWA_PREFERENCJI, Context.MODE_PRIVATE);
        likes = preferencje.getInt(KLUCZ_ILOSC_LIKE, ILOSC_LIKE);
        likesTextView.setText(likes + " polubień");


        /*Obsługa zdarzenia kliknięcia przycisku "Polub"
        * Inkrementuje licznik polubień i aktualizuje TextView z liczbą polubień
        *
        * @param view - widok reprezentujący kliknięty przycisk
        * @author Artem Prykhoda
        * */
        polub.setOnClickListener(v -> {
            likes++;
            likesTextView.setText(likes + " polubień");
        });

        /*Obsługa zdarzenia kliknięcia przycisku "Zapisz"
         * Zapisuję aktualną ilość polubien do SharedPreferences
         *
         * @param view - widok reprezentujący kliknięty przycisk
         * @author Artem Prykhoda
         * */
        zapisz.setOnClickListener(v->{
            saveLikes();
        });
        /*Obsługa zdarzenia kliknięcia przycisku "Usuń"
         * Dekrementuje licznik polubień i aktualizuje TextView z liczbą polubień
         *
         * @param view - widok reprezentujący kliknięty przycisk
         * @author Artem Prykhoda
         * */
        usun.setOnClickListener(v->{
            if(likes>0) {
                likes--;
            }
           likesTextView.setText(likes + " polubień");
        });
        /*Obsługa zdarzenia kliknięcia przycisku "Umów"
         * Przechodzi do nowej aktywności "Umowienie"
         *
         * @param view - widok reprezentujący kliknięty przycisk
         * @author Artem Prykhoda
         * */
        umow.setOnClickListener(v->{
            startActivity(new android.content.Intent(MainActivity.this, Umowienie.class));
        });
    }
    /*Zaimplementowanie funkcji saveLikes
     * Zapisuję aktualną ilość polubien do SharedPreferences
     *
     * @param view - widok reprezentujący kliknięty przycisk
     * @author Artem Prykhoda
     * */
    public void saveLikes(){
        SharedPreferences settings = getSharedPreferences(NAZWA_PREFERENCJI, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = settings.edit();
        editor.putInt(KLUCZ_ILOSC_LIKE, likes);
        editor.apply();
    }

    protected void onStop(){
        super.onStop();
        saveLikes();
    }

    protected void onPause(){
        super.onPause();
        saveLikes();
    }
}