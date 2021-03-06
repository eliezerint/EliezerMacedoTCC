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

    // inserção e consulta de TELEFONE



    public void saveTelefone(Telefone telefone) {
        ContentValues cv = new ContentValues();
        cv.put("Numero", telefone.getNumero());
        cv.put("Descricao", telefone.getTipo());
        cv.put("idPessoa", telefone.getIdPessoa());
        cv.put("CPFCNPJ", telefone.getCPF());


        getWritableDatabase().insert("Telefone", null, cv);

    }
    public void updateTelefone(Telefone telefone) {
        ContentValues cv = new ContentValues();
        cv.put("Numero", telefone.getNumero());
        cv.put("idPessoa", telefone.getIdPessoa());
        cv.put("CPFCNPJ", telefone.getCPF());


        getWritableDatabase().update("Telefone", cv, "idTelefone", new String[]{telefone.getIdTelefone().toString()});

    }

    public void deleteTelefone(Integer idpessoa) {

        getWritableDatabase().delete("Telefone", " idPessoa = ? ", new String[]{idpessoa.toString()});
    }
    public List<Telefone> listTelefone(String id) {
        Cursor c = getReadableDatabase().rawQuery("Select " +
                "idTelefone,idPessoa, numero,Descricao, CPFCNPJ   From Telefone where idPessoa = ?", new String[]{id});

        List<Telefone> telefones = new ArrayList<>();


        while (c.moveToNext()) {

            Telefone telefone = new Telefone();
            telefone.setIdTelefone(c.getInt(0));
            telefone.setIdPessoa(c.getInt(1));
            telefone.setNumero(c.getString(2));
            telefone.setTipo(c.getString(3));
            telefone.setCPF(c.getString(4));



            telefones.add(telefone);

        }
        c.close();
        return telefones;
    }

    // inserção e consulta de pessoa

    public void savePessoa(Pessoa pessoa) {
        ContentValues cv = new ContentValues();
        cv.put("id_Cidade", pessoa.getIdCidade());
        cv.put("CNPJCPF", pessoa.getCnpjCpf());
        cv.put("Endereco", pessoa.getEndereco());
        cv.put("Numero", pessoa.getNumero());
        cv.put("Bairro", pessoa.getBairro());
        cv.put("Cep", pessoa.getCep());
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

    public void Update(Pessoa pessoa) {
        ContentValues cv = new ContentValues();
        cv.put("id_Cidade", pessoa.getIdCidade());
        cv.put("CNPJCPF", pessoa.getCnpjCpf());
        cv.put("Endereco", pessoa.getEndereco());
        cv.put("Numero", pessoa.getNumero());
        cv.put("Bairro", pessoa.getBairro());
        cv.put("Data_Nascimento",(dateParaString(pessoa.getDataNascimento())));
        cv.put("Data_Cadastro",(dateParaString(pessoa.getDataCadastro())));
        cv.put("Complemento", pessoa.getComplemento());
        cv.put("Cep", pessoa.getCep());
        cv.put("Email", pessoa.getEmail());
        cv.put("Razao_socialNome", pessoa.getRazaoSocialNome());
        cv.put("Nome_fantasiaApelido", pessoa.getFantasiaApelido());
        cv.put("inscriEstadualRG", pessoa.getInscriEstadualRG());
        cv.put("Data_ultima_compra", (dateParaString(pessoa.getDataUltimacompra())));
        cv.put("Valor_ultima_compra", pessoa.getValorUltimacompra());



        getWritableDatabase().update("Pessoa", cv, "idPessoa = ?", new String[]{pessoa.getIdpessoa().toString()});



    }

    public List<Pessoa> list() {
        Cursor c = getReadableDatabase().rawQuery("Select  idPessoa,"
                + "  id_Cidade, CNPJCPF , Endereco , Numero , Bairro , cep"
                + " , Data_Nascimento ,"
                +  " Data_Cadastro , Complemento , Email , Razao_socialNome , Nome_fantasiaApelido , "
                +  " inscriEstadualRG , Data_ultima_compra , Valor_ultima_compra " +
                "        From Pessoa ", null);

        List<Pessoa> pessoas = new ArrayList<>();


        while (c.moveToNext()) {

            Pessoa pessoa = new Pessoa();
            pessoa.setIdpessoa(c.getInt(0));
            pessoa.setIdCidade(c.getInt(1));
            pessoa.setCnpjCpf(c.getString(2));
            pessoa.setEndereco(c.getString(3));
            pessoa.setNumero(c.getString(4));
            pessoa.setBairro(c.getString(5));
            pessoa.setCep(c.getString(6));
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
    public List<Pessoa> list(String nome) {
        Cursor c = getReadableDatabase().rawQuery("Select  idPessoa,"
                + "  id_Cidade, CNPJCPF , Endereco , Numero , Bairro , cep"
                + " , Data_Nascimento ,"
                +  " Data_Cadastro , Complemento , Email , Razao_socialNome , Nome_fantasiaApelido , "
                +  " inscriEstadualRG , Data_ultima_compra , Valor_ultima_compra " +
                "        From Pessoa  where Razao_socialNome like ?", new String[]{"%"+nome+"%"});

        List<Pessoa> pessoas = new ArrayList<>();


        while (c.moveToNext()) {

            Pessoa pessoa = new Pessoa();
            pessoa.setIdpessoa(c.getInt(0));
            pessoa.setIdCidade(c.getInt(1));
            pessoa.setCnpjCpf(c.getString(2));
            pessoa.setEndereco(c.getString(3));
            pessoa.setNumero(c.getString(4));
            pessoa.setBairro(c.getString(5));
            pessoa.setCep(c.getString(6));
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
    public List<Pessoa> listCpfCnpj(String cpfCnpj) {
        Cursor c = getReadableDatabase().rawQuery("Select  idPessoa,"
                + "  id_Cidade, CNPJCPF , Endereco , Numero , Bairro , cep"
                + " , Data_Nascimento ,"
                +  " Data_Cadastro , Complemento , Email , Razao_socialNome , Nome_fantasiaApelido , "
                +  " inscriEstadualRG , Data_ultima_compra , Valor_ultima_compra " +
                "        From Pessoa  where CNPJCPF like ?", new String[]{cpfCnpj});

        List<Pessoa> pessoas = new ArrayList<>();


        while (c.moveToNext()) {

            Pessoa pessoa = new Pessoa();
            pessoa.setIdpessoa(c.getInt(0));
            pessoa.setIdCidade(c.getInt(1));
            pessoa.setCnpjCpf(c.getString(2));
            pessoa.setEndereco(c.getString(3));
            pessoa.setNumero(c.getString(4));
            pessoa.setBairro(c.getString(5));
            pessoa.setCep(c.getString(6));
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

    public Integer CosultaClienteCNPJCPF(String CNPJCPF){
        Cursor consulta = getReadableDatabase().rawQuery("Select idPessoa from Pessoa " +
                        "where CNPJCPF like ? ",
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
    
    public String CosultaClienteNome(String id){
        Cursor consulta = getReadableDatabase().rawQuery("Select Razao_socialNome from Pessoa " +
                        "where idPessoa = ? ",
                new String[]{id});
        String nome = " " ;

        if (consulta != null) {
            try {
                if (consulta.moveToFirst()) {
                    return nome = consulta.getString(0);
                }
            } finally {
                consulta.close();
            }



        }

        return nome;


    }
}




