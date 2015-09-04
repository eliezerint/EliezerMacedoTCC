package br.grupointegrado.appmetaforadevenda.Produtos;

/**
 * Created by eli on 27/07/2015.
 */
public class ListaPreco {

    Integer IdlistaPreco;
    Integer IdProduto;
    String descricao;

    public Integer getIdlistaPreco() {
        return IdlistaPreco;
    }

    public void setIdlistaPreco(Integer idlistaPreco) {
        IdlistaPreco = idlistaPreco;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Integer getIdProduto() {
        return IdProduto;
    }

    public void setIdProduto(Integer idProduto) {
        IdProduto = idProduto;
    }
}
