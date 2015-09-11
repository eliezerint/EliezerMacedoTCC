package br.grupointegrado.appmetaforadevenda;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;


import br.grupointegrado.appmetaforadevenda.MainActivity;


public class PedidoActivity extends ActionBarActivity {
    private EditText idvendedorpedido;
    private Toolbar atoolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pedido);

        atoolbar = (Toolbar) findViewById(R.id.tb_main);
        atoolbar.setTitle(" Pedido ");
        atoolbar.setLogo(R.drawable.ic_description_black_18dp);
        atoolbar.setNavigationIcon(R.drawable.ic_menu);
        setSupportActionBar(atoolbar);

        idvendedorpedido =  (EditText)findViewById(R.id.idvendedor);

        idvendedorpedido.setText(MainActivity.IdVendedor);



    }




    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_pedido, menu);
        return true;

    }
    public  void Produtos(View view){
        Intent i = new Intent(getApplication(),ProdutoActivity.class);
        startActivity(i);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();


        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
