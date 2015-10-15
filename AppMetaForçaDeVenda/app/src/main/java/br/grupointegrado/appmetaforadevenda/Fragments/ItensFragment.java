package br.grupointegrado.appmetaforadevenda.Fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import net.i2p.android.ext.floatingactionbutton.FloatingActionButton;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import br.grupointegrado.appmetaforadevenda.Listagem.AdapterItensPedido;
import br.grupointegrado.appmetaforadevenda.Pedido.ItensPedido;
import br.grupointegrado.appmetaforadevenda.Pessoa.Pessoa;
import br.grupointegrado.appmetaforadevenda.R;
import br.grupointegrado.appmetaforadevenda.TelaCadastro.CadastroItensPedidoActivity;
import br.grupointegrado.appmetaforadevenda.TelaConsulta.ConsultaProdutoActivity;
import br.grupointegrado.appmetaforadevenda.Util.FragmentTab;

/**
 * Created by eli on 18/09/2015.
 */
public class ItensFragment extends Fragment implements FragmentTab {


    private FloatingActionButton floatingActionbutton;
    private AdapterItensPedido adapteritenspedido;
    private List<ItensPedido> listaitens;

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

        listaitens = new ArrayList<>();

        floatingActionbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addItensPedido();
            }
        });



        final StaggeredGridLayoutManager llm = new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL);
        llm.setGapStrategy(StaggeredGridLayoutManager.GAP_HANDLING_NONE);
        recycleritenspedido.setLayoutManager(llm);

        adapteritenspedido = new AdapterItensPedido(this.getActivity(), new ArrayList<ItensPedido>()) {
            @Override
            protected void onItemClickListener(int adapterPosition, int layoutPosition) {




            }

            @Override
            protected boolean onLongItemClickListener(int adapterPosition, int layoutPosition) {


                return true;
            }
        };

        recycleritenspedido.setAdapter(adapteritenspedido);


        ConsultaItensPedido();



    }


    public void ConsultaItensPedido(){
        adapteritenspedido.setItems(listaitens);
        adapteritenspedido.notifyDataSetChanged();

    }
    public void ConsultaItensPedido(List<ItensPedido> listaitens){
        adapteritenspedido.setItems(listaitens);
        adapteritenspedido.notifyDataSetChanged();

    }

    private static final int REQUEST_ADD_ITENSPEDIDO = 1001;

    private void addItensPedido() {
        Intent intent = new Intent(getActivity(), ConsultaProdutoActivity.class);
        intent.putExtra("selecionando_produto", true);
        startActivityForResult(intent, REQUEST_ADD_ITENSPEDIDO);
    }



    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (REQUEST_ADD_ITENSPEDIDO == requestCode && resultCode == getActivity().RESULT_OK) {
            ItensPedido item = (ItensPedido)data.getSerializableExtra("itens_object");

            Toast.makeText(this.getContext(),item.getIdProduto()+ "", Toast.LENGTH_SHORT).show();
            listaitens.add(item);
            ConsultaItensPedido(listaitens);

        }


    }

    @Override
    public void onDetach() {
        super.onDetach();

    }

    @Override
    public void atualizar() {
        System.out.println("Fragment Itens Pedido");
    }




}
