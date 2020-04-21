package com.example.adivinhar_numero_aleatorio;

import java.util.Random;

public class NumerosAleatorios {
    public static Random random = new Random();

    /**
     *
     * @return devolve um número aleatório de 1 a 10;
     */

    public static int proximoNumero(){
        return random.nextInt(10) + 1;
    }
}
