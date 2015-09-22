package br.grupointegrado.appmetaforadevenda.Listagem;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import br.grupointegrado.appmetaforadevenda.Fragments.ItensFragment;
import br.grupointegrado.appmetaforadevenda.Fragments.PedidoFragment;
import br.grupointegrado.appmetaforadevenda.Fragments.PessoaFragment;
import br.grupointegrado.appmetaforadevenda.Fragments.TelefoneFragment;
import br.grupointegrado.appmetaforadevenda.R;

/**
 * Created by eli on 18/09/2015.
 */
public class AdapterTabsViewPedido extends FragmentPagerAdapter{

        private Context mContext;
        private String[] titles = {"PEDIDO", "ITENS"};
        private int[] icons = new int[]{R.drawable.ic_edit};
        private int heightIcon;


        public AdapterTabsViewPedido(FragmentManager fm, Context c) {
            super(fm);

            mContext = c;
            double scale = c.getResources().getDisplayMetrics().density;
            heightIcon = (int)( 24 * scale + 0.5f );
        }

        @Override
        public Fragment getItem(int position) {
            Fragment frag = null;

            if(position == 0){
                frag = new PedidoFragment();
            }
            else if(position == 1){
                frag = new ItensFragment();
            }
            else if(position == 2){

            }
            else if(position == 3){

            }
            else if(position == 4){

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


