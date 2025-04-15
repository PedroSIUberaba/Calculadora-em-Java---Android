package com.example.prjcalculadora001;

import static android.widget.Toast.LENGTH_LONG;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    public float valor1, valor2, resultado = 0, memoria = 0;

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
    }
}