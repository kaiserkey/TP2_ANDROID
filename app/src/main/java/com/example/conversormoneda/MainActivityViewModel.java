package com.example.conversormoneda;

import android.app.Application;
import android.content.Context;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.example.conversormoneda.model.Moneda;

public class MainActivityViewModel extends AndroidViewModel {
    private Context context;
    private MutableLiveData<String> resultadoM;
    private MutableLiveData<Boolean> selectEM;
    private MutableLiveData<Boolean> selectDM;
    public MainActivityViewModel(@NonNull Application application) {
        super(application);
        context = application.getApplicationContext();
    }
    public MutableLiveData<String> getResultadoM(){
        if(resultadoM == null){
            resultadoM = new MutableLiveData<String>();
        }
        return resultadoM;
    }

    public MutableLiveData<Boolean> getSelectEM(){
        if(selectEM == null){
            selectEM = new MutableLiveData<Boolean>();
        }
        return selectEM;
    }

    public MutableLiveData<Boolean> getSelectDM(){
        if(selectDM == null){
            selectDM = new MutableLiveData<Boolean>();
        }
        return selectDM;
    }


    public void convertirMoneda(String dolar,String euro, Boolean selectDolar, Boolean selectEuro){
        Moneda moneda = new Moneda();

        // Verificar si las cadenas no están vacías
        if (euro.isEmpty() && dolar.isEmpty()) {
            Toast.makeText(context, "Ingrese un valor en alguno de los campos dolar/euro.", Toast.LENGTH_LONG).show();
            return;
        }

        double resultado = 0;

        if (selectEuro) {
            if(!euro.isEmpty()){
                try {
                    double euros = Double.parseDouble(euro);
                    resultado = euros * moneda.getValor("euroDolar");
                } catch (NumberFormatException e) {
                    // Manejar la excepción si la conversión falla
                    Toast.makeText(context, "Valor euro no válido.", Toast.LENGTH_LONG).show();
                    return;
                }
            }

        }

        if (selectDolar) {
            if(!dolar.isEmpty()){
                try {
                    double dolares = Double.parseDouble(dolar);
                    resultado = dolares * moneda.getValor("dolarEuro");
                } catch (NumberFormatException e) {
                    // Manejar la excepción si la conversión falla
                    Toast.makeText(context, "Valor dolar no válido.", Toast.LENGTH_LONG).show();
                    return;
                }
            }
        }

        getResultadoM().setValue(String.valueOf(resultado));
    }
}
