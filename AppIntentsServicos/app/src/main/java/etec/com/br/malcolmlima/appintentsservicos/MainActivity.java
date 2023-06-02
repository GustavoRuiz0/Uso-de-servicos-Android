package etec.com.br.malcolmlima.appintentsservicos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText edNome, edFone, edEmail, edEndereco;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.edNome = findViewById(R.id.edtNome);
        this.edFone = findViewById(R.id.edtFone);
        this.edEmail = findViewById(R.id.edtEmail);
        this.edEndereco = findViewById(R.id.edtEndereco);
    }

    // MÉTODO QUE ENVIA OS DADOS - OBS View v é obrigatório
    public void onClickEnviar(View v) {
        Intent tela2 = new Intent(MainActivity.this, SegundaTela.class);

        if(edNome.getText().toString().isEmpty()) {
            edNome.setError("Insira seu nome");
            edNome.requestFocus();
        } else if(edFone.getText().toString().isEmpty()) {
            edFone.setError("Insira seu telefone");
            edFone.requestFocus();
        } else if(edEmail.getText().toString().isEmpty()) {
            edEmail.setError("Insira seu email");
            edEmail.requestFocus();
        } else if(edEndereco.getText().toString().isEmpty()) {
            edEndereco.setError("Insira seu endereço");
            edEndereco.requestFocus();
        } else {
            tela2.putExtra("nome", edNome.getText().toString());
            tela2.putExtra("fone", edFone.getText().toString());
            tela2.putExtra("email", edEmail.getText().toString());
            tela2.putExtra("endereco", edEndereco.getText().toString());
            startActivity(tela2);
            Toast.makeText(this, "Dados enviados com sucesso!", Toast.LENGTH_SHORT).show();
        }

    }
}