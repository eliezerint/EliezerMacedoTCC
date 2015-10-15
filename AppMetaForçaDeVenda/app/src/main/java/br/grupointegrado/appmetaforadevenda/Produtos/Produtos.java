package br.grupointegrado.appmetaforadevenda.Produtos;

/**
 * Created by eli on 27/07/2015.
 */
public class Produtos {
    Integer Idproduto;
    Integer Idgrupopoduto;
    Integer IdUnidademedida;
    String descricao;
    Double quantidaestoque;
    Double vlunitario;


    public Produtos() {
    }

    public Produtos(Integer idgrupopoduto, Integer idUnidademedida, String descricao, Double quantidaestoque, Double vlunitario) {
        this.Idgrupopoduto = idgrupopoduto;
        this.IdUnidademedida = idUnidademedida;
        this.descricao = descricao;
        this.quantidaestoque = quantidaestoque;
        this.vlunitario = vlunitario;
    }

    public Produtos(Integer idproduto, Integer idgrupopoduto, Integer idUnidademedida, String descricao, Double quantidaestoque, Double vlunitario) {
        this.Idproduto = idproduto;
        this.Idgrupopoduto = idgrupopoduto;
        this.IdUnidademedida = idUnidademedida;
        this.descricao = descricao;
        this.quantidaestoque = quantidaestoque;
        this.vlunitario = vlunitario;
    }

    public Double getVlunitario() {
        return vlunitario;
    }

    public void setVlunitario(Double vlunitario) {
        this.vlunitario = vlunitario;
    }

    public Integer getIdproduto() {
        return Idproduto;
    }

    public void setIdproduto(Integer idproduto) {
        Idproduto = idproduto;
    }

    public Double getQuantidaestoque() {
        return quantidaestoque;
    }

    public void setQuantidaestoque(Double quantidaestoque) {
        this.quantidaestoque = quantidaestoque;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Integer getIdUnidademedida() {
        return IdUnidademedida;
    }

    public void setIdUnidademedida(Integer idUnidademedida) {
        IdUnidademedida = idUnidademedida;
    }

    public Integer getIdgrupopoduto() {
        return Idgrupopoduto;
    }

    public void setIdgrupopoduto(Integer idgrupopoduto) {
        Idgrupopoduto = idgrupopoduto;
    }
}
