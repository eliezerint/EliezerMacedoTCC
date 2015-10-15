package br.grupointegrado.appmetaforadevenda.TelaConsulta;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.Toast;

import com.afollestad.materialdialogs.MaterialDialog;
import com.rengwuxian.materialedittext.MaterialEditText;

import java.util.ArrayList;
import java.util.List;

import br.grupointegrado.appmetaforadevenda.Dao.ProdutoDao;
import br.grupointegrado.appmetaforadevenda.Listagem.AdapterProduto;
import br.grupointegrado.appmetaforadevenda.Pedido.ItensPedido;
import br.grupointegrado.appmetaforadevenda.Pessoa.Pessoa;
import br.grupointegrado.appmetaforadevenda.Produtos.Grupos_Produtos;
import br.grupointegrado.appmetaforadevenda.Produtos.Produtos;
import br.grupointegrado.appmetaforadevenda.Produtos.Tabela_preco;
import br.grupointegrado.appmetaforadevenda.Produtos.UnidadeMedida;
import br.grupointegrado.appmetaforadevenda.R;
import br.grupointegrado.appmetaforadevenda.Util.Mask;

public class ConsultaProdutoActivity extends AppCompatActivity {

    private RecyclerView recyclerviewProdutos;


    private MaterialEditText edit_produto;
    private MaterialEditText edit_vlunitario;
    private MaterialEditText edit_quantidade;
    private MaterialEditText edit_descontovalor;
    private MaterialEditText edit_descontopercentual;
    private MaterialEditText edit_valorTotal;
    private RadioButton radio_buttonReal;
    private RadioButton radio_buttonPercentual;

    private Toolbar atoolbar;
    private AdapterProduto adapterproduto;
    private List<Produtos> lista_telefone;

    private ProdutoDao produtodao;
    private Produtos produto;

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

        final ItensPedido itenpedido = new ItensPedido();


       /* produtodao.saveGrupoProduto(insertGrupo());
        produtodao.saveUnidadeMedida(insertUnidadeMedida());
        produtodao.saveProduto(insertProduto());
        produtodao.savePreco(insertTabelaPreco());*/


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


        MaterialDialog dialog = new MaterialDialog.Builder(this)
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
                        itenpedido.setDesconto(Double.parseDouble(edit_descontopercentual.getText().toString()));
                        itenpedido.setTotal(Double.parseDouble(edit_valorTotal.getText().toString()));


                        telItens(itenpedido);

                        dialog.dismiss();
                    }

                    @Override
                    public void onNegative(MaterialDialog dialog) {

                        dialog.dismiss();

                    }

                }).build();

        edit_vlunitario = (MaterialEditText) dialog.findViewById(R.id.edit_dialogvlunitario);
        edit_quantidade = (MaterialEditText) dialog.findViewById(R.id.edit_dialogquantidade);
        edit_descontopercentual = (MaterialEditText) dialog.findViewById(R.id.edit_dialogdescontoperc);
        edit_descontovalor = (MaterialEditText) dialog.findViewById(R.id.edit_dialogdescontovalor);
        edit_valorTotal = (MaterialEditText) dialog.findViewById(R.id.edit_dialogvalorTotal);

        edit_vlunitario.setText(produto.getVlunitario().toString());


        edit_descontopercentual.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                double soma = 0.00, unitario = 0.00, descontoperc = 0.00, descontovalor = 0.00,
                        total = 0.00, unitarionovo = 0.000;
                if (!hasFocus)
                    if (!edit_descontopercentual.getText().toString().isEmpty()) {
                        descontoperc = Double.parseDouble(edit_descontopercentual.getText().toString());
                        unitario = Double.parseDouble(edit_vlunitario.getText().toString());

                            soma = unitario - (unitario * descontoperc / divisao);

                            descontovalor = (unitario * descontoperc / divisao);

                            unitarionovo = soma;
                            edit_descontovalor.setText(String.valueOf(descontovalor));
                            edit_vlunitario.setText(String.valueOf(unitarionovo));
                            if (edit_quantidade.getText().toString().isEmpty()) {
                                total = unitarionovo * 1;
                                edit_quantidade.setText("1");
                            } else {
                                total = unitarionovo * Double.parseDouble(edit_quantidade.getText().toString());
                            }
                            edit_valorTotal.setText(String.valueOf(total));


                    }
            }
        });

        edit_descontovalor.setOnFocusChangeListener(new View.OnFocusChangeListener() {
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

                        unitarionovo = soma;

                        edit_descontopercentual.setText(String.valueOf(descontoperc));
                        edit_vlunitario.setText(String.valueOf(unitarionovo));
                        if (edit_quantidade.getText().toString().isEmpty()) {
                            total = unitarionovo * 1;
                            edit_quantidade.setText("1");
                        } else {
                            total = unitarionovo * Double.parseDouble(edit_quantidade.getText().toString());
                        }
                        edit_valorTotal.setText(String.valueOf(total));

                    }
            }
        });


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
        // gp.setDescricao("Cereais");
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
        p.setDescricao("Sabão Em pó");
        p.setQuantidaestoque(5.00);
        p.setVlunitario(6.00);

        return p;
    }

    public Tabela_preco insertTabelaPreco() {
        Tabela_preco tb = new Tabela_preco();
        tb.setIdProduto(1);
        tb.setTp_venda("Atacado");
        tb.setPrecominimo(5.00);
        tb.setPrecomaximo(8.00);

        return tb;
    }


    public ItensPedido getitens(ItensPedido itens) {
        return new ItensPedido(itens.getIdProduto(), itens.getProduto(),
                itens.getVlunitario(), itens.getQuantidade(), itens.getDesconto(), itens.getTotal());
    }


}











