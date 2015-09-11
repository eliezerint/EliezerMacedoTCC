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

import java.util.List;

import br.grupointegrado.appmetaforadevenda.Dao.CidadeDao;
import br.grupointegrado.appmetaforadevenda.Listagem.AdapterCidade;
import br.grupointegrado.appmetaforadevenda.Pessoa.Cidade;
import br.grupointegrado.appmetaforadevenda.R;
import br.grupointegrado.appmetaforadevenda.TelaCadastro.CadastroCidadeActivity;

public class ConsultaCidadeActivity extends AppCompatActivity {

    private Toolbar atoolbar;
    private RecyclerView RecyviewCidade;
    private List listacidade;
    private MaterialEditText MaterialEditCidade;
    private MaterialBetterSpinner MaterialSpinnerPais;
    private MaterialBetterSpinner MaterialSpinnerEstado;


    private String nomecidade;
    private Integer idCidade;

    private Cidade cidade;
    private CidadeDao cidadedao;
    private AdapterCidade adaptercidade;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consulta_cidade);
        atoolbar = (Toolbar) (findViewById(R.id.tb_main));


        setSupportActionBar(atoolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        MaterialSpinnerPais = (MaterialBetterSpinner) findViewById(R.id.SpinnerPais);
        MaterialSpinnerEstado = (MaterialBetterSpinner) findViewById(R.id.SpinnerEstado);

        RecyviewCidade = (RecyclerView) findViewById(R.id.RecyviewCidade);


        cidadedao = new CidadeDao(this);

        MaterialEditCidade = (MaterialEditText) findViewById(R.id.MaterialEditCidadeDelete);




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
                Consultacidade();
                break;
        }

        return true;
    }


    public void CadastrarCidade(View view) {
        Intent i = new Intent(this.getApplication(), CadastroCidadeActivity.class);


        startActivity(i);

    }

    public void Consultacidade() {
        final StaggeredGridLayoutManager llm = new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL);
        llm.setGapStrategy(StaggeredGridLayoutManager.GAP_HANDLING_NONE);
        RecyviewCidade.setLayoutManager(llm);

        adaptercidade = new AdapterCidade(this, cidadedao.list()) {
            @Override
            protected void onItemClickListener(int adapterPosition, int layoutPosition) {
                // evento de click simples
                MaterialDialogCidade();
                idCidade = adaptercidade.getItems().get(adapterPosition).getIdcidade();
                nomecidade = adaptercidade.getItems().get(adapterPosition).getDescricao();
            }
            @Override
            protected boolean onLongItemClickListener(int adapterPosition, int layoutPosition) {
                // evento e click longo

                idCidade = adaptercidade.getItems().get(adapterPosition).getIdcidade();
                nomecidade = adaptercidade.getItems().get(adapterPosition).getDescricao();
                MaterialDialogCidade();
                return true;
            }
        };

        RecyviewCidade.setAdapter(adaptercidade);

    }

    public void MaterialDialogCidade() {
        boolean wrapInScrollView = true;
        MaterialDialog app = new MaterialDialog.Builder(this)
                .title("Cidade")
                .customView(R.layout.activity_opcao, wrapInScrollView)
                .negativeText("Sair")
                .autoDismiss(true)

                .show();
        ;
    }

    public void EditarCidade(View view){
       MaterialEditCidade.setText(nomecidade);

        boolean wrapInScrollView = true;
        MaterialDialog app = new MaterialDialog.Builder(this)
                .title("Cidade")
                .customView(R.layout.activity_alt_cidade, wrapInScrollView)
                .negativeText("Sair")
                .autoDismiss(true)


                .show();
        ;


    }
    public void DeletarCidade(View view){

    }


    public void Addspinnerpais() {

        String[] ITEMS = {"BR"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, ITEMS);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        MaterialSpinnerPais.setAdapter(adapter);

        MaterialSpinnerPais.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                // conteudopais = String.valueOf(SpinnerPais.getListSelection());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


    }


    public void Addspinnerestado() {

        String[] ITEMS = {"PR"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, ITEMS);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        MaterialSpinnerEstado.setAdapter(adapter);

        MaterialSpinnerEstado.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // conteudoestado = String.valueOf(SpinnerEstado.getListSelection());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


    }


}
