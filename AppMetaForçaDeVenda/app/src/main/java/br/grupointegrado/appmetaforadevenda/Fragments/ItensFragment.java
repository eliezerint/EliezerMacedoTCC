package br.grupointegrado.appmetaforadevenda.Fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import net.i2p.android.ext.floatingactionbutton.FloatingActionButton;

import br.grupointegrado.appmetaforadevenda.R;
import br.grupointegrado.appmetaforadevenda.TelaCadastro.CadastroItensPedidoActivity;

/**
 * Created by eli on 18/09/2015.
 */
public class ItensFragment extends Fragment {

    private FloatingActionButton floatingActionbutton;

    private RecyclerView recycleritenspedido;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_itens_pedidos, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        recycleritenspedido = (RecyclerView)view.findViewById(R.id.RecyviewItensPedido);
        floatingActionbutton = (FloatingActionButton)view.findViewById(R.id.button_itens_pedido);


        floatingActionbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i =  new Intent(getActivity(), CadastroItensPedidoActivity.class);

                startActivity(i);
            }
        });

        final StaggeredGridLayoutManager llm = new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL);
        llm.setGapStrategy(StaggeredGridLayoutManager.GAP_HANDLING_NONE);
        recycleritenspedido.setLayoutManager(llm);

      /*  adaptertelefone = new AdapterTelefone(this.getActivity(), new ArrayList<Telefone>()) {
            @Override
            protected void onItemClickListener(int adapterPosition, int layoutPosition) {


            }

            @Override
            protected boolean onLongItemClickListener(int adapterPosition, int layoutPosition) {


                return true;
            }
        };
*/




    }




    @Override
    public void onDetach() {
        super.onDetach();

    }

}
