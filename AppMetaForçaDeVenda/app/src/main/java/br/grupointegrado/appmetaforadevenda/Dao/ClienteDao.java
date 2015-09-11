package br.grupointegrado.appmetaforadevenda.Dao;

import android.content.ContentValues;
import android.content.Context;

import br.grupointegrado.appmetaforadevenda.Pessoa.Cidade;
import br.grupointegrado.appmetaforadevenda.Pessoa.Telefone;

/**
 * Created by eli on 06/09/2015.
 */
public class ClienteDao extends AppDao {
    public ClienteDao(Context context) {
        super(context);
    }

    // teste de inserção e consulta de Cidade

    public void saveTelefone(Telefone telefone) {
        ContentValues cv = new ContentValues();
        cv.put("Numero", telefone.getNumero());
        cv.put("idPessoa", telefone.getIdPessoa());
        cv.put("CPF/CNPJ", telefone.getCPF());



        getWritableDatabase().insert("Cidade", null, cv);

    }
}




