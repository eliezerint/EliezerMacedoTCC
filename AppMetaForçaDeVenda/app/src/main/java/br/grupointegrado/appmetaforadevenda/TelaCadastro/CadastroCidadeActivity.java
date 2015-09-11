package br.grupointegrado.appmetaforadevenda.TelaCadastro;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.rengwuxian.materialedittext.MaterialEditText;

import br.grupointegrado.appmetaforadevenda.Dao.AppDao;
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
    private MaterialBetterSpinner  SpinnerEstado;
    private MaterialEditText EditCidade;
    private MaterialEditText EditIbge;


    private CidadeDao cidadedao;
    private EstadoDao esatdodao;
    private Cidade cidade;


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
    public void Addspinnerpais (){

        String[] ITEMS = {"BR"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, ITEMS);

        SpinnerPais.setAdapter(adapter);

        SpinnerPais.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                conteudopais = SpinnerPais.getText().toString();
                Toast.makeText(getApplication(), conteudoestado,Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });




    }


    //Adicionado o estado em uma spinner com array
    public void Addspinnerestado (){

        String[] ITEMS = {"PR"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, ITEMS);


        SpinnerEstado.setAdapter(adapter);

        SpinnerEstado.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                conteudoestado =  SpinnerEstado.getHelperText();
                Toast.makeText(getApplication(), conteudoestado,Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });



    }
    //pegar dados da tela e passar para o modelo
    public Cidade getCidade(){
        return  new  Cidade (
               "BR",
               "PR",
                EditCidade.getText().toString(),
                EditIbge.getText().toString());


    }

    public void save(){


        try {
                cidadedao.saveCidade(getCidade());
                Toast.makeText(this, " salvo com sucesso ", Toast.LENGTH_SHORT).show();

        } catch (Exception e) {
                Toast.makeText(this, e.toString(), Toast.LENGTH_SHORT).show();

        }


    }

}
