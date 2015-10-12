package br.grupointegrado.appmetaforadevenda.TelaCadastro;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;


import com.rengwuxian.materialedittext.MaterialEditText;

import java.io.Serializable;

import br.grupointegrado.appmetaforadevenda.Pedido.ItensPedido;
import br.grupointegrado.appmetaforadevenda.R;

import static br.grupointegrado.appmetaforadevenda.Util.ConvesorUtil.*;
import static br.grupointegrado.appmetaforadevenda.Util.ConvesorUtil.stringParaDouble;

public class CadastroItensPedidoActivity extends AppCompatActivity {


    private Toolbar atoolbar;
    private MaterialEditText edit_produto;
    private MaterialEditText edit_quantidade;
    private MaterialEditText edit_vlunitario;
    private MaterialEditText edit_desconto;
    private MaterialEditText edit_valortotal;


    private ItensPedido itenspedido;

    private Boolean addItens = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_itens_pedido);

        atoolbar = (Toolbar) (findViewById(R.id.tb_main));
        atoolbar.setTitle("Itens Pedido");

        setSupportActionBar(atoolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        edit_produto = (MaterialEditText) findViewById(R.id.edit_produto);
        edit_quantidade = (MaterialEditText) findViewById(R.id.edit_Quantidade);
        edit_vlunitario = (MaterialEditText) findViewById(R.id.edit_vlunitario);
        edit_desconto = (MaterialEditText) findViewById(R.id.edit_desconto);
        edit_valortotal = (MaterialEditText) findViewById(R.id.edit_valor_total);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_itens_pedido, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {

            case R.id.home:
                finish();

                break;

            case R.id.add_itens:
                Intent data = new Intent();
                data.putExtra("itenspedido_object", getItensPedido());
                setResult(RESULT_OK, data);
                finish();

                break;
        }


        return true;
    }


    public ItensPedido getItensPedido() {
        return new ItensPedido ("p",
                Double.parseDouble("2"),
                Double.parseDouble("3"),
                Double.parseDouble("4"),
                Double.parseDouble("5")

                );



    }
}
