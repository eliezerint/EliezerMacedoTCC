package br.grupointegrado.appmetaforadevenda.TelaConsulta;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import br.grupointegrado.appmetaforadevenda.R;
import br.grupointegrado.appmetaforadevenda.TelaCadastro.CadastroPessoaActivity;

public class ConsultaClienteActivity extends ActionBarActivity {

    private Toolbar atoolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consulta_cliente);

        atoolbar = (Toolbar)findViewById(R.id.tb_main);


        setSupportActionBar(atoolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_consulta_cliente, menu);
        return true;
    }
    public void CadastrarCliente(View view) {

        Intent i = new Intent(this.getApplication(), CadastroPessoaActivity.class);


        startActivity(i);


    }

        @Override
        public boolean onOptionsItemSelected(MenuItem item) {
            int id = item.getItemId();

            if (id == android.R.id.home) {
                finish();
            }

            return true;
        }

}
