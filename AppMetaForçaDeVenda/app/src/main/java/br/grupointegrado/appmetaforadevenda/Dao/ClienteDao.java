package br.grupointegrado.appmetaforadevenda.Dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import java.util.ArrayList;
import java.util.List;

import br.grupointegrado.appmetaforadevenda.Pessoa.Pessoa;
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
        cv.put("CPFCNPJ", telefone.getCPF());


        getWritableDatabase().insert("Telefone", null, cv);

    }

    public List<Pessoa> list() {
        Cursor c = getReadableDatabase().rawQuery("Select  idPessoa,"
                + " id_Cidade, CNPJCPF , Endereco , Numero , Bairro, Telefone  , "
                +  " Data_Cadastro , Complemento , Email , Razao_socialNome , Nome_fantasiaApelido , "
                +  " inscriEstadualRG , Data_ultima_compra , Valor_ultima_compra  From Pessoa ", null);

        List<Pessoa> pessoas = new ArrayList<>();


        while (c.moveToNext()) {

            Pessoa pessoa = new Pessoa();
            pessoa.setIdpessoa(c.getInt(0));
            pessoa.setCnpjCpf(c.getString(1));


            pessoas.add(pessoa);

        }
        c.close();
        return pessoas;
    }
}




