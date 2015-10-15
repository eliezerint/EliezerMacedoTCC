package br.grupointegrado.appmetaforadevenda.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.rengwuxian.materialedittext.MaterialEditText;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import br.grupointegrado.appmetaforadevenda.Dao.CondicaoPgtoDao;
import br.grupointegrado.appmetaforadevenda.Dao.FilialDao;
import br.grupointegrado.appmetaforadevenda.Dao.PessoaDao;
import br.grupointegrado.appmetaforadevenda.Dao.VendedorDao;
import br.grupointegrado.appmetaforadevenda.MainActivity;
import br.grupointegrado.appmetaforadevenda.R;
import br.grupointegrado.appmetaforadevenda.TelaConsulta.ConsultaClienteActivity;
import br.grupointegrado.appmetaforadevenda.TelaConsulta.Consulta_CondicaPgtoActivity;
import br.grupointegrado.appmetaforadevenda.TelaConsulta.Consulta_FilialActivity;
import br.grupointegrado.appmetaforadevenda.TelaConsulta.Consulta_VendedorActivity;
import br.grupointegrado.appmetaforadevenda.Util.FragmentTab;


/**
 * Created by eli on 18/09/2015.
 */
public class PedidoFragment extends Fragment implements FragmentTab {

    private MaterialEditText edit_nome_cliente;
    private MaterialEditText edit_cond_pgto;
    private MaterialEditText edit_vendedor;
    private MaterialEditText edit_filial;
    private MaterialEditText edit_valor_Total;
    private MaterialEditText edit_data_pedido;


    private Integer idpessoa;
    private Integer idfilial;
    private Integer idcondpgto;
    private Integer idvendedor;
    private Integer idcendedortelainicial;


    private PessoaDao pessoadao;
    private FilialDao filialdao;
    private CondicaoPgtoDao condpgtodao;
    private VendedorDao vendedordao;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_cadastro_pedido, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        edit_nome_cliente = (MaterialEditText) view.findViewById(R.id.edit_nome_pessoa);
        edit_cond_pgto = (MaterialEditText) view.findViewById(R.id.edit_condicao_pag);
        edit_vendedor = (MaterialEditText) view.findViewById(R.id.edit_vendedor);
        edit_filial = (MaterialEditText) view.findViewById(R.id.edit_filial);
        edit_valor_Total = (MaterialEditText) view.findViewById(R.id.edit_valor_total);
        edit_data_pedido = (MaterialEditText) view.findViewById(R.id.edit_data_pedido);


        pessoadao = new PessoaDao(getActivity());
        filialdao = new FilialDao(getActivity());
        condpgtodao = new CondicaoPgtoDao(getActivity());
        vendedordao = new VendedorDao(getActivity());

        idcendedortelainicial = MainActivity.idvendedortelainicial;

        edit_vendedor.setText(vendedordao.nomeVendedor(idcendedortelainicial.toString()));

        edit_valor_Total.setText(" 0.00");

        edit_data_pedido.setText(getDateTime());

        edit_nome_cliente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selecionarPessoa();

            }
        });
        edit_cond_pgto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selecionarCondPgto();
            }
        });
        edit_filial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selecionarFilial();

            }
        });
        edit_vendedor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                selecionarVendedor();
            }
        });
    }

    private static final int REQUEST_CONSULTA_PESSOA = 1001;
    private static final int REQUEST_CONSULTA_CONDPGTO = 1002;
    private static final int REQUEST_CONSULTA_FILIAL = 1003;
    private static final int REQUEST_CONSULTA_VENDEDOR = 1004;

    private void selecionarPessoa() {
        Intent intent = new Intent(getActivity(), ConsultaClienteActivity.class);
        intent.putExtra("selecionar_pessoa", true);
        startActivityForResult(intent, REQUEST_CONSULTA_PESSOA);
    }


    private void selecionarCondPgto() {
        Intent intent = new Intent(getActivity(), Consulta_CondicaPgtoActivity.class);
        intent.putExtra("selecionar_condpgto", true);
        startActivityForResult(intent, REQUEST_CONSULTA_CONDPGTO);
    }

    private void selecionarVendedor() {
        Intent intent = new Intent(getActivity(), Consulta_VendedorActivity.class);
        intent.putExtra("selecionar_vendedor", true);
        startActivityForResult(intent, REQUEST_CONSULTA_VENDEDOR);
    }

    private void selecionarFilial() {
        Intent intent = new Intent(getActivity(), Consulta_FilialActivity.class);
        intent.putExtra("selecionar_filial", true);
        startActivityForResult(intent, REQUEST_CONSULTA_FILIAL);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (REQUEST_CONSULTA_PESSOA == requestCode && resultCode == getActivity().RESULT_OK) {
             idpessoa = data.getIntExtra("pessoa_id", 0);
            String nomePessoa;
            nomePessoa = pessoadao.CosultaClienteNome(Integer.toString(idpessoa));
            edit_nome_cliente.setText(nomePessoa);


        }
        else if (REQUEST_CONSULTA_CONDPGTO == requestCode && resultCode == getActivity().RESULT_OK) {
            idcondpgto = data.getIntExtra("condpgto_id", 0);
            String nomeCondPgto;
            nomeCondPgto = condpgtodao.nomeCondPgto(Integer.toString(idcondpgto));
            edit_cond_pgto.setText(nomeCondPgto);


        }
       else if (REQUEST_CONSULTA_FILIAL == requestCode && resultCode == getActivity().RESULT_OK) {
            idfilial = data.getIntExtra("filial_id", 0);
           String nomeFilial;
            nomeFilial = filialdao.nomeFilial(Integer.toString(idfilial));
            edit_filial.setText(nomeFilial);


        }else if (REQUEST_CONSULTA_VENDEDOR == requestCode && resultCode == getActivity().RESULT_OK) {
            idvendedor = data.getIntExtra("vendedor_id", 0);
            String nomeVendedor;
            nomeVendedor = vendedordao.nomeVendedor(Integer.toString(idvendedor));
            edit_vendedor.setText(nomeVendedor);

        }

    }




    @Override
    public void onDetach() {
        super.onDetach();

    }


    private String getDateTime() {
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date date = new Date();
        return dateFormat.format(date);
    }


    @Override
    public void atualizar() {
        System.out.println("Fragment Pedido");
    }




}
