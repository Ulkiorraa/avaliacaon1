package com.ulkiorra.avaliacaon1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class MenuPrincipal extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_principal);

        findViewById(R.id.codificadorBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuPrincipal.this, Codificador.class);
                startActivity(intent);
            }
        });

        findViewById(R.id.pedraPapelTesouraBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuPrincipal.this, JogoPedra.class);
                startActivity(intent);
            }
        });

        findViewById(R.id.codigoBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuPrincipal.this, Codigo.class);
                startActivity(intent);
            }
        });

        findViewById(R.id.randomBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuPrincipal.this, com.ulkiorra.avaliacaon1.Randomizar.class);
                startActivity(intent);
            }
        });
    }
}
