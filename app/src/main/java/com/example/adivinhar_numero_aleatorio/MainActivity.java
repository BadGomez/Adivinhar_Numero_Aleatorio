package com.example.adivinhar_numero_aleatorio;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputEditText;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private Random random = new Random();
    private int numeroAdivinhar = random.nextInt(10) + 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    int numerotentativas;
    public void TentarNumero (View view){
        Intent intent = new Intent(this, MainActivity.class);

        TextInputEditText TextInputEditNumeroRandom = (TextInputEditText) findViewById(R.id.TextInputEditNumeroRandom);
        TextView TextViewResultado = (TextView) findViewById(R.id.textViewResultado);
        TextView TextViewNumeroTentativas = (TextView) findViewById(R.id.textViewNumeroTentativas);

        String strTentativa = TextInputEditNumeroRandom.getText().toString();


        int tentativa;
        try {
            tentativa = Integer.parseInt(strTentativa);
            }catch (NumberFormatException e){
            TextInputEditNumeroRandom.setError(getString(R.string.Numero_necessario));
            TextInputEditNumeroRandom.requestFocus();
            return;
        }

        if(tentativa > 10 || tentativa <1){
            TextInputEditNumeroRandom.setError(getString(R.string.Intervalor_numero_permitido));
            TextInputEditNumeroRandom.requestFocus();
            return;
        }

        if(numeroAdivinhar == tentativa) {
            TextViewResultado.setText(R.string.Resposta_Correta);
            TextViewNumeroTentativas.setText(String.valueOf(numerotentativas));
        }else{
            TextViewResultado.setText(R.string.Resposta_Errada);
            numerotentativas++;
            TextViewNumeroTentativas.setText(String.valueOf(numerotentativas));
        }
    }
}
