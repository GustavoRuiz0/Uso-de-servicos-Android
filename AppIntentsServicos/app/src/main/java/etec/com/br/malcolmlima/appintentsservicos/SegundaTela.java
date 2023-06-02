package etec.com.br.malcolmlima.appintentsservicos;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class SegundaTela extends AppCompatActivity {

    private TextView txtnome, txtfone, txtemail, txtendereco;
    private String nomeRecebido, foneRecebido, emailRecebido, enderecoRecebido;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_segunda_tela);

        this.findViews();
        this.getIntentValues();
        this.setViewTexts();

    }

    public void fazerLigacao(View v) {
        AlertDialog.Builder mensagem = new AlertDialog.Builder(SegundaTela.this);
        //TITULO
        mensagem.setTitle("Opções");
        //CORPO DA MENSAGEM
        mensagem.setMessage("Duendes a todo vapor, o que quer deles hoje");
        //BOTAO POSITIVO PARA LIGAR
        mensagem.setPositiveButton("Ligar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Uri uri = Uri.parse("tel:"+foneRecebido);
                Intent ligar = new Intent(Intent.ACTION_DIAL,uri);
                startActivity(ligar);
            }
        });

        //BOTAO NEGATIVO ´
        mensagem.setNegativeButton("Whatsapp", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String contato = "+55" + foneRecebido;
                String url = "https://api.whatsapp.com/send?fone=" + contato;

                try {
                    PackageManager pm = getPackageManager();
                    pm.getPackageInfo("com.whatsapp",PackageManager.GET_ACTIVITIES);
                    Intent whatsapp = new Intent(Intent.ACTION_VIEW);
                    startActivity(whatsapp);
                } catch (PackageManager.NameNotFoundException e){
                    Toast.makeText(SegundaTela.this, "WHats non esta intalado", Toast.LENGTH_SHORT).show();
                }

            }
        });
        mensagem.setIcon(R.drawable.ico_ligar);
        mensagem.show();
    }

    public void enviarEmail(View v) {
        String mailto = "mailto:" + this.emailRecebido +
                "?cc=" + "" +
                "&subject=" + Uri.encode("E-mail de teste pelo app") +
                "&body=" + Uri.encode("");

        Intent emailIntent = new Intent(Intent.ACTION_SENDTO);
        emailIntent.setData(Uri.parse(mailto));

        try {
            startActivity(emailIntent);
        } catch(ActivityNotFoundException e) {
            Toast.makeText(this, "Erro ao enviar o e-mail: " + e, Toast.LENGTH_SHORT).show();
        }
    }

    public void abrirMapa(View v) {
        Uri gmmIntentUri = Uri.parse("geo:0,0?q=" + this.enderecoRecebido);
        Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
        mapIntent.setPackage("com.google.android.apps.maps");
        startActivity(mapIntent);
    }

    private void findViews() {
        this.txtnome = findViewById(R.id.txtNome);
        this.txtfone = findViewById(R.id.txtFone);
        this.txtemail = findViewById(R.id.txtEmail);
        this.txtendereco = findViewById(R.id.txtEndereco);
    }

    private void getIntentValues() {
        Intent telaAtual = getIntent();
        Bundle valores = telaAtual.getExtras();

        this.nomeRecebido = valores.getString("nome");
        this.foneRecebido = valores.getString("fone");
        this.emailRecebido = valores.getString("email");
        this.enderecoRecebido = valores.getString("endereco");
    }

    private void setViewTexts() {
        this.txtnome.setText(this.nomeRecebido);
        this.txtfone.setText(this.foneRecebido);
        this.txtemail.setText(this.emailRecebido);
        this.txtendereco.setText(this.enderecoRecebido);
    }
}