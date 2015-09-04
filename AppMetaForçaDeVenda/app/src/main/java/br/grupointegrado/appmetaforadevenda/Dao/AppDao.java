package br.grupointegrado.appmetaforadevenda.Dao;

import android.content.ContentValues;
import android.content.Context;
import android.content.res.Resources;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import br.grupointegrado.appmetaforadevenda.Pedido.Pedido;
import br.grupointegrado.appmetaforadevenda.Pessoa.Estado;
import br.grupointegrado.appmetaforadevenda.R;

/**
 * Created by eli on 26/07/2015.
 */

    public class AppDao extends SQLiteOpenHelper {
        public static final String BD_NAME = "ForcaVenda";
        public static final int BD_Version = 1;
        private Resources res;
        private Pedido pedido;
        private Estado estado;

        public AppDao(Context context) {
            super(context, AppDao.BD_NAME, null, AppDao.BD_Version);

            res = context.getResources();

        }


        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(res.getString(R.string.SQL_CREATE_BANCO));
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        }

//    teste de inserção e consulta de estado

//    public void saveEstado(String estado1,String cidade) {
//        ContentValues cv = new ContentValues();
//        cv.put("id_estado", estado1 );
//        cv.put("Descricao", cidade);
//
//
//
//        getWritableDatabase().insert("estado", null, cv);
//
//    }
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

//    teste de inserção e consulta de Cidade

//    public void saveCidade(Integer idCidade,String estado1,String cidade,String Ibge,String pais) {
//        ContentValues cv = new ContentValues();
//        cv.put("id_cidade", idCidade );
//        cv.put("id_estado", estado1 );
//        cv.put("Descricao", cidade);
//        cv.put("IBGE", Ibge);
//        cv.put("PAIS", pais);
//
//
//
//        getWritableDatabase().insert("Cidade", null, cv);
//
//    }


    }


