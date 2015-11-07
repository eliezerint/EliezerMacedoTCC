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

import java.util.ArrayList;
import java.util.List;

import br.grupointegrado.appmetaforadevenda.Dao.PedidoDao;
import br.grupointegrado.appmetaforadevenda.Dao.ProdutoDao;
import br.grupointegrado.appmetaforadevenda.Dao.VendedorDao;
import br.grupointegrado.appmetaforadevenda.Listagem.AdapterProduto;
import br.grupointegrado.appmetaforadevenda.MainActivity;
import br.grupointegrado.appmetaforadevenda.Pedido.ItensPedido;
import br.grupointegrado.appmetaforadevenda.Produtos.Grupos_Produtos;
import br.grupointegrado.appmetaforadevenda.Produtos.Produtos;
import br.grupointegrado.appmetaforadevenda.Produtos.TabelaItenPreco;
import br.grupointegrado.appmetaforadevenda.Produtos.Tabelapreco;
import br.grupointegrado.appmetaforadevenda.Produtos.UnidadeMedida;
import br.grupointegrado.appmetaforadevenda.R;
import br.grupointegrado.appmetaforadevenda.Vendedor.Vendedor;

public class ConsultaProdutoActivity extends AppCompatActivity {

    private RecyclerView recyclerviewProdutos;


    private MaterialEditText edit_produto;
    private MaterialEditText edit_vlunitario;
    private MaterialEditText edit_quantidade;
    private MaterialEditText edit_descontovalor;
    private MaterialEditText edit_descontopercentual;
    private MaterialEditText edit_valorTotal;
    private MaterialEditText edit_dialogvalorTotalcomDesconto;
    private MaterialBetterSpinner spinnerTp_tabela;
    private MaterialBetterSpinner spinnerTabela_preco;

    private Toolbar atoolbar;
    private AdapterProduto adapterproduto;
    private List<Produtos> lista_telefone;
    private Double max_desconto;
    private Double max_acrescimo;
    private Integer posicaoTabelaPreco = 0;
    private Integer posicaoTabelaItenPreco = 0;
    private Integer idproduto;

    private ProdutoDao produtodao;
    private VendedorDao vendedordao;
    private Produtos produto;
    private PedidoDao pedidodao;

    private ItensPedido itens;


