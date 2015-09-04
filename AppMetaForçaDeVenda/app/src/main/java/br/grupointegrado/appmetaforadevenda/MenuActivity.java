package br.grupointegrado.appmetaforadevenda;

import android.content.Intent;
import android.os.Build;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.afollestad.materialdialogs.MaterialDialog;

import br.grupointegrado.appmetaforadevenda.Dao.AppDao;
import br.grupointegrado.appmetaforadevenda.TelaCadastro.*;
import br.grupointegrado.appmetaforadevenda.TelaConsulta.*;

public class MenuActivity extends ActionBarActivity {


    private AppDao dao;
    private TextView tvEmpty;
    private Toolbar atoolbar;
    private Toolbar atoolbarBotton;
    FloatingActionButton Cliente;
    private TextView tvCadastrarCliente;
    FloatingActionButton Pedido;
    FloatingActionButton Exportacao;


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
                DialogsMenu();
            }
        });


        // voltar no toolbar


        tvEmpty = (TextView) findViewById(R.id.tvEmpty);

        Cliente = (FloatingActionButton) findViewById(R.id.Cliente);
        Pedido = (FloatingActionButton) findViewById(R.id.Pedido);
        Exportacao = (FloatingActionButton) findViewById(R.id.Exportacao);
        tvCadastrarCliente = (TextView) findViewById(R.id.tvCadastrarCliente);


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
                .customView(R.layout.activity_dialogspessoa, wrapInScrollView)
                .positiveText("Sair")
                .show();


    }

    public void DialogsMenu() {
        boolean wrapInScrollView = true;
        MaterialDialog app = new MaterialDialog.Builder(this)
                .title("Menu")
                .customView(R.layout.activity_dialogsmenu, wrapInScrollView)
                .positiveText("Sair")
                .show();


    }

    public void FazerPedido() {

        Intent i = new Intent(this.getApplication(), PedidoActivity.class);


        startActivity(i);


    }



    public void ConsultarCliente(View view) {

        Intent i = new Intent(this.getApplication(), ConsultaClienteActivity.class);


        startActivity(i);


    }


    public void ConsultarCidade(View view) {

        Intent i = new Intent(this.getApplication(), ConsultaCidadeActivity.class);


        startActivity(i);
    }

   //Nao cria o menu na hora do click
   public void ConsultarDados(View view) {

        switch (view.getId()) {
            case R.id.Cliente:
                try {
                    Cliente.setOnCreateContextMenuListener(new View.OnCreateContextMenuListener()

                    {
                        @Override
                        public void onCreateContextMenu(ContextMenu menu, View view, ContextMenu.ContextMenuInfo
                                contextMenuInfo) {
                            getMenuInflater().inflate(R.menu.menu_menu, menu);
                            menu.setHeaderTitle("Consultar Dados");
                        }
                    });
                } catch (Exception e) {

                    e.printStackTrace();
                }
                break;
        }
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
                Intent i = new Intent(this.getApplication(), ProdutoActivity.class);


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
