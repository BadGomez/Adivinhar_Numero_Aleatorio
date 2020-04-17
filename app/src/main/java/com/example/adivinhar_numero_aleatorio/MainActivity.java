package com.example.adivinhar_numero_aleatorio;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private Random random = new Random();
    private int numeroAdivinhar = random.nextInt(10) + 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void TentarNumero (View view){

    }
}
