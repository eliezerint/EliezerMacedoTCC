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

import br.grupointegrado.appmetaforadevenda.Dao.CondicaoPgtoDao;
import br.grupointegrado.appmetaforadevenda.Listagem.AdapterCondPgto;
import br.grupointegrado.appmetaforadevenda.Listagem.AdapterFilial;
import br.grupointegrado.appmetaforadevenda.Pedido.CondicaoPagamento;
import br.grupointegrado.appmetaforadevenda.R;

public class Consulta_CondicaPgtoActivity extends AppCompatActivity {

    private Toolbar atoolbar;
    private RecyclerView RecyviewCondPgto;

    private CondicaoPgtoDao condpgtodao;

    private AdapterCondPgto adaptercondpgto;

    private Integer idcondpgto;
    private String nomecondpgto;
    private Boolean selecionandoCondPgto = false;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consulta__condica_pgto);

        atoolbar = (Toolbar) (findViewById(R.id.tb_main));
        atoolbar.setTitle("Consulta Condição de Pagamento");

        setSupportActionBar(atoolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        condpgtodao = new CondicaoPgtoDao(this);


        RecyviewCondPgto = (RecyclerView)findViewById(R.id.RecyviewCondPgto);



        /*condpgtodao.saveCondPgto("30/60",2.0,30);
        condpgtodao.saveCondPgto("15/30/45",3.0,15);
        condpgtodao.saveCondPgto("30/60/90",3.0,30);
        condpgtodao.saveCondPgto("A vista",1.0,0);
        condpgtodao.saveCondPgto("15 dias",1.0,15);
        condpgtodao.saveCondPgto("A prazo",1.0,15);*/


        if (getIntent().getExtras() != null)
            selecionandoCondPgto = getIntent().getExtras().getBoolean("selecionar_condpgto", false);


        final StaggeredGridLayoutManager llm = new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL);
        llm.setGapStrategy(StaggeredGridLayoutManager.GAP_HANDLING_NONE);
        RecyviewCondPgto.setLayoutManager(llm);

        adaptercondpgto = new AdapterCondPgto(this, new ArrayList<CondicaoPagamento>()) {
            @Override
            protected void onItemClickListener(int adapterPosition, int layoutPosition) {
                // evento de click simples


                CondicaoPagamento condpgto = adaptercondpgto.getItems().get(adapterPosition);
                if (selecionandoCondPgto) {
                    Intent data = new Intent();
                    data.putExtra("condpgto_id", condpgto.getIdcodicaopagamento());
                    setResult(RESULT_OK, data);
                    finish();
                } else {
                    idcondpgto = condpgto.getIdcodicaopagamento();
                    nomecondpgto = condpgto.getDescricao();

                }

            }

            @Override
            protected boolean onLongItemClickListener(int adapterPosition, int layoutPosition) {
                // evento e click longo
                CondicaoPagamento  condpgto = adaptercondpgto.getItems().get(adapterPosition);
                idcondpgto = condpgto.getIdcodicaopagamento();
                nomecondpgto = condpgto.getDescricao();




                return true;
            }
        };


        RecyviewCondPgto.setAdapter(adaptercondpgto);

        RecyclerViewCondPgto();



    }

    @Override
    protected void onResume() {
        super.onResume();

        RecyclerViewCondPgto();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_consulta__condica_pgto, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void RecyclerViewCondPgto(){
        adaptercondpgto.setItems(condpgtodao.list());
        adaptercondpgto.notifyDataSetChanged();
    };
}
