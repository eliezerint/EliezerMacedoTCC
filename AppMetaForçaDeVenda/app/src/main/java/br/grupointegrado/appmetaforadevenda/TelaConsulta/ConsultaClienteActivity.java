package br.grupointegrado.appmetaforadevenda.TelaConsulta;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.afollestad.materialdialogs.MaterialDialog;

import java.util.ArrayList;
import java.util.List;

import br.grupointegrado.appmetaforadevenda.Dao.PessoaDao;
import br.grupointegrado.appmetaforadevenda.Listagem.AdapterCliente;

import br.grupointegrado.appmetaforadevenda.Pessoa.Pessoa;
import br.grupointegrado.appmetaforadevenda.TelaCadastro.CadastroPessoaActivity;
import br.grupointegrado.appmetaforadevenda.R;

public class ConsultaClienteActivity extends AppCompatActivity {

    private Toolbar atoolbar;
    private RecyclerView RecyviewPessoa;
    private List lista;
    private AdapterCliente adaptercliente;


    private Integer idpessoa;
    private String CNPJCPF;

    private PessoaDao clientedao;
    private Pessoa pessoa;


    private Boolean selecionandoPessoa = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consulta_cliente);

        atoolbar = (Toolbar)findViewById(R.id.tb_main);
        atoolbar.setTitle("");

        setSupportActionBar(atoolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        RecyviewPessoa =  (RecyclerView)findViewById(R.id.RecyviewPessoa);

        clientedao = new PessoaDao(this);

        if (getIntent().getExtras() != null)
            selecionandoPessoa = getIntent().getExtras().getBoolean("selecionar_pessoa", false);



        final StaggeredGridLayoutManager llm = new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL);
        llm.setGapStrategy(StaggeredGridLayoutManager.GAP_HANDLING_NONE);
        RecyviewPessoa.setLayoutManager(llm);


        adaptercliente = new AdapterCliente(this, new ArrayList<Pessoa>() ) {
            @Override
            protected void onItemClickListener(int adapterPosition, int layoutPosition) {
                // evento de click simples

                Pessoa pessoa = adaptercliente.getItems().get(adapterPosition);
                if (selecionandoPessoa) {
                    Intent data = new Intent();
                    data.putExtra("pessoa_id", pessoa.getIdpessoa());
                    setResult(RESULT_OK, data);
                    finish();
                }


            }

            @Override
            protected boolean onLongItemClickListener(int adapterPosition, int layoutPosition) {
                // evento e click longo

                Pessoa pessoa = adaptercliente.getItems().get(adapterPosition);

                idpessoa = pessoa.getIdpessoa();
                CNPJCPF = pessoa.getCnpjCpf();
                MaterialDialogCidade();

                return true;
            }


        };

        RecyviewPessoa.setAdapter(adaptercliente);

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
        adaptercliente.setItems(clientedao.list());
        adaptercliente.notifyDataSetChanged();

    }
    public void MaterialDialogCidade() {
        boolean wrapInScrollView = true;
        new MaterialDialog.Builder(this)
                .title("Cliente")
                .items(R.array.Array_de_alterar)
                .itemsCallback(new MaterialDialog.ListCallback() {
                    @Override
                    public void onSelection(MaterialDialog dialog, View view, int which, CharSequence text) {

                        if (text.equals("Editar")) {


                            dialog.dismiss();
                        } else if (text.equals("Excluir")) {
                            DeletarCliente();
                            dialog.dismiss();
                        }


                    }

                })
                .show();

    }
    public void EditarPessoa(Pessoa pessoa) {

        Intent i = new Intent(this.getBaseContext(), CadastroPessoaActivity.class);

        


        startActivity(i);

    }

    public void DeletarCliente() {
        try {
            clientedao.delete(idpessoa);
            Toast.makeText(this, "Pessoa Excluido", Toast.LENGTH_SHORT).show();
            Consultacliente();
        } catch (Exception e) {
            Toast.makeText(this, e.toString(), Toast.LENGTH_SHORT).show();
        }


    }

}
