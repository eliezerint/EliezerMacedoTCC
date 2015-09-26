package br.grupointegrado.appmetaforadevenda.Dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import java.util.ArrayList;
import java.util.List;

import br.grupointegrado.appmetaforadevenda.Pessoa.Pessoa;
import br.grupointegrado.appmetaforadevenda.Pessoa.Telefone;
import br.grupointegrado.appmetaforadevenda.Util.ConvesorUtil;

import static br.grupointegrado.appmetaforadevenda.Util.ConvesorUtil.dateParaSQLDate;
import static br.grupointegrado.appmetaforadevenda.Util.ConvesorUtil.dateParaString;
import static br.grupointegrado.appmetaforadevenda.Util.ConvesorUtil.stringParaDate;
import static br.grupointegrado.appmetaforadevenda.Util.ConvesorUtil.stringParaSQLDate;

/**
 * Created by eli on 06/09/2015.
 */
public class PessoaDao extends AppDao {
    public PessoaDao(Context context) {
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

    public void savePessoa(Pessoa pessoa) {
        ContentValues cv = new ContentValues();
        cv.put("id_Cidade", pessoa.getIdCidade());
        cv.put("CNPJCPF", pessoa.getCnpjCpf());
        cv.put("Endereco", pessoa.getEndereco());
        cv.put("Numero", pessoa.getNumero());
        cv.put("Bairro", pessoa.getBairro());
        cv.put("cidade", pessoa.getCidade());
        cv.put("Data_Nascimento",(dateParaString(pessoa.getDataNascimento())));
        cv.put("Data_Cadastro",(dateParaString(pessoa.getDataCadastro())));
        cv.put("Complemento", pessoa.getComplemento());
        cv.put("Email", pessoa.getEmail());
        cv.put("Razao_socialNome", pessoa.getRazaoSocialNome());
        cv.put("Nome_fantasiaApelido", pessoa.getFantasiaApelido());
        cv.put("inscriEstadualRG", pessoa.getInscriEstadualRG());
        cv.put("Data_ultima_compra", (dateParaString(pessoa.getDataUltimacompra())));
        cv.put("Valor_ultima_compra", pessoa.getValorUltimacompra());



        getWritableDatabase().insert("Pessoa", null, cv);

    }

    public List<Pessoa> list() {
        Cursor c = getReadableDatabase().rawQuery("Select  idPessoa,"
                + " id_Cidade, CNPJCPF , Endereco , Numero , Bairro , Cidade "
                + " , Data_Nascimento ,"
                +  " Data_Cadastro , Complemento , Email , Razao_socialNome , Nome_fantasiaApelido , "
                +  " inscriEstadualRG , Data_ultima_compra , Valor_ultima_compra  From Pessoa  ", null);

        List<Pessoa> pessoas = new ArrayList<>();


        while (c.moveToNext()) {

            Pessoa pessoa = new Pessoa();
            pessoa.setIdpessoa(c.getInt(0));
            pessoa.setIdCidade(c.getInt(1));
            pessoa.setCnpjCpf(c.getString(2));
            pessoa.setEndereco(c.getString(3));
            pessoa.setNumero(c.getString(4));
            pessoa.setBairro(c.getString(5));
            pessoa.setCidade(c.getString(6));
            pessoa.setDataNascimento(stringParaSQLDate(c.getString(7)));
            pessoa.setDataCadastro(stringParaSQLDate(c.getString(8)));
            pessoa.setComplemento(c.getString(9));
            pessoa.setEmail(c.getString(10));
            pessoa.setRazaoSocialNome(c.getString(11));
            pessoa.setFantasiaApelido(c.getString(12));
            pessoa.setInscriEstadualRG(c.getString(13));
            pessoa.setDataUltimacompra(stringParaSQLDate(c.getString(14)));
            pessoa.setValorUltimacompra(c.getDouble(15));


            pessoas.add(pessoa);

        }
        c.close();
        return pessoas;
    }
    public void delete(Integer id) {

        getWritableDatabase().delete("Pessoa", "idPessoa = ?", new String[]{id.toString()});
    }

    public Integer CosultaCliente(String CNPJCPF){
        Cursor consulta = getReadableDatabase().rawQuery("Select CNPJCPF from Pessoa " +
                        "where CNPJCPF like '?' ",
                new String[]{CNPJCPF});
        Integer id = 0;

        if (consulta != null) {
            try {
                if (consulta.moveToFirst()) {
                    return id = consulta.getInt(0);
                }
            } finally {
                consulta.close();
            }



        }

        return id;


    }
}




