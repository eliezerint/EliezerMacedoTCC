package br.grupointegrado.appmetaforadevenda.Pessoa;

/**
 * Created by eli on 02/09/2015.
 */
public class Telefone {

    Integer idTelefone;
    Integer idPessoa;
    String Numero;
    String CPF;

    public Telefone( Integer idPessoa, String numero,String cpf) {
        this.idPessoa = idPessoa;
        this.Numero = numero;
        this.CPF = cpf;
    }

    public Telefone(){}

    public String getCPF() {
        return CPF;
    }

    public void setCPF(String CPF) {
        this.CPF = CPF;
    }

    public Integer getIdTelefone() {
        return idTelefone;
    }

    public void setIdTelefone(Integer idTelefone) {
        this.idTelefone = idTelefone;
    }

    public Integer getIdPessoa() {
        return idPessoa;
    }

    public void setIdPessoa(Integer idPessoa) {
        this.idPessoa = idPessoa;
    }

    public String getNumero() {
        return Numero;
    }

    public void setNumero(String numero) {
        Numero = numero;
    }
}
