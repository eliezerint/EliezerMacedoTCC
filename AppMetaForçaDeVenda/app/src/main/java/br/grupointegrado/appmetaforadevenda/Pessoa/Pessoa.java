package br.grupointegrado.appmetaforadevenda.Pessoa;

import java.util.Date;

/**
 * Created by eli on 23/07/2015.
 */
public class Pessoa {

    Integer idpessoa ;
    Integer idCidade ;
    String CnpjCpf;
    String rua;
    String bairro;
    String Cidade;
    String telefone;
    Date dataCadastro;
    String complemento;
    String Email;
    String RazaoSocialNome;
    String fantasiaApelido;
    String inscriEstadualRG;
    Date dataUltimacompra;
    Double valorUltimacompra;


    public Pessoa( Integer idCidade, String cnpjCpf, String rua, String bairro, String cidade, Date dataCadastro,
                  String telefone, String complemento, String email, String razaoSocialNome, String fantasiaApelido,
                  Date dataUltimacompra, String inscriEstadualRG, Double valorUltimacompra) {

        this.idCidade = idCidade;
        CnpjCpf = cnpjCpf;
        this.rua = rua;
        this.bairro = bairro;
        Cidade = cidade;
        this.dataCadastro = dataCadastro;
        this.telefone = telefone;
        this.complemento = complemento;
        Email = email;
        RazaoSocialNome = razaoSocialNome;
        this.fantasiaApelido = fantasiaApelido;
        this.dataUltimacompra = dataUltimacompra;
        this.inscriEstadualRG = inscriEstadualRG;
        this.valorUltimacompra = valorUltimacompra;
    }
    public Pessoa( Integer idpessoa,Integer idCidade, String cnpjCpf, String rua, String bairro, String cidade, Date dataCadastro,
                   String telefone, String complemento, String email, String razaoSocialNome, String fantasiaApelido,
                   Date dataUltimacompra, String inscriEstadualRG, Double valorUltimacompra) {
        this.idpessoa = idpessoa;
        this.idCidade = idCidade;
        CnpjCpf = cnpjCpf;
        this.rua = rua;
        this.bairro = bairro;
        Cidade = cidade;
        this.dataCadastro = dataCadastro;
        this.telefone = telefone;
        this.complemento = complemento;
        Email = email;
        RazaoSocialNome = razaoSocialNome;
        this.fantasiaApelido = fantasiaApelido;
        this.dataUltimacompra = dataUltimacompra;
        this.inscriEstadualRG = inscriEstadualRG;
        this.valorUltimacompra = valorUltimacompra;
    }

    public Pessoa (){}



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

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
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
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
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
