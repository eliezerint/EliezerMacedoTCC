package br.grupointegrado.appmetaforadevenda.TelaCadastro;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.rengwuxian.materialedittext.MaterialEditText;

import br.grupointegrado.appmetaforadevenda.Dao.CidadeDao;
import br.grupointegrado.appmetaforadevenda.Dao.EstadoDao;
import br.grupointegrado.appmetaforadevenda.Pessoa.Cidade;
import br.grupointegrado.appmetaforadevenda.R;

import com.weiwangcn.betterspinner.library.material.MaterialBetterSpinner;

public class CadastroCidadeActivity extends ActionBarActivity {

    private String conteudopais;
    private String conteudoestado;


    Toolbar toolbar;
    private MaterialBetterSpinner SpinnerPais;
    private MaterialBetterSpinner SpinnerEstado;
    private MaterialEditText EditCidade;
    private MaterialEditText EditIbge;


    private CidadeDao cidadedao;
    private EstadoDao esatdodao;
    private Cidade cidadealt;
    private Cidade cidade;


    private Intent cidadeIntent;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_cidade);
        toolbar = (Toolbar) findViewById(R.id.tb_main);
        toolbar.setTitle("Cadastro de Cidade ");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        SpinnerEstado = (MaterialBetterSpinner) findViewById(R.id.SpinnerEstado);
        SpinnerPais = (MaterialBetterSpinner) findViewById(R.id.SpinnerPais);
        EditCidade = (MaterialEditText) findViewById(R.id.EditCidade);
        EditIbge = (MaterialEditText) findViewById(R.id.EditIbge);


        cidadedao = new CidadeDao(this);
        esatdodao = new EstadoDao(this);


        Addspinnerestado();
        Addspinnerpais();

        cidadealt = (Cidade) getIntent().getSerializableExtra("alterarcidade");

        if (cidadealt != null){
           setCidadealt(cidadealt);
        }


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_cadastro_cidade, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.home:
                finish();
                break;
            case R.id.Salvarcidade:
                //  esatdodao.saveEstado();

                save();
                break;
        }


        return true;

    }

    //Adicionado o pais em uma spinner com array
    public void Addspinnerpais() {

        String[] ITEMS = {"BR", "EUA", "PY"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, ITEMS);

        SpinnerPais.setAdapter(adapter);

        SpinnerPais.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                conteudopais = SpinnerPais.getText().toString();
            }

        });


    }


    //Adicionado o estado em uma spinner com array
    public void Addspinnerestado() {

        String[] ITEMS = {"PR", "SP", "SC", "DF"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, ITEMS);


        SpinnerEstado.setAdapter(adapter);


        SpinnerEstado.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                conteudoestado = SpinnerEstado.getText().toString();


            }


        });


    }

    //pegar dados da tela e passar para o modelo
    public Cidade getCidadealt() {
        return new Cidade(
                conteudopais,
                conteudoestado,
                EditCidade.getText().toString(),
                EditIbge.getText().toString());


    }
    public void setCidadealt(Cidade cidadealt){

        for (int x = 0; x < SpinnerPais.getLineCount(); x++) {
            String i = SpinnerPais.getText().toString();

            if (i.equals(cidadealt.getPais())) {
                SpinnerPais.setSelection(x);


                break;

            }
        }

        for (int x = 0; x < SpinnerEstado.getLineCount(); x++) {
            String i = SpinnerEstado.getText().toString();

            if (i.equals(cidadealt.getIdestado())) {
                SpinnerEstado.setSelection(x);


                break;

            }
        }


        EditCidade.setText(cidadealt.getDescricao());
        EditIbge.setText(cidadealt.getIbge());
    }


    public void save() {

        if (validacao(getCidadealt()) == 1) {
            if (cidadeIntent == null) {
                try {
                    cidadedao.saveCidade(getCidadealt());
                    Toast.makeText(this, " salvo com sucesso ", Toast.LENGTH_SHORT).show();

                } catch (Exception e) {
                    Toast.makeText(this, e.toString(), Toast.LENGTH_SHORT).show();

                }
            }else {

                //cidadeIntent.
            }

        } else Toast.makeText(this, " Preencher todos os campos", Toast.LENGTH_SHORT).show();


    }

    public Integer validacao(Cidade cidade) {
        Integer retorno = 0;

        if (!cidade.getDescricao().equals(" ") && !cidade.getDescricao().isEmpty()) {
            if (!cidade.getIbge().equals(" ") && !cidade.getIbge().isEmpty()) {
                if (conteudopais != null && conteudoestado != null) {
                    retorno = 1;
                }

            }

        }


        return retorno;
    }

}
