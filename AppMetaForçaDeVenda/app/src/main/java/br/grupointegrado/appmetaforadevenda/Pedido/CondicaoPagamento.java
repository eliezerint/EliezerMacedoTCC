package br.grupointegrado.appmetaforadevenda.Pedido;

/**
 * Created by eli on 27/06/2015.
 */
public class CondicaoPagamento {

    Integer Idcodicaopagamento;
    String descricao;
    Double quantidade;

    public Integer getIdcodicaopagamento() {
        return Idcodicaopagamento;
    }

    public void setIdcodicaopagamento(Integer idcodicaopagamento) {
        Idcodicaopagamento = idcodicaopagamento;
    }

    public Double getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Double quantidade) {
        this.quantidade = quantidade;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
