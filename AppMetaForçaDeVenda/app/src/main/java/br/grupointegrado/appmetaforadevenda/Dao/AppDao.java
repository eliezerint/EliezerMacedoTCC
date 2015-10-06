package br.grupointegrado.appmetaforadevenda.Dao;

import android.content.ContentValues;
import android.content.Context;
import android.content.res.Resources;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

import br.grupointegrado.appmetaforadevenda.Pedido.Pedido;
import br.grupointegrado.appmetaforadevenda.Pessoa.Cidade;
import br.grupointegrado.appmetaforadevenda.Pessoa.Estado;
import br.grupointegrado.appmetaforadevenda.R;

/**
 * Created by eli on 26/07/2015.
 */

public class AppDao extends SQLiteOpenHelper {
    public static final String BD_NAME = "ForcaVenda";
    public static final int BD_Version = 3;
    private Resources res;
    private Pedido pedido;
    private Estado estado;

    public AppDao(Context context) {
        super(context, AppDao.BD_NAME, null, AppDao.BD_Version);

        res = context.getResources();

    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        //  db.execSQL(res.getString(R.string.SQL_CREATE_BANCO));
        db.execSQL(res.getString(R.string.SQL_CREATE_PAIS));
        db.execSQL(res.getString(R.string.SQL_CREATE_ESTADO));
        db.execSQL(res.getString(R.string.SQL_CREATE_CIDADE));
        db.execSQL(res.getString(R.string.SQL_CREATE_TELEFONE));
        db.execSQL(res.getString(R.string.SQL_CREATE_PESSOA));
        db.execSQL(res.getString(R.string.SQL_CREATE_FILIAL));
        db.execSQL(res.getString(R.string.SQL_CREATE_CONDPGTO));

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }


//    public String validaUsuario(String user) {
//        Cursor consulta = getReadableDatabase().rawQuery("Select id_estado from estado where id_estado = ? ", new String[]{user});
//         String e;
//        if (consulta.getCount() == 1) {
//            return e = consulta.getString(0);
//
//        }
//        else return"2";
//
//    }

//

}


