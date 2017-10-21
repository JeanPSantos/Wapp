package br.com.wapp.cursoandroid.wapp.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.github.rtoshiro.util.format.SimpleMaskFormatter;
import com.github.rtoshiro.util.format.text.MaskTextWatcher;

import java.util.Random;

import br.com.wapp.cursoandroid.wapp.R;

public class LoginActivity extends AppCompatActivity {

    private EditText telefone;
    private EditText codPais;
    private EditText codArea;
    private EditText nome;
    private Button cadastrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        telefone = (EditText) findViewById(R.id.edit_telefone);
        nome = (EditText) findViewById(R.id.edit_nome);
        codArea = (EditText) findViewById(R.id.edit_cod_area);
        codPais = (EditText) findViewById(R.id.edit_cod_pais);
        cadastrar = (Button) findViewById(R.id.bt_cadastrar);


        //Definição das máscaras
        SimpleMaskFormatter simpleMaskCodPais = new SimpleMaskFormatter("+NN");
        SimpleMaskFormatter simpleMaskCodArea = new SimpleMaskFormatter("NN");
        SimpleMaskFormatter simpleMaskTelefone = new SimpleMaskFormatter("NNNNN-NNNN");

        MaskTextWatcher maskCodPais = new MaskTextWatcher(codPais, simpleMaskCodPais);
        MaskTextWatcher maskCodArea = new MaskTextWatcher(codArea, simpleMaskCodArea);
        MaskTextWatcher maskTelefone = new MaskTextWatcher(telefone, simpleMaskTelefone);

        codPais.addTextChangedListener(maskCodPais);
        codArea.addTextChangedListener(maskCodArea);
        telefone.addTextChangedListener(maskTelefone);

        cadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String nomeUsuario = nome.getText().toString();
                String telefoneCompleto = codPais.getText().toString() +
                        codArea.getText().toString() +
                        telefone.getText().toString();
                String telefoneSemFormatacao = telefoneCompleto.replace("+", ""); //Substitui o primeiro conteudo pela segundo dentro das aspas
                telefoneSemFormatacao = telefoneSemFormatacao.replace("-", ""); //Substitui o primeiro conteudo pela segundo dentro das aspas

                //Gerar token
                Random randomico = new Random();
                int numeroRandomico = randomico.nextInt(9999 - 1000) + 1000; //Garantir que seja gerado numero randomico de 4 digitos, entre 1000 e 9999
                String token = String.valueOf(numeroRandomico);


            }
        });
    }
}
