package br.grupointegrado.appmetaforadevenda.Vendedor;

/**
 * Created by eli on 27/07/2015.
 */
public class Vendedor {

    Integer Idvendedor;
    String Nome;

    public Vendedor(String nome) {
        Nome = nome;
    }

    public  Vendedor(){

    }

    public Integer getIdvendedor() {
        return Idvendedor;
    }

    public void setIdvendedor(Integer idvendedor) {
        Idvendedor = idvendedor;
    }

    public String getNome() {
        return Nome;
    }

    public void setNome(String nome) {
        Nome = nome;
    }
}
