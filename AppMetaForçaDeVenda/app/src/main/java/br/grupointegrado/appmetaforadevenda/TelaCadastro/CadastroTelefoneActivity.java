package br.grupointegrado.appmetaforadevenda.TelaCadastro;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.rengwuxian.materialedittext.MaterialEditText;

import br.grupointegrado.appmetaforadevenda.Dao.ClienteDao;
import br.grupointegrado.appmetaforadevenda.Pessoa.Telefone;
import br.grupointegrado.appmetaforadevenda.R;

public class CadastroTelefoneActivity extends ActionBarActivity {

    private MaterialEditText EditNumero;
    private String cpf;
    private Integer idpessoa;

    private ClienteDao clientedao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_telefone);


        EditNumero = (MaterialEditText)findViewById(R.id.EditNumero);

        clientedao = new ClienteDao(this);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_telefone, menu);
        return true;

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
           switch (item.getItemId()){



         }

        return true;
    }


}
