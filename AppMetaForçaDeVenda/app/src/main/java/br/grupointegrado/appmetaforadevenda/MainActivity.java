package br.grupointegrado.appmetaforadevenda;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.support.v7.widget.Toolbar;

import br.grupointegrado.appmetaforadevenda.Dao.AppDao;
import br.grupointegrado.appmetaforadevenda.Pessoa.Estado;


public class MainActivity extends ActionBarActivity {
     private AppDao DAO;
     private Estado estado;
     public static String IdVendedor;
     private EditText edtcdVendedor;
     private Toolbar  atoolbar;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        atoolbar = (Toolbar) findViewById(R.id.tb_main);
        atoolbar.setTitle(" Login ");
        atoolbar.setLogo(R.drawable.ic_logo);
        setSupportActionBar(atoolbar);

        DAO = new AppDao(this);

        edtcdVendedor = (EditText)findViewById(R.id.edtcdVendedor);

         edtcdVendedor.getText().toString();



    }
    public  void entrar(View view){

//     DAO.saveEstado("SC","Campo mourao");

        Intent i = new Intent(this.getApplication(),MenuActivity.class);


        startActivity(i);



    }



}
