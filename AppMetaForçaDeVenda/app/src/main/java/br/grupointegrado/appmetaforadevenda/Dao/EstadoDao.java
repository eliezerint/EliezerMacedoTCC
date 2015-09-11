package br.grupointegrado.appmetaforadevenda.Dao;

import android.content.ContentValues;
import android.content.Context;

/**
 * Created by eli on 06/09/2015.
 */
public class EstadoDao extends AppDao {

    public EstadoDao(Context context) {
        super(context);
    }

    //    teste de inserção e consulta de estado

    public void saveEstado() {
        ContentValues cv = new ContentValues();
        cv.put("id_estado", "PR");
        cv.put("Descricao", "Parana");


        getWritableDatabase().insert("estado", null, cv);

    }
}
