package br.grupointegrado.appmetaforadevenda.Listagem;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import br.grupointegrado.appmetaforadevenda.TelaConsulta.ConsultaCidadeActivity;
import br.grupointegrado.appmetaforadevenda.TelaConsulta.ConsultaProdutoActivity;

/**
 * Created by eli on 16/09/2015.
 */
public class AdapterTabsView extends FragmentPagerAdapter {
    private Context mContext;
    private String[] titles = {"PEDIDO", "NOVO", "ITENS"};
    private int[] icons = new int[]{};
    private int heightIcon;

    public AdapterTabsView(FragmentManager fm, Context c) {
        super(fm);

        mContext = c;
    }

    @Override
    public Fragment getItem(int position) {
        Fragment frag = null;

        switch (position) {
            case 0:
                frag = new Fragment();
                break;
            case 1:
                break;
            case 2:
                break;

        }

        Bundle b = new Bundle();
        b.putInt("position", position);

        frag.setArguments(b);

        return frag;


    }

    @Override
    public int getCount() {
        return titles.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return (titles[position]);

    }
}
