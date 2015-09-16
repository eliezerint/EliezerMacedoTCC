package br.grupointegrado.appmetaforadevenda.TelaConsulta;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import java.util.List;

import br.grupointegrado.appmetaforadevenda.Dao.ClienteDao;
import br.grupointegrado.appmetaforadevenda.Listagem.AdapterCidade;
import br.grupointegrado.appmetaforadevenda.Listagem.AdapterCliente;
import br.grupointegrado.appmetaforadevenda.R;
import br.grupointegrado.appmetaforadevenda.TelaCadastro.CadastroPessoaActivity;

public class ConsultaClienteActivity extends ActionBarActivity {

    private Toolbar atoolbar;
    private RecyclerView RecyviewPessoa;
    private List lista;
    private AdapterCliente adaptercliente;

    private ClienteDao clientedao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consulta_cliente);

        atoolbar = (Toolbar)findViewById(R.id.tb_main);
        atoolbar.setTitle("");

        setSupportActionBar(atoolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        RecyviewPessoa =  (RecyclerView)findViewById(R.id.RecyviewPessoa);

        clientedao = new ClienteDao(this);

        Consultacliente();


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

    @Override
    protected void onResume() {
        super.onResume();

        Consultacliente();
    }




    public void Consultacliente( ) {
        final StaggeredGridLayoutManager llm = new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL);
        llm.setGapStrategy(StaggeredGridLayoutManager.GAP_HANDLING_NONE);
        RecyviewPessoa.setLayoutManager(llm);


        adaptercliente = new AdapterCliente(this, clientedao.list()){
            @Override
            protected void onItemClickListener(int adapterPosition, int layoutPosition) {
                // evento de click simples


            }
            @Override
            protected boolean onLongItemClickListener(int adapterPosition, int layoutPosition) {
                // evento e click longo

                return true;
            }
        };




        RecyviewPessoa.setAdapter(adaptercliente);

    }

}
