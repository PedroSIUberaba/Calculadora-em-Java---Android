package com.example.prjcalculadora001;

import static android.widget.Toast.LENGTH_LONG;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    public float valor1, valor2, resultado = 0, memoria = 0;
    final String historicoGeral = "historico.txt";
    final String historicoDiario = "Diario.txt";

    Locale loc = new Locale("pt", "BR");

    private void salvarNoHistorico(String operacao) {
        try {
            String dataHora = java.text.DateFormat.getDateInstance(DateFormat.LONG, loc).format(new Date());
            String entrada = "" + dataHora + "\n" + operacao + "\n-------------------------------------\n";
            openFileOutput(historicoGeral, MODE_APPEND).write(entrada.getBytes());
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), "Erro ao salvar no histórico", LENGTH_LONG).show();
        }
    }

    private void salvarNoHistoricoDiario(String operacao) {
        try {
            String dataAtual = java.text.DateFormat.getDateInstance(DateFormat.SHORT, loc).format(new Date());
            String entrada = "" + dataAtual + "\n" + operacao + "\n-------------------------------------\n";
            openFileOutput(historicoDiario, MODE_APPEND).write(entrada.getBytes());
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), "Erro ao salvar no histórico", LENGTH_LONG).show();
        }
    }


    private String lerHistoricoDiario() {
        try {
            String dataAtual = java.text.DateFormat.getDateInstance(DateFormat.SHORT, loc).format(new Date());
            java.io.InputStream in = openFileInput(historicoDiario);
            java.util.Scanner scanner = new java.util.Scanner(in).useDelimiter("\\A");
            StringBuilder historico = new StringBuilder();
            while (scanner.hasNext()) {
                String linha = scanner.next();
                if (linha.startsWith(dataAtual)) {
                    historico.append(linha);
                }
            }
            return historico.length() > 0 ? historico.toString() : "Nenhuma operação realizada hoje.";
        } catch (Exception e) {
            return "Erro ao ler histórico.";
        }
    }


    private String lerHistorico() {
        try {
            java.io.InputStream in = openFileInput(historicoGeral);
            java.util.Scanner scanner = new java.util.Scanner(in).useDelimiter("\\A");
            return scanner.hasNext() ? scanner.next() : "Histórico vazio!";
        } catch (Exception e) {
            return "Erro ao ler histórico.";
        }
    }


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

        EditText txtValor1 = (EditText) findViewById(R.id.txtValor1);
        EditText txtValor2 = (EditText) findViewById(R.id.txtValor2);
        TextView txtResultado = (TextView) findViewById(R.id.txtResultado);
        TextView txtMemoria = (TextView) findViewById(R.id.txtMemoria);
        Button btnSoma = (Button) findViewById(R.id.btnSoma);
        Button btnSub = (Button) findViewById(R.id.btnSub);
        Button btnDiv = (Button) findViewById(R.id.btnDiv);
        Button btnMult = (Button) findViewById(R.id.btnMult);
        Button btnFinalizar = (Button) findViewById(R.id.btnFinalizar);
        Button btnC1 = (Button) findViewById(R.id.btnC1);
        Button btnC2 = (Button) findViewById(R.id.btnC2);
        Button btnCA = (Button) findViewById(R.id.btnCA);
        Button btnR1 = (Button) findViewById(R.id.btnR1);
        Button btnMP = (Button) findViewById(R.id.btnMP);
        Button btnMM = (Button) findViewById(R.id.btnMM);
        Button btnMR = (Button) findViewById(R.id.btnMR);
        Button btnMC = (Button) findViewById(R.id.btnMC);
        Button btnH = (Button) findViewById(R.id.btnH);
        Button btnHC = (Button) findViewById(R.id.btnHC);
        Button btnHF = (Button) findViewById(R.id.btnHF);
        Button btnHFC = (Button) findViewById(R.id.btnHFC);

        btnFinalizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.exit(0);
            }
        });


        //Somar
        btnSoma.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (txtValor1.getText().toString().isEmpty() || txtValor2.getText().toString().isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Preencha os campos", LENGTH_LONG).show();
                } else {
                    valor1 = Float.parseFloat(txtValor1.getText().toString());
                    valor2 = Float.parseFloat(txtValor2.getText().toString());
                    float soma = valor1 + valor2;
                    txtResultado.setText(String.valueOf(soma));
                    salvarNoHistorico(valor1 + " + " + valor2 + " = " + soma);
                    salvarNoHistoricoDiario(valor1 + " + " + valor2 + " = " + soma);
                }
            }
        });


        //Subtrair
        btnSub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (txtValor1.getText().toString().isEmpty() || txtValor2.getText().toString().isEmpty()){
                    Toast.makeText(getApplicationContext(), "Preencha os campos", LENGTH_LONG).show();
                }else{
                    valor1 = Float.parseFloat(txtValor1.getText().toString());
                    valor2 = Float.parseFloat(txtValor2.getText().toString());
                    resultado = valor1-valor2;
                    txtResultado.setText(String.valueOf(resultado));
                    salvarNoHistorico(valor1 + " - " + valor2 + " = " + resultado);
                    salvarNoHistoricoDiario(valor1 + " - " + valor2 + " = " + resultado);
                }
            }
        });

        //Divisao
        btnDiv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (txtValor2.getText().toString().isEmpty()){
                    Toast.makeText(getApplicationContext(), "Erro. Divisão por zero.", LENGTH_LONG).show();
                }
                else{
                    valor1 = Float.parseFloat(txtValor1.getText().toString());
                    valor2 = Float.parseFloat(txtValor2.getText().toString());
                    resultado = valor1/valor2;
                    txtResultado.setText(String.valueOf(resultado));
                    salvarNoHistorico(valor1 + " / " + valor2 + " = " + resultado);
                    salvarNoHistoricoDiario(valor1 + " / " + valor2 + " = " + resultado);
                }
            }
        });

        //Multiplicacao
        btnMult.setOnClickListener(v -> {
            if (txtValor1.getText().toString().isEmpty() || txtValor2.getText().toString().isEmpty()) {
                Toast.makeText(getApplicationContext(), "Preencha os campos", Toast.LENGTH_LONG).show();
            } else {
                valor1 = Float.parseFloat(txtValor1.getText().toString());
                valor2 = Float.parseFloat(txtValor2.getText().toString());
                resultado = valor1 * valor2;
                txtResultado.setText(String.valueOf(resultado));
                salvarNoHistorico(valor1 + " x " + valor2 + " = " + resultado);
                salvarNoHistoricoDiario(valor1 + " x " + valor2 + " = " + resultado);
            }
        });

        //Limpar Valor1
        btnC1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(txtValor1.getText().toString().isEmpty()){
                    Toast.makeText(getApplicationContext(), "Valor zerado!", Toast.LENGTH_LONG).show();
                } else{
                    txtValor1.setText("");
                    Toast.makeText(getApplicationContext(), "Valor apagado com sucesso!", Toast.LENGTH_SHORT).show();
                }
            }
        });
        //Limpar Valor2
        btnC2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(txtValor2.getText().toString().isEmpty()){
                    Toast.makeText(getApplicationContext(), "Valor zerado!", Toast.LENGTH_LONG).show();
                } else{
                    txtValor2.setText("");
                    Toast.makeText(getApplicationContext(), "Valor apagado com sucesso!", Toast.LENGTH_SHORT).show();
                }
            }
        });
        //Limpar Tudo
        btnCA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(txtValor2.getText().toString().isEmpty() || txtValor1.getText().toString().isEmpty()){
                    Toast.makeText(getApplicationContext(), "Preencha os campos", Toast.LENGTH_LONG).show();
                } else{
                    txtValor1.setText("");
                    txtValor2.setText("");
                    txtResultado.setText("0");
                    Toast.makeText(getApplicationContext(), "Valores apagados com sucesso!", Toast.LENGTH_SHORT).show();
                }
            }
        });
        //Valor1 = resultado
        btnR1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String resultadoStr = txtResultado.getText().toString();
                if(txtValor1.getText().toString().isEmpty()){
                    Toast.makeText(getApplicationContext(), "Erro. Valor 1 está vazio!", Toast.LENGTH_LONG).show();
                } else{
                    valor1 = Float.parseFloat(resultadoStr);
                    txtValor1.setText(String.valueOf(valor1));
                    Toast.makeText(getApplicationContext(), "Valor 1 atualizado com sucesso!", Toast.LENGTH_SHORT).show();
                }
            }
        });
        //Botão ADD Memoria
        btnMP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(txtResultado.getText().toString().isEmpty()){
                    Toast.makeText(getApplicationContext(), "Erro. Resultado não encontrado", Toast.LENGTH_LONG).show();
                } else{
                    float aux = Float.parseFloat(txtResultado.getText().toString());
                    memoria = memoria + aux;
                    txtMemoria.setText(String.valueOf(memoria));
                }
            }
        });
        //Botão Tirar Memoria
        btnMM.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(txtResultado.getText().toString().isEmpty()){
                    Toast.makeText(getApplicationContext(), "Erro. Resultado não encontrado", Toast.LENGTH_LONG).show();
                } else{
                    float aux = Float.parseFloat(txtResultado.getText().toString());
                    memoria = memoria - aux;
                    txtMemoria.setText(String.valueOf(memoria));
                }
            }
        });

        //Botão Valor1 = memoria
        btnMR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(txtResultado.getText().toString().isEmpty()){
                    Toast.makeText(getApplicationContext(), "Erro. Resultado não encontrado", Toast.LENGTH_LONG).show();
                } else{
                    txtValor1.setText(String.valueOf(memoria));
                }
            }
        });
        //Botão Limpar Memória
        btnMC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(txtResultado.getText().toString().isEmpty()){
                    Toast.makeText(getApplicationContext(), "Erro. Resultado não encontrado", Toast.LENGTH_LONG).show();
                } else{
                    memoria = 0;
                    txtMemoria.setText(String.valueOf(memoria));
                    Toast.makeText(getApplicationContext(), "Memoria limpa com sucesso!", Toast.LENGTH_LONG).show();
                }
            }
        });

        //Botâo pra salvar histórico apenas do dia, só será visível no dia da operação feita
        btnH.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String historico = lerHistoricoDiario();
                new androidx.appcompat.app.AlertDialog.Builder(MainActivity.this)
                        .setTitle("Histórico de Operações - Diário")
                        .setMessage(historico)
                        .setPositiveButton("Fechar", null)
                        .setNegativeButton("Limpar", new DialogInterface.OnClickListener(){
                            @Override
                            public void onClick(DialogInterface dialog, int which){
                                btnHC.performClick();
                                Toast.makeText(getApplicationContext(), "Histórico limpo", Toast.LENGTH_LONG).show();
                                btnH.performClick();
                            }
                        })
                        .show();
            }
        });


        //Botão para limpar histórico do dia
        btnHC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    openFileOutput(historicoDiario, MODE_PRIVATE).close();
                    Toast.makeText(getApplicationContext(), "Histórico limpo com sucesso!", Toast.LENGTH_SHORT).show();
                } catch (Exception e) {
                    Toast.makeText(getApplicationContext(), "Erro ao limpar histórico", LENGTH_LONG).show();
                }
            }
        });


        //Botão do histórico -> Mostrar arquivo com data, horário e operação completa, valor1-sinal-valor2 e resultado
        btnHF.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String historico = lerHistorico();
                new androidx.appcompat.app.AlertDialog.Builder(MainActivity.this)
                        .setTitle("Histórico de Operações - Completo")
                        .setMessage(historico)
                        .setPositiveButton("Fechar", null)
                        .setNegativeButton("Limpar", new DialogInterface.OnClickListener(){
                            @Override
                            public void onClick(DialogInterface dialog, int which){
                                btnHFC.performClick();
                                Toast.makeText(getApplicationContext(), "Histórico limpo", Toast.LENGTH_LONG).show();
                                btnHF.performClick();
                            }
                        })
                        .show();
            }
        });
        btnHFC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    openFileOutput(historicoGeral, MODE_PRIVATE).close(); // sobrescreve e limpa completo
                    Toast.makeText(getApplicationContext(), "Histórico limpo com sucesso!", Toast.LENGTH_SHORT).show();
                } catch (Exception e) {
                    Toast.makeText(getApplicationContext(), "Erro ao limpar histórico", LENGTH_LONG).show();
                }
            }
        });
    }

}