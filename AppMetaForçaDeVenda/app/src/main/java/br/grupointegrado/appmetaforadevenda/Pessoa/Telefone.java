package br.grupointegrado.appmetaforadevenda.Pessoa;

/**
 * Created by eli on 02/09/2015.
 */
public class Telefone {

    Integer idTelefone;
    Integer idPessoa;
    String Numero;

    public Telefone(Integer idTelefone, Integer idPessoa, String numero) {
        this.idTelefone = idTelefone;
        this.idPessoa = idPessoa;
        Numero = numero;
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
