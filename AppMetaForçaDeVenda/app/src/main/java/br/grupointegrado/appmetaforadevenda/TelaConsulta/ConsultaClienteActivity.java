package br.grupointegrado.appmetaforadevenda.TelaConsulta;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
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
import br.grupointegrado.appmetaforadevenda.Fragments.PessoaFragment;
import br.grupointegrado.appmetaforadevenda.Listagem.AdapterCliente;

import br.grupointegrado.appmetaforadevenda.Pessoa.Pessoa;
import br.grupointegrado.appmetaforadevenda.Pessoa.Telefone;
import br.grupointegrado.appmetaforadevenda.TelaCadastro.CadastroPessoaActivity;
import br.grupointegrado.appmetaforadevenda.R;

public class ConsultaClienteActivity extends AppCompatActivity {

    private Toolbar atoolbar;
    private RecyclerView RecyviewPessoa;
    private List lista;
    private AdapterCliente adaptercliente;
    private List<Telefone> lista_telefone;

    private String conteudoSearch;
    private Integer idpessoa;
    private String CNPJCPF;

    private PessoaDao clientedao;
    private Pessoa pessoa;


    private Boolean selecionandoPessoa = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consulta_cliente);

        atoolbar = (Toolbar) findViewById(R.id.tb_main);
        atoolbar.setTitle("Consulta de Cliente");

        setSupportActionBar(atoolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        RecyviewPessoa = (RecyclerView) findViewById(R.id.RecyviewPessoa);

        clientedao = new PessoaDao(this);

        if (getIntent().getExtras() != null)
            selecionandoPessoa = getIntent().getExtras().getBoolean("selecionar_pessoa", false);


        final StaggeredGridLayoutManager llm = new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL);
        llm.setGapStrategy(StaggeredGridLayoutManager.GAP_HANDLING_NONE);
        RecyviewPessoa.setLayoutManager(llm);


        adaptercliente = new AdapterCliente(this, new ArrayList<Pessoa>()) {
            @Override
            protected void onItemClickListener(int adapterPosition, int layoutPosition) {
                // evento de click simples

                Pessoa pessoa = adaptercliente.getItems().get(adapterPosition);
                if (selecionandoPessoa) {
                    Intent data = new Intent();
                    data.putExtra("pessoa_id", pessoa.getIdpessoa());
                    setResult(RESULT_OK, data);
                    finish();
                }else {

                    EditarPessoa(getPessoa(pessoa));


                }


            }

            @Override
            protected boolean onLongItemClickListener(int adapterPosition, int layoutPosition) {
                // evento e click longo

                Pessoa pessoa = adaptercliente.getItems().get(adapterPosition);

                idpessoa = pessoa.getIdpessoa();
                CNPJCPF = pessoa.getCnpjCpf();

                MaterialDialogCidade(pessoa);

                return true;
            }


        };

        RecyviewPessoa.setAdapter(adaptercliente);

        Consultacliente();

        getDadosSearch(this.getIntent());

    }

    @Override
    protected void onNewIntent(Intent intent) {
        setIntent(intent);
        getDadosSearch(intent);

    }

    public void getDadosSearch(Intent intent) {

        String conteudoQuery = intent.getStringExtra(SearchManager.QUERY);
        conteudoSearch = conteudoQuery;

        if (conteudoSearch != null) {
            Toast.makeText(this, conteudoQuery, Toast.LENGTH_SHORT).show();
            adaptercliente.setItems(clientedao.list(conteudoSearch));
            adaptercliente.notifyDataSetChanged();
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_consulta_cliente, menu);

        SearchManager searchmanager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);

        SearchView search = (SearchView) menu.findItem(R.id.ConsultaCliente).getActionView();

        search.setSearchableInfo(searchmanager.getSearchableInfo(getComponentName()));

        search.setQueryHint(getResources().getString(R.string.search_hint_cliente));


        return true;
    }

    public void CadastrarCliente(View view) {

        Intent i = new Intent(this.getApplication(), CadastroPessoaActivity.class);


        startActivity(i);


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){
            case R.id.home:
                finish();
                break;
            case android.R.id.home:
                finish();
                break;
            case R.id.ConsultaCliente:
                Consultacliente();
                break;

        }

        return true;
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (conteudoSearch != null){
            adaptercliente.setItems(clientedao.list(conteudoSearch));
            adaptercliente.notifyDataSetChanged();
        }else{
            adaptercliente.setItems(clientedao.list());
            adaptercliente.notifyDataSetChanged();
        }

    }


    public void Consultacliente() {
        adaptercliente.setItems(clientedao.list());
        adaptercliente.notifyDataSetChanged();

    }

    public void MaterialDialogCidade(final Pessoa pessoa) {
        boolean wrapInScrollView = true;
        new MaterialDialog.Builder(this)
                .title("Cliente")
                .items(R.array.Array_de_alterar)
                .itemsCallback(new MaterialDialog.ListCallback() {
                    @Override
                    public void onSelection(MaterialDialog dialog, View view, int which, CharSequence text) {

                        if (text.equals("Editar")) {

                            EditarPessoa(getPessoa(pessoa));
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
        i.putExtra("alterarpessoa", pessoa);

        startActivity(i);

    }

    public Pessoa getPessoa(Pessoa pessoa){
        return new Pessoa(
                pessoa.getIdpessoa(),
                pessoa.getIdCidade(),
                pessoa.getCnpjCpf(),
                pessoa.getRazaoSocialNome(),
                pessoa.getFantasiaApelido(),
                pessoa.getInscriEstadualRG(),
                pessoa.getEndereco(),
                pessoa.getNumero(),
                pessoa.getBairro(),
                pessoa.getComplemento(),
                pessoa.getCidade(),
                pessoa.getDataNascimento(),
                pessoa.getEmail(),
                pessoa.getDataUltimacompra(),
                pessoa.getValorUltimacompra(),
                pessoa.getDataCadastro());
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
