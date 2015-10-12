package br.grupointegrado.appmetaforadevenda.Pessoa;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by eli on 23/07/2015.
 */
public class Pessoa implements Serializable {

    Integer idpessoa ;
    Integer idCidade ;
    String CnpjCpf;
    String Numero;
    String bairro;
    String complemento;
    String Cidade;
    String telefone;
    Date dataCadastro;
    String Endereco;
    String Email;
    String RazaoSocialNome;
    String fantasiaApelido;
    String inscriEstadualRG;
    Date dataUltimacompra;
    Double valorUltimacompra = 0.00;
    Date dataNascimento;



    public Pessoa( Integer idCidade, String cnpjCpf,String razaoSocialNome, String fantasiaApelido,
                   String inscriEstadualRG , String endereco ,String Numero, String bairro,String complemento, String cidade,
                  String Email,Date dataUltimacompra , double valorUltimacompra,Date dataCadastro) {

        this.idCidade = idCidade;
        this.CnpjCpf = cnpjCpf;
        this.Numero = Numero;
        this.bairro = bairro;
        this.complemento = complemento;
        this.Cidade = cidade;
        this.dataCadastro = dataCadastro;
        this.Endereco = endereco;
        this.Email = Email;
        this.RazaoSocialNome = razaoSocialNome;
        this.fantasiaApelido = fantasiaApelido;
        this.dataUltimacompra = dataUltimacompra;
        this.inscriEstadualRG = inscriEstadualRG;
        this.valorUltimacompra = valorUltimacompra;
    }




    public Pessoa (){}

    public Pessoa(Integer idpessoa,Integer idCidade, String cnpjCpf,String razaoSocialNome, String fantasiaApelido,
                  String inscriEstadualRG , String endereco ,String Numero, String bairro,String complemento, String cidade,
                  Date dataNascimento,String Email,Date dataUltimacompra , double valorUltimacompra,Date dataCadastro) {
        this.idpessoa = idpessoa;
        this.idCidade = idCidade;
        this.CnpjCpf = cnpjCpf;
        this.Numero = Numero;
        this.bairro = bairro;
        this.complemento = complemento;
        this.Cidade = cidade;
        this.dataCadastro = dataCadastro;
        this.Endereco = endereco;
        this.Email = Email;
        this.RazaoSocialNome = razaoSocialNome;
        this.fantasiaApelido = fantasiaApelido;
        this.dataNascimento = dataNascimento;
        this.dataUltimacompra = dataUltimacompra;
        this.inscriEstadualRG = inscriEstadualRG;
        this.valorUltimacompra = valorUltimacompra;



    }



    public Date getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public String getNumero() {
        return Numero;
    }

    public void setNumero(String numero) {
        Numero = numero;
    }

    public String getCnpjCpf() {
        return CnpjCpf;
    }

    public String getCidade() {
        return Cidade;
    }

    public void setCidade(String cidade) {
        Cidade = cidade;
    }

    public Integer getIdpessoa() {
        return idpessoa;
    }

    public void setIdpessoa(Integer idpessoa) {
        this.idpessoa = idpessoa;
    }

    public Integer getIdCidade() {
        return idCidade;
    }

    public void setIdCidade(Integer idCidade) {
        this.idCidade = idCidade;
    }

    public Double getValorUltimacompra() {
        return valorUltimacompra;
    }

    public void setValorUltimacompra(Double valorUltimacompra) {
        this.valorUltimacompra = valorUltimacompra;
    }

    public String getRazaoSocialNome() {
        return RazaoSocialNome;
    }

    public void setRazaoSocialNome(String razaoSocialNome) {
        RazaoSocialNome = razaoSocialNome;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getEndereco() {
        return Endereco;
    }

    public void setEndereco(String endereco) {
        this.Endereco = endereco;
    }

    public Date getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(Date dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getRua() {
        return Numero;
    }

    public void setRua(String rua) {
        this.Numero = rua;
    }

    public String getCnpjCpf(String string) {
        return CnpjCpf;
    }

    public void setCnpjCpf(String cnpjCpf) {
        CnpjCpf = cnpjCpf;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getFantasiaApelido() {
        return fantasiaApelido;
    }

    public void setFantasiaApelido(String fantasiaApelido) {
        this.fantasiaApelido = fantasiaApelido;
    }

    public String getInscriEstadualRG() {
        return inscriEstadualRG;
    }

    public void setInscriEstadualRG(String inscriEstadualRG) {
        this.inscriEstadualRG = inscriEstadualRG;
    }

    public Date getDataUltimacompra() {
        return dataUltimacompra;
    }

    public void setDataUltimacompra(Date dataUltimacompra) {
        this.dataUltimacompra = dataUltimacompra;
    }







}
