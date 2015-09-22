package br.grupointegrado.appmetaforadevenda;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.EditText;

import br.grupointegrado.appmetaforadevenda.Extras.SlidingTabLayout;
import br.grupointegrado.appmetaforadevenda.Listagem.AdapterTabsViewPedido;


/**
 * Created by eli on 18/09/2015.
 */
public class PedidoTabs extends AppCompatActivity {
    private EditText idvendedorpedido;
    private Toolbar atoolbar;

    private SlidingTabLayout mSlidingTabLayout;
    private ViewPager mViewPager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pedidotabs);

        atoolbar = (Toolbar) findViewById(R.id.tb_main);
        atoolbar.setTitle(" Pedido ");
        atoolbar.setLogo(R.drawable.ic_description_black_18dp);
        atoolbar.setNavigationIcon(R.drawable.ic_menu);
        setSupportActionBar(atoolbar);




        //pageView

        mViewPager = (ViewPager) findViewById(R.id.vp_tabs);
        mViewPager.setAdapter(new AdapterTabsViewPedido(getSupportFragmentManager(), this));

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
                // navigationDrawerLeft.setSelection(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
        mSlidingTabLayout.setViewPager(mViewPager);
        //mSlidingTabLayout.setHorizontalFadingEdgeEnabled(true);
        //mSlidingTabLayout.setHorizontalScrollBarEnabled(true);





    }

}
