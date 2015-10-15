package br.grupointegrado.appmetaforadevenda.Dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import java.util.ArrayList;
import java.util.List;

import br.grupointegrado.appmetaforadevenda.Vendedor.Vendedor;

/**
 * Created by eli on 06/10/2015.
 */
public class VendedorDao extends AppDao {


    public VendedorDao(Context context) {
        super(context);
    }

    public void saveVendedor(String nome) {
        ContentValues cv = new ContentValues();
        cv.put("Nome", nome);



        getWritableDatabase().insert("Vendedor", null, cv);

    }
    public List<Vendedor> list() {
        Cursor c = getReadableDatabase().rawQuery("Select idVendedor, nome from Vendedor ",null);

        List<Vendedor> vendedores = new ArrayList<>();


        while (c.moveToNext()) {

            Vendedor vendedor = new Vendedor();
            vendedor.setIdvendedor(c.getInt(0));
            vendedor.setNome(c.getString(1));


            vendedores.add(vendedor);

        }
        c.close();
        return vendedores;
    }
    public String nomeVendedor(String id){
        Cursor c = getReadableDatabase().rawQuery("Select nome from Vendedor where idVendedor = ?",
                new String[]{id});
        String nome = " ";
        if (c != null){
            try{
                c.moveToFirst();
                return c.getString(0);

            }finally {
                c.close();
            }

        }


        return nome;
    }

    public Boolean validaVendedor(String id){
        Cursor c = getReadableDatabase().rawQuery("Select nome from Vendedor where idVendedor = ?",
                new String[]{id});
        if (c.getCount() == 1)
            return true;

        else return false;
    }




}
