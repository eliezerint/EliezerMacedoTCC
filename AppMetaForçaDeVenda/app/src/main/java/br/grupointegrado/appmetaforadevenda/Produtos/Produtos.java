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
