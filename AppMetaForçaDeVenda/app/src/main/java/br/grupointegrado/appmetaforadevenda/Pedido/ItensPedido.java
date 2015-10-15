package br.grupointegrado.appmetaforadevenda.Pedido;

import java.io.Serializable;

/**
 * Created by eli on 27/07/2015.
 */
public class ItensPedido implements Serializable{

    Integer IdPedido;
    Integer IdProduto;
    String produto;
    Double desconto;
    Double quantidade;
    Double vlunitario;
    Double total;


    public ItensPedido(String produto, Double desconto, Double quantidade, Double vlunitario, Double total) {
        this.produto = produto;
        this.desconto = desconto;
        this.quantidade = quantidade;
        this.vlunitario = vlunitario;
        this.total = total;
    }
    public ItensPedido(Integer idProduto, String produto, Double vlunitario, Double quantidade, Double desconto, Double total) {
        this.IdProduto = idProduto;
        this.produto = produto;
        this.desconto = desconto;
        this.quantidade = quantidade;
        this.vlunitario = vlunitario;
        this.total = total;
    }

    public ItensPedido() {

    }

    public Integer getIdPedido() {
        return IdPedido;
    }

    public void setIdPedido(Integer idPedido) {
        IdPedido = idPedido;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public Double getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Double quantidade) {
        this.quantidade = quantidade;
    }

    public Double getDesconto() {
        return desconto;
    }

    public void setDesconto(Double desconto) {
        this.desconto = desconto;
    }

    public Integer getIdProduto() {
        return IdProduto;
    }

    public void setIdProduto(Integer idProduto) {
        IdProduto = idProduto;
    }

    public String getProduto() {
        return produto;
    }

    public void setProduto(String produto) {
        this.produto = produto;
    }

    public Double getVlunitario() {
        return vlunitario;
    }

    public void setVlunitario(Double vlunitario) {
        this.vlunitario = vlunitario;
    }
}
