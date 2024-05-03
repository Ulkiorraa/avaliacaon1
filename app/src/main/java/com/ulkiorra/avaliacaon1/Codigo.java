package com.ulkiorra.avaliacaon1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class Codigo extends AppCompatActivity {

    private RecyclerView recyclerView;
    private com.ulkiorra.avaliacaon1.CodigoAdapter adapter;
    private com.ulkiorra.avaliacaon1.CodigoSecreto codigoSecreto;
    private Button voltarMenu, enviar;
    private TextView feedbackTextView, tentativasTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.codigo);

        EditText[] editTexts = new EditText[4];
        editTexts[0] = findViewById(R.id.edit_text_1);
        editTexts[1] = findViewById(R.id.edit_text_2);
        editTexts[2] = findViewById(R.id.edit_text_3);
        editTexts[3] = findViewById(R.id.edit_text_4);
        

        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        voltarMenu = findViewById(R.id.voltar);
        enviar = findViewById(R.id.send_button);
        feedbackTextView = findViewById(R.id.result_text_view);
        tentativasTextView = findViewById(R.id.tentativas_text_view);

        voltarMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Codigo.this, com.ulkiorra.avaliacaon1.MenuPrincipal.class);
                startActivity(intent);
            }
        });

        enviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<Integer> tentativa = new ArrayList<>();
                for (EditText editText : editTexts) {
                    try {
                        tentativa.add(Integer.parseInt(editText.getText().toString()));
                    } catch (NumberFormatException e) {
                        // Mostrar mensagem de erro ao usuário
                        feedbackTextView.setText("Erro: valor inválido");
                        return; // Encerra a execução do método se houver um erro
                    }
                }

                // Obter o feedback da tentativa
                String feedback;
                feedback = codigoSecreto.exibirFeedback(tentativa);
                feedbackTextView.setText(feedback);

                // Atualizar o RecyclerView com todas as tentativas
                enviarTentativa(tentativa);

                // Atualizar o texto do TextView com o número de tentativas
                atualizarTextoTentativasTextView();

                //Limpa todos os campos
                limparCampos();
            }
        });

        codigoSecreto = new com.ulkiorra.avaliacaon1.CodigoSecreto();

        List<List<Integer>> historicoTentativas;
        historicoTentativas = codigoSecreto.getHistoricoTentativas();
        adapter = new com.ulkiorra.avaliacaon1.CodigoAdapter(historicoTentativas);
        recyclerView.setAdapter(adapter);
    }


    public void enviarTentativa(List<Integer> tentativa) {
        codigoSecreto.adicionarTentativa(tentativa);
        List<List<Integer>> historicoTentativas;
        historicoTentativas = codigoSecreto.getHistoricoTentativas();
        adapter.setTentativas(historicoTentativas);
        adapter.notifyDataSetChanged(); // Notificar o adaptador para atualizar sua layout
        atualizarTextoTentativasTextView();
    }

    private void atualizarTextoTentativasTextView() {
        tentativasTextView.setText("Número de tentativas: " + String.valueOf(codigoSecreto.getNumeroTentativas()));
    }

    private void limparCampos() {
        EditText[] editTexts = new EditText[4];
        editTexts[0] = findViewById(R.id.edit_text_1);
        editTexts[1] = findViewById(R.id.edit_text_2);
        editTexts[2] = findViewById(R.id.edit_text_3);
        editTexts[3] = findViewById(R.id.edit_text_4);

        for (EditText editText : editTexts) {
            editText.setText(""); // Limpa o conteúdo do campo de texto
        }
    }
}
