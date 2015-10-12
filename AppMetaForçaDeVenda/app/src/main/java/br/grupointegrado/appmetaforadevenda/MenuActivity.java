package br.grupointegrado.appmetaforadevenda;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.afollestad.materialdialogs.MaterialDialog;

import net.i2p.android.ext.floatingactionbutton.FloatingActionButton;

import br.grupointegrado.appmetaforadevenda.Dao.AppDao;
import br.grupointegrado.appmetaforadevenda.TelaCadastro.CadastroPedidoActivity;
import br.grupointegrado.appmetaforadevenda.TelaConsulta.*;

public class MenuActivity extends AppCompatActivity {


    private AppDao dao;
    private TextView tvEmpty;
    private Toolbar atoolbar;
    private Toolbar atoolbarBotton;
    private FloatingActionButton Cliente;
    private TextView tvCadastrarCliente;
    private FloatingActionButton Pedido;
    private FloatingActionButton Exportacao;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        atoolbar = (Toolbar) findViewById(R.id.tb_main);
        atoolbar.setTitle(" Meta ");
        atoolbar.setSubtitle("Força  De Venda");
        atoolbar.setLogo(R.drawable.ic_logo);
        atoolbar.setNavigationIcon(R.drawable.ic_menu);
        setSupportActionBar(atoolbar);


        atoolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });


        // voltar no toolbar


        tvEmpty = (TextView) findViewById(R.id.tvEmpty);

        Cliente = (FloatingActionButton) findViewById(R.id.Cliente);
        Pedido = (FloatingActionButton) findViewById(R.id.Pedido);
        Exportacao = (FloatingActionButton) findViewById(R.id.Exportacao);



        Cliente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogsCliente();
            }
        });

        Pedido.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FazerPedido();
            }
        });

        Exportacao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogsDados();
            }
        });


//        tvCadastrarCliente.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent i = new Intent(getApplicationContext(), CadastroPessoaActivity.class);
//
//                startActivity(i);
//            }
//        });


//      dao = new AppDao(this); consulta de estado
//        tvEmpty.setText(dao.validaUsuario("SC"));


    }

    public void DialogsDados() {
        boolean wrapInScrollView = true;
        MaterialDialog app = new MaterialDialog.Builder(this)
                .title("Importação | Exportação")
                .customView(R.layout.activity_dialogsdados, wrapInScrollView)
                .positiveText("Sair")
                .show();


    }

    public void DialogsCliente() {
        boolean wrapInScrollView = true;
        MaterialDialog app = new MaterialDialog.Builder(this)
                .title("Dados de Clientes")
                .items(R.array.Array_tp_pessoa_consulta)
                .positiveText("Sair")
                .autoDismiss(false)
                .itemsCallback(new MaterialDialog.ListCallback() {
                    @Override
                    public void onSelection(MaterialDialog dialog, View view, int which, CharSequence text) {
                        Intent i;
                        if (text.equals("Cliente")) {
                            i = new Intent(dialog.getContext(), ConsultaClienteActivity.class);
                            startActivity(i);
                            dialog.dismiss();
                        } else if (text.equals("Cidade")) {
                            i = new Intent(dialog.getContext(), ConsultaCidadeActivity.class);
                            startActivity(i);
                            dialog.dismiss();
                        }

                    }
                })
                .show();


    }


    public void FazerPedido() {

        Intent i = new Intent(this.getApplication(), CadastroPedidoActivity.class);


        startActivity(i);


    }


    @Override
    protected void onResume() {
        super.onResume();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            atoolbar.setBackgroundResource(R.drawable.toolbar_rounded_corners);
        }
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.Produto:
                Intent i = new Intent(this.getApplication(), ConsultaProdutoActivity.class);


                startActivity(i);

                break;

            case R.id.Pessoa:


                break;

        }

        return super.onContextItemSelected(item);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();


        return super.onOptionsItemSelected(item);
    }
}
