package br.grupointegrado.appmetaforadevenda;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;

import com.rengwuxian.materialedittext.MaterialEditText;

import br.grupointegrado.appmetaforadevenda.Dao.AppDao;
import br.grupointegrado.appmetaforadevenda.Dao.VendedorDao;
import br.grupointegrado.appmetaforadevenda.Pessoa.Estado;


public class MainActivity extends AppCompatActivity {
     private AppDao DAO;
     private VendedorDao vendedordao;
     private Estado estado;
     private Button btentrar;
     public static Integer idvendedortelainicial;
     private MaterialEditText edtcdVendedor;
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
        vendedordao = new VendedorDao(this);

        edtcdVendedor = (MaterialEditText)findViewById(R.id.edtcdVendedor);
        btentrar      = (Button)findViewById(R.id.btentrar);


        vendedordao.saveVendedor("Lucas", 20.00 , 20.00);

        btentrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!edtcdVendedor.getText().toString().isEmpty()) {
                    if (vendedordao.validaVendedor(edtcdVendedor.getText().toString())) {
                        idvendedortelainicial = Integer.parseInt(edtcdVendedor.getText().toString());
                        entrar();

                    } else
                        Toast.makeText(v.getContext(), "Vendedor nao autorizado", Toast.LENGTH_SHORT).show();


                }else Toast.makeText(v.getContext(), "O campo codigo não informado", Toast.LENGTH_SHORT).show();
            }
        });




    }
    public  void entrar(){


        Intent i = new Intent(this.getApplication(),MenuActivity.class);


        startActivity(i);



    }



}
