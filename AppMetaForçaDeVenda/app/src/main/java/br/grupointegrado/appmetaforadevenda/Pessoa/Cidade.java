package br.grupointegrado.appmetaforadevenda.Pessoa;

/**
 * Created by eli on 23/07/2015.
 */
public class Cidade {

    Integer idcidade;
    String idestado;
    String descricao;
    String ibge;
    String Pais;

    public Cidade(Integer idcidade, String ibge, String pais, String descricao, String idestado) {
        this.idcidade = idcidade;
        this.ibge = ibge;
        Pais = pais;
        this.descricao = descricao;
        this.idestado = idestado;
    }

    public Integer getId() {
        return idcidade;
    }

    public void setId(Integer id) {
        this.idcidade = id;
    }

    public String getIdestado() {
        return idestado;
    }

    public void setIdestado(String idestado) {
        this.idestado = idestado;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getIbge() {
        return ibge;
    }

    public void setIbge(String ibge) {
        this.ibge = ibge;
    }

    public String getPais() {
        return Pais;
    }

    public void setPais(String pais) {
        Pais = pais;
    }
}