    private boolean selecionandoProduto = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consulta_produto);

        atoolbar = (Toolbar) findViewById(R.id.tb_main);
        atoolbar.setTitle("Consulta de Produtos");

        setSupportActionBar(atoolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        produtodao = new ProdutoDao(this);
        vendedordao = new VendedorDao(this);

        final ItensPedido itenpedido = new ItensPedido();

         produtodao.saveGrupoProduto(insertGrupo());
         produtodao.saveUnidadeMedida(insertUnidadeMedida());
         produtodao.saveProduto(insertProduto());

           produtodao.savePreco(insertTabelaPreco());
           produtodao.saveItemtabelaPreco(insertTabelaItenPreco());




        recyclerviewProdutos = (RecyclerView) findViewById(R.id.RecyviewProduto);

        if (getIntent().getExtras() != null)
            selecionandoProduto = getIntent().getExtras().getBoolean("selecionando_produto", false);


        final StaggeredGridLayoutManager llm = new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL);
        llm.setGapStrategy(StaggeredGridLayoutManager.GAP_HANDLING_NONE);
        recyclerviewProdutos.setLayoutManager(llm);

        adapterproduto = new AdapterProduto(this, new ArrayList<Produtos>()) {
            @Override
            protected void onItemClickListener(int adapterPosition, int layoutPosition) {
                // evento de click simples

                Produtos produto = adapterproduto.getItems().get(adapterPosition);


                if (selecionandoProduto) {

                    idproduto = produto.getIdproduto();
                    insertItens(produto, itenpedido);


                } else {
                }
            }

            @Override
            protected boolean onLongItemClickListener(int adapterPosition, int layoutPosition) {
                // evento e click longo

                Produtos produto = adapterproduto.getItems().get(adapterPosition);


                return true;
            }


        };

        recyclerviewProdutos.setAdapter(adapterproduto);

        consultaProduto();

    }

    public void telItens(ItensPedido itens) {
        Intent data = new Intent();
        data.putExtra("itens_object", itens);
        setResult(RESULT_OK, data);
        finish();
    }

    private void insertItens(final Produtos produto, final ItensPedido itenpedido) {
        final Integer divisao = 100;


        final MaterialDialog dialog = new MaterialDialog.Builder(this)
                .title("Itens")
                .customView(R.layout.layout_dialogs_itens, true)
                .positiveText("Salvar")
                .negativeText("Sair")
                .autoDismiss(false)
                .callback(new MaterialDialog.ButtonCallback() {
                    @Override
                    public void onPositive(MaterialDialog dialog) {

                        itenpedido.setIdProduto(produto.getIdproduto());
                        itenpedido.setProduto(produto.getDescricao());

                        itenpedido.setVlunitario(Double.parseDouble(edit_vlunitario.getText().toString()));
                        itenpedido.setQuantidade(Double.parseDouble(edit_quantidade.getText().toString()));
                        itenpedido.setDesconto(Double.parseDouble(edit_descontovalor.getText().toString()));
                        itenpedido.setTotal(Double.parseDouble(edit_valorTotal.getText().toString()));
                        itenpedido.setTotalCdesconto(Double.parseDouble(edit_dialogvalorTotalcomDesconto.getText().toString()));


                        telItens(itenpedido);

                        idproduto = 0;

                        dialog.dismiss();
                    }

                    @Override
                    public void onNegative(MaterialDialog dialog) {

                        idproduto = 0;
                        dialog.dismiss();

                    }

                }).build();

        edit_vlunitario = (MaterialEditText) dialog.findViewById(R.id.edit_dialogvlunitario);
        edit_quantidade = (MaterialEditText) dialog.findViewById(R.id.edit_dialogquantidade);
        edit_descontopercentual = (MaterialEditText) dialog.findViewById(R.id.edit_dialogdescontoperc);
        edit_descontovalor = (MaterialEditText) dialog.findViewById(R.id.edit_dialogdescontovalor);
        edit_valorTotal = (MaterialEditText) dialog.findViewById(R.id.edit_dialogvalorTotal);
        edit_dialogvalorTotalcomDesconto = (MaterialEditText) dialog.findViewById(R.id.edit_dialogvalorTotalcomDesconto);
        spinnerTp_tabela  = (MaterialBetterSpinner) dialog.findViewById(R.id.SpinnerTpTabela);
        spinnerTabela_preco  = (MaterialBetterSpinner) dialog.findViewById(R.id.SpinnerPreco);

        List<Tabelapreco> listTpTabela =  produtodao.listPrecoVEnda(idproduto.toString());

        ArrayAdapter<Tabelapreco> tptabela_adapter = new ArrayAdapter<Tabelapreco>(this, android.R.layout.simple_list_item_1, listTpTabela);


        spinnerTp_tabela.setAdapter(tptabela_adapter);






       List<TabelaItenPreco> listTpTabelaiten =  produtodao.listPrecoVEndaIten
                (listTpTabela.get(posicaoTabelaPreco).getIdTabelapreco().toString());

        final ArrayAdapter<TabelaItenPreco> tabelaiten_adapter = new ArrayAdapter<TabelaItenPreco>
                (this, android.R.layout.simple_list_item_1, listTpTabelaiten);


        spinnerTabela_preco.setAdapter(tabelaiten_adapter);


        spinnerTp_tabela.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                posicaoTabelaPreco = position;
                tabelaiten_adapter.notifyDataSetChanged();
        }
        });


        spinnerTabela_preco.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                posicaoTabelaItenPreco = position;
            }
        });


        Vendedor vendedor = vendedordao.ConsultaVendedorporid(MainActivity.idvendedortelainicial.toString());
        max_desconto = vendedor.getMax_desconto();


        edit_vlunitario.setText(tabelaiten_adapter.getItem(posicaoTabelaItenPreco).getVlunitario().toString());

        edit_quantidade.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                double soma = 0.00, valorUni = 0.00, qtde = 0.00;
                if (!hasFocus)
                    if (edit_quantidade.getText().toString().isEmpty()) {
                        valorUni = Double.parseDouble(edit_vlunitario.getText().toString());
                        qtde = 1;
                        soma = valorUni * qtde;
                        edit_valorTotal.setText(String.valueOf(soma));

                    } else {
                        valorUni = Double.parseDouble(edit_vlunitario.getText().toString());
                        qtde = Double.parseDouble(edit_quantidade.getText().toString());
                        soma = valorUni * qtde;
                        edit_valorTotal.setText(String.valueOf(soma));
                    }
            }
        });


        edit_descontopercentual.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                double soma = 0.00, unitario = 0.00, descontoperc = 0.00, descontovalor = 0.00,
                        total = 0.00, unitarionovo = 0.00;
                if (!hasFocus)
                    if (!edit_descontopercentual.getText().toString().isEmpty()) {
                        descontoperc = Double.parseDouble(edit_descontopercentual.getText().toString());
                        unitario = Double.parseDouble(edit_vlunitario.getText().toString());

                        descontovalor = (unitario * descontoperc / divisao);
                        soma = unitario - (unitario * descontoperc / divisao);

                        if (descontoperc > 100) {
                            edit_descontopercentual.setText("");
                            edit_descontovalor.setText("");
                            Toast.makeText(dialog.getContext(), "O valor de percentual maior que o valor unitario", Toast.LENGTH_SHORT).show();
                            total = 0.00;


                    } else if (descontovalor > max_desconto) {
                        edit_descontopercentual.setText("");
                        edit_descontovalor.setText("");
                        Toast.makeText(dialog.getContext(), "O valor de desconto não pode ser maior " + max_desconto, Toast.LENGTH_SHORT).show();
                        total = 0.00;
                    }  else {


                        unitarionovo = soma;
                        edit_descontovalor.setText(String.valueOf(descontovalor));
                        if (edit_quantidade.getText().toString().isEmpty()) {
                            total = unitarionovo * 1;
                            edit_quantidade.setText("1");
                        } else {
                            total = unitarionovo * Double.parseDouble(edit_quantidade.getText().toString());
                        }

                    }
                edit_dialogvalorTotalcomDesconto.setText(String.valueOf(total));


            }
        }
    }

    );

    edit_descontovalor.setOnFocusChangeListener(new View.OnFocusChangeListener()

                                                {
                                                    @Override
                                                    public void onFocusChange(View v, boolean hasFocus) {
                                                        double soma = 0.00, unitario = 0.00, descontoperc = 0, descontovalor = 0.00,
                                                                total = 0.00, unitarionovo = 0.000;
                                                        if (!hasFocus)
                                                            if (!edit_descontovalor.getText().toString().isEmpty()) {
                                                                unitario = Double.parseDouble(edit_vlunitario.getText().toString());
                                                                descontovalor = Double.parseDouble(edit_descontovalor.getText().toString());

                                                                soma = (unitario - descontovalor);

                                                                descontoperc = ((unitario - soma) * 100) / unitario;

                                                                if (descontovalor > unitario) {
                                                                    edit_descontopercentual.setText("");
                                                                    edit_descontovalor.setText("");
                                                                    Toast.makeText(dialog.getContext(), "O valor de desconto maior que o valor unitario", Toast.LENGTH_SHORT).show();
                                                                    total = 0.00;
                                                                } else if (descontovalor > max_desconto) {
                                                                    edit_descontopercentual.setText("");
                                                                    edit_descontovalor.setText("");
                                                                    Toast.makeText(dialog.getContext(), "O valor de desconto não pode ser maior " + max_desconto, Toast.LENGTH_SHORT).show();
                                                                    total = 0.00;
                                                                }  else {

                                                                    unitarionovo = soma;

                                                                    edit_descontopercentual.setText(String.valueOf(descontoperc));
                                                                    if (edit_quantidade.getText().toString().isEmpty()) {
                                                                        total = unitarionovo * 1;
                                                                        edit_quantidade.setText("1");
                                                                    } else {
                                                                        total = unitarionovo * Double.parseDouble(edit_quantidade.getText().toString());
                                                                    }
                                                                }
                                                                edit_dialogvalorTotalcomDesconto.setText(String.valueOf(total));

                                                            }
                                                    }
                                                }

    );


    dialog.show();


}


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_consulta__produto, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;

            case R.id.home:
                finish();
                break;

            case R.id.ConsultaProduto:
                consultaProduto();
                break;

        }

        return true;
    }

    private void consultaProduto() {

        adapterproduto.setItems(produtodao.list());
        adapterproduto.notifyDataSetChanged();

    }

    public Grupos_Produtos insertGrupo() {
        Grupos_Produtos gp = new Grupos_Produtos();
        // gp.setDescricaoUnidademedida("Cereais");
        gp.setDescricao("Higiene Pessoal");

        return gp;
    }

    public UnidadeMedida insertUnidadeMedida() {
        UnidadeMedida unM = new UnidadeMedida();
        unM.setDescricao("Kilograma");
        unM.setSigla("Kg");
        return unM;
    }

    public Produtos insertProduto() {
        Produtos p = new Produtos();
        p.setIdgrupopoduto(1);
        p.setIdUnidademedida(1);
        p.setDescricao("Detergente");

        return p;
    }

    public Tabelapreco insertTabelaPreco() {
        Tabelapreco tb = new Tabelapreco();
        tb.setIdProduto(1);
        tb.setDescricao("varejo");




        return tb;
    }

    public TabelaItenPreco insertTabelaItenPreco() {
        TabelaItenPreco tb = new TabelaItenPreco();
        tb.setIdtabelapreco(2);
        tb.setDescricao("aprazo");
        tb.setVlunitario(10.00);



        return tb;
    }


    public ItensPedido getitens(ItensPedido itens) {
        return new ItensPedido(itens.getIdProduto(), itens.getProduto(),
                itens.getVlunitario(), itens.getQuantidade(), itens.getDesconto(), itens.getTotal(), itens.getTotalCdesconto());
    }


}











