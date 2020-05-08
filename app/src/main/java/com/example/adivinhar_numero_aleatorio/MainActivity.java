package com.example.adivinhar_numero_aleatorio;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputEditText;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private int numeroAdivinhar = NumerosAleatorios.proximoNumero();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    static final String STATE_NUMEROADIVINHAR = "numeroAdivinhar";
    static final String STATE_NUMEROTENTATIVAS = "numeroTentativas";

    @Override
    protected void onSaveInstanceState(@NonNull Bundle savedInstanceState) {
        savedInstanceState.putInt(STATE_NUMEROADIVINHAR, numeroAdivinhar);
        savedInstanceState.putInt(STATE_NUMEROTENTATIVAS, numerotentativas);

        super.onSaveInstanceState(savedInstanceState);
    }

    public void onRestoreInstanceState(Bundle savedInstanceState){
        super.onRestoreInstanceState(savedInstanceState);

        numeroAdivinhar = savedInstanceState.getInt(STATE_NUMEROADIVINHAR);
        numerotentativas = savedInstanceState.getInt(STATE_NUMEROTENTATIVAS);
    }

    int numerotentativas;
    public void TentarNumero (View view) {
        TextInputEditText TextInputEditNumeroRandom = (TextInputEditText) findViewById(R.id.TextInputEditNumeroRandom);
        TextView TextViewResultado = (TextView) findViewById(R.id.textViewResultado);
        final TextView TextViewNumeroTentativas = (TextView) findViewById(R.id.textViewNumeroTentativas);

        String strTentativa = TextInputEditNumeroRandom.getText().toString();


        int tentativa;
        try {
            tentativa = Integer.parseInt(strTentativa);
        } catch (NumberFormatException e) {
            TextInputEditNumeroRandom.setError(getString(R.string.Numero_necessario));
            TextInputEditNumeroRandom.requestFocus();
            return;
        }

        if (tentativa > 10 || tentativa < 1) {
            TextInputEditNumeroRandom.setError(getString(R.string.Intervalor_numero_permitido));
            TextInputEditNumeroRandom.requestFocus();
            return;
        }

        if (numeroAdivinhar == tentativa) {
            TextViewResultado.setTextColor(Color.GREEN);
            TextViewResultado.setText(R.string.Resposta_Correta);
            numerotentativas++;
            TextViewNumeroTentativas.setTextColor(Color.GREEN);
            TextViewNumeroTentativas.setText(String.valueOf(numerotentativas));

            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Novo jogo");
            builder.setMessage("Quer jogar novamente?");
            builder.setCancelable(false);
            builder.setPositiveButton("sim", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    novoJogo();
                    TextViewNumeroTentativas.setText("Tentativas: 0");
                    TextViewNumeroTentativas.setText("");
                }
            });
            builder.setNegativeButton("Não", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    finish();
                }
            });
            builder.show();
        } else if (numeroAdivinhar > tentativa) {
            TextViewResultado.setTextColor(Color.RED);
            TextViewResultado.setText(R.string.Numero_Maior);
            numerotentativas++;
            TextViewNumeroTentativas.setTextColor(Color.RED);
            TextViewNumeroTentativas.setText(String.valueOf(numerotentativas));
        } else {
            TextViewResultado.setTextColor(Color.RED);
            TextViewResultado.setText(R.string.Número_Menor);
            numerotentativas++;
            TextViewNumeroTentativas.setTextColor(Color.RED);
            TextViewNumeroTentativas.setText(String.valueOf(numerotentativas));
        }
    }

    private void novoJogo() {
        numeroAdivinhar = NumerosAleatorios.proximoNumero();
        numerotentativas = 0;
    }
}
