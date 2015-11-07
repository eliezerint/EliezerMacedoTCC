package br.grupointegrado.appmetaforadevenda.TelaConsulta;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;

import br.grupointegrado.appmetaforadevenda.Dao.VendedorDao;
import br.grupointegrado.appmetaforadevenda.Listagem.AdapterVendedor;
import br.grupointegrado.appmetaforadevenda.R;
import br.grupointegrado.appmetaforadevenda.Vendedor.Vendedor;

public class Consulta_VendedorActivity extends AppCompatActivity {

    private Toolbar atoolbar;
    private RecyclerView recyclerviewvendedor;
    private AdapterVendedor adaptervendedor;


    private VendedorDao vendedordao;

    private String nome;
    private Integer idvendedor;

    private Boolean selecionandoVendedor = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consulta__vendedor);


        atoolbar = (Toolbar)findViewById(R.id.tb_main);
        atoolbar.setTitle("Consulta de Vendedor");

        setSupportActionBar(atoolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        vendedordao = new VendedorDao(this);

        /*insercao de dados de teste

        */


        /* vendedordao.saveVendedor("Amanda", 10.00 , 10.00);
         vendedordao.saveVendedor("Tereza", 40.00 , 40.00);
         vendedordao.saveVendedor("Onofre", 50.00 , 50.00);*/



        recyclerviewvendedor = (RecyclerView)findViewById(R.id.RecyviewVendedor);

        if (getIntent().getExtras() != null)
            selecionandoVendedor = getIntent().getExtras().getBoolean("selecionar_vendedor", false);




        final StaggeredGridLayoutManager llm = new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL);
        llm.setGapStrategy(StaggeredGridLayoutManager.GAP_HANDLING_NONE);
        recyclerviewvendedor.setLayoutManager(llm);


        adaptervendedor =  new AdapterVendedor(this, new  ArrayList<Vendedor>()){

                @Override
                protected void onItemClickListener(int adapterPosition, int layoutPosition) {
                    // evento de click simples

                    Vendedor vendedor = adaptervendedor.getItems().get(adapterPosition);
                    if (selecionandoVendedor) {
                        Intent data = new Intent();
                        data.putExtra("vendedor_id", vendedor.getIdvendedor());
                        setResult(RESULT_OK, data);
                        finish();
                    }


                }

                @Override
                protected boolean onLongItemClickListener(int adapterPosition, int layoutPosition) {
                    // evento e click longo

                    Vendedor vendedor = adaptervendedor.getItems().get(adapterPosition);

                    idvendedor = vendedor.getIdvendedor();
                    nome = vendedor.getNome();


                    return true;
                }
        };

        recyclerviewvendedor.setAdapter(adaptervendedor);

        consultaVendedor();





    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_consulta__vendedor, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){
            case R.id.home:
                finish();
                break;
        }

        return true;
    }

    @Override
    protected void onResume() {
        super.onResume();

        consultaVendedor();
    }

    public void consultaVendedor(){
        adaptervendedor.setItems(vendedordao.list());
        adaptervendedor.notifyDataSetChanged();

    }
}
