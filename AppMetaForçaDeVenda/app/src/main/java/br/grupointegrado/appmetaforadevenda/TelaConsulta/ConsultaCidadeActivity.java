package br.grupointegrado.appmetaforadevenda.TelaConsulta;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.afollestad.materialdialogs.MaterialDialog;
import com.rengwuxian.materialedittext.MaterialEditText;
import com.weiwangcn.betterspinner.library.material.MaterialBetterSpinner;

import java.util.ArrayList;
import java.util.List;

import br.grupointegrado.appmetaforadevenda.Dao.CidadeDao;
import br.grupointegrado.appmetaforadevenda.Listagem.AdapterCidade;
import br.grupointegrado.appmetaforadevenda.Pessoa.Cidade;
import br.grupointegrado.appmetaforadevenda.Pessoa.Pessoa;
import br.grupointegrado.appmetaforadevenda.R;
import br.grupointegrado.appmetaforadevenda.TelaCadastro.CadastroCidadeActivity;

public class ConsultaCidadeActivity extends AppCompatActivity {

    private Toolbar atoolbar;
    private RecyclerView RecyviewCidade;
    private List listacidade;
    public MaterialEditText MaterialEditCidade;
    private MaterialBetterSpinner MaterialSpinnerPais;
    private MaterialBetterSpinner MaterialSpinnerEstado;


    private String nomecidade;
    private String conteudopais;
    private String conteudoestado;
    private Integer idCidade;
    private String IBGE;


    private Pessoa pessoa;
    private Cidade cidade;
    private CidadeDao cidadedao;
    private AdapterCidade adaptercidade;
    private boolean selecionandoCidade = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consulta_cidade);
        atoolbar = (Toolbar) (findViewById(R.id.tb_main));
        atoolbar.setTitle("");

        setSupportActionBar(atoolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        MaterialSpinnerPais = (MaterialBetterSpinner) findViewById(R.id.SpinnerPais);
        MaterialSpinnerEstado = (MaterialBetterSpinner) findViewById(R.id.SpinnerEstado);

        RecyviewCidade = (RecyclerView) findViewById(R.id.RecyviewCidade);


        cidadedao = new CidadeDao(this);

        MaterialEditCidade = (MaterialEditText) findViewById(R.id.MaterialEditCidadeDelete);




        final StaggeredGridLayoutManager llm = new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL);
        llm.setGapStrategy(StaggeredGridLayoutManager.GAP_HANDLING_NONE);
        RecyviewCidade.setLayoutManager(llm);

        adaptercidade = new AdapterCidade(this, new ArrayList<Cidade>()) {
            @Override
            protected void onItemClickListener(int adapterPosition, int layoutPosition) {
                // evento de click simples
                //  MaterialDialogCidade();


                Cidade cidade = adaptercidade.getItems().get(adapterPosition);
                if (selecionandoCidade) {
                    Intent data = new Intent();
                    data.putExtra("cidade_id", cidade.getId());
                    setResult(RESULT_OK, data);
                    finish();
                }else {
                    idCidade = cidade.getIdcidade();
                    nomecidade = cidade.getDescricao();

                }
                selecionandoCidade = getIntent().getExtras().getBoolean("selecionar_cidade", false);
            }

            @Override
            protected boolean onLongItemClickListener(int adapterPosition, int layoutPosition) {
                // evento e click longo
                 Cidade cidade = adaptercidade.getItems().get(adapterPosition);
                idCidade = cidade.getIdcidade();
                nomecidade = cidade.getDescricao();
                IBGE = cidade.getIbge();


                MaterialDialogCidade();
                return true;
            }
        };



        RecyviewCidade.setAdapter(adaptercidade);


        Consultacidade();

        Addspinnerestado();
        Addspinnerpais();


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_consulta_cidade, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.home:
                finish();
                break;
            case R.id.ConsultarCidade:
                if ((conteudopais == null) && (conteudoestado == null)) {
                    Consultacidade();
                } else Consultacidade(conteudopais, conteudoestado);
                break;
        }

        return true;
    }

    private void Consultacidade(String conteudopais, String conteudoestado) {

        adaptercidade.setItems(cidadedao.list(conteudopais, conteudoestado));
        adaptercidade.notifyDataSetChanged();

    }


    public void CadastrarCidade(View view) {
        Intent i = new Intent(this.getApplication(), CadastroCidadeActivity.class);


        startActivity(i);

    }

    public void Consultacidade() {

        adaptercidade.setItems(cidadedao.list());
        adaptercidade.notifyDataSetChanged();

    }


    public void MaterialDialogCidade() {
        boolean wrapInScrollView = true;
        new MaterialDialog.Builder(this)
                .title("Cidade")
                .items(R.array.Array_de_alterar)
                .itemsCallback(new MaterialDialog.ListCallback() {
                    @Override
                    public void onSelection(MaterialDialog dialog, View view, int which, CharSequence text) {

                        if (text.equals("Editar")) {

                            EditarCidade(getCidade());
                            dialog.dismiss();
                        } else if (text.equals("Excluir")) {
                            DeletarCidade();
                            dialog.dismiss();
                        }


                    }

                })
                .show();
    }


    public void EditarCidade(Cidade cidade) {
        Intent i = new Intent(this.getBaseContext(), CadastroCidadeActivity.class);
        i.putExtra("alterarcidade", cidade);


        startActivity(i);

    }

    public void DeletarCidade() {
        try {
            cidadedao.delete(idCidade);
            Toast.makeText(this, "Cidade Excluida", Toast.LENGTH_SHORT).show();
            Consultacidade();
        } catch (Exception e) {
            Toast.makeText(this, e.toString(), Toast.LENGTH_SHORT).show();
        }


    }


    public void Addspinnerpais() {

        String[] ITEMS = {"BR", "EUA", "PY"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, ITEMS);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        MaterialSpinnerPais.setAdapter(adapter);

        MaterialSpinnerPais.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                conteudopais = MaterialSpinnerPais.getText().toString();


            }


        });


    }


    public void Addspinnerestado() {

        String[] ITEMS = {"PR", "SP", "SC", "DF"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, ITEMS);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        MaterialSpinnerEstado.setAdapter(adapter);

        MaterialSpinnerEstado.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                conteudoestado = MaterialSpinnerEstado.getText().toString();

            }


        });


    }

    //pegar dados da tela e passar para o modelo
    public Cidade getCidade() {
        return new Cidade(
                conteudopais,
                conteudoestado,
                nomecidade,
                IBGE);


    }


}
