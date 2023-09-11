package com.example.conversormoneda;


import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.view.View;

import com.example.conversormoneda.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private MainActivityViewModel vm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        ActivityMainBinding binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(R.layout.activity_main);
        setContentView(binding.getRoot());
        vm= ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication()).create(MainActivityViewModel.class);

        vm.getResultadoM().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                binding.tvResultado.setText(s);
            }
        });

        vm.getSelectEM().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {

                    binding.etDolar.setEnabled(!aBoolean);
                    binding.etEuros.setEnabled(aBoolean);
                    binding.rbDolarEuro.setChecked(!aBoolean);
            }
        });

        vm.getSelectDM().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                    binding.etEuros.setEnabled(!aBoolean);
                    binding.etDolar.setEnabled(aBoolean);
                    binding.rbEuroDolar.setChecked(!aBoolean);
            }
        });

        binding.rbDolarEuro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                vm.getSelectDM().setValue(true);
                vm.getSelectEM().setValue(false);
            }
        });

        binding.rbEuroDolar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                vm.getSelectEM().setValue(true);
                vm.getSelectDM().setValue(false);
            }
        });

        binding.btnConvertir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                vm.convertirMoneda(binding.etDolar.getText().toString(), binding.etEuros.getText().toString(), binding.rbDolarEuro.isChecked(), binding.rbEuroDolar.isChecked());
            }
        });
    }
}