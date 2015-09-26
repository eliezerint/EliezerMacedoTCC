package br.grupointegrado.appmetaforadevenda.TelaCadastro;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import br.grupointegrado.appmetaforadevenda.Dao.PessoaDao;
import br.grupointegrado.appmetaforadevenda.Extras.SlidingTabLayout;
import br.grupointegrado.appmetaforadevenda.Fragments.PessoaFragment;
import br.grupointegrado.appmetaforadevenda.Listagem.AdapterTabsViewPessoa;
import br.grupointegrado.appmetaforadevenda.Pessoa.Pessoa;
import br.grupointegrado.appmetaforadevenda.R;

/**
 * Created by eli on 18/09/2015.
 */
public class CadastroPessoaActivity extends AppCompatActivity{
    private Toolbar atoolbar;
    private String titulo;

    private PessoaFragment fragpessoa;

    private CadastroPessoaActivity cad;
    private SlidingTabLayout mSlidingTabLayout;
    private ViewPager mViewPager;

    private Integer posicaoPageView;

    private PessoaDao pessoadao;
    private Pessoa pessoa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_pessoa);
        atoolbar = (Toolbar) findViewById(R.id.tb_main);
        atoolbar.setTitle("Cadastrar Cliente");
        setSupportActionBar(atoolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        pessoadao = new PessoaDao(this);

        pessoa = new Pessoa();




        mViewPager = (ViewPager) findViewById(R.id.vp_tabs);
        mViewPager.setAdapter(new AdapterTabsViewPessoa(getSupportFragmentManager(), this));

        mSlidingTabLayout = (SlidingTabLayout) findViewById(R.id.stl_tabs);
        //mSlidingTabLayout.setDistributeEvenly(true);
        mSlidingTabLayout.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
        mSlidingTabLayout.setSelectedIndicatorColors(getResources().getColor(R.color.colorWhite));
        mSlidingTabLayout.setCustomTabView(R.layout.tab_view, R.id.tv_tab);
        mSlidingTabLayout.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {


            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
        mSlidingTabLayout.setViewPager(mViewPager);
        //mSlidingTabLayout.setHorizontalFadingEdgeEnabled(true);
        //mSlidingTabLayout.setHorizontalScrollBarEnabled(true);




    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_cadastro_pessoa, menu);



        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.home:
                finish();
                break;
            case R.id.Salvarpessoa:

             fragpessoa.savePessoatela();

                break;


        }

        return true;
    }















}


