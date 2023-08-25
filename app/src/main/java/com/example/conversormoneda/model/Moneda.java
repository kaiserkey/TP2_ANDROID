package com.example.conversormoneda.model;

import java.util.HashMap;

public class Moneda {
    private HashMap<String, Double> monedas = new HashMap<>();

    public Moneda() {
        monedas.put("dolarEuro", 0.93);
        monedas.put("euroDolar", 1.08);
    }

    public double getValor(String mValor){
        return monedas.get(mValor);
    }

    public HashMap<String, Double> getMonedas() {
        return monedas;
    }

    public void setMonedas(HashMap<String, Double> monedas) {
        this.monedas = monedas;
    }
}
