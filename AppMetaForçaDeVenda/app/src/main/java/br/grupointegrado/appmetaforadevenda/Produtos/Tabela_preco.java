package br.grupointegrado.appmetaforadevenda.Produtos;

/**
 * Created by eli on 27/07/2015.
 */
public class Tabela_preco {

    Integer idTabelapreco;
    Integer idProduto;
    String  Tp_venda;
    Double  precominimo;
    Double  precomaximo;


    public Tabela_preco() {

    }

    public Tabela_preco(Integer idTabelapreco, Integer idProduto, Double precominimo, Double precomaximo, String Tp_venda) {
        this.idTabelapreco = idTabelapreco;
        this.idProduto = idProduto;
        this.precominimo = precominimo;
        this.precomaximo = precomaximo;
        this.Tp_venda = Tp_venda;
    }

    public Tabela_preco(Integer idProduto, String tp_venda, Double precominimo, Double precomaximo) {
        this.idProduto = idProduto;
        Tp_venda = tp_venda;
        this.precominimo = precominimo;
        this.precomaximo = precomaximo;
    }

    public Integer getIdTabelapreco() {
        return idTabelapreco;
    }

    public void setIdTabelapreco(Integer idTabelapreco) {
        this.idTabelapreco = idTabelapreco;
    }

    public Integer getIdProduto() {
        return idProduto;
    }

    public void setIdProduto(Integer idProduto) {
        this.idProduto = idProduto;
    }

    public String getTp_venda() {
        return Tp_venda;
    }

    public void setTp_venda(String tp_venda) {
        Tp_venda = tp_venda;
    }

    public Double getPrecomaximo() {
        return precomaximo;
    }

    public void setPrecomaximo(Double precomaximo) {
        this.precomaximo = precomaximo;
    }

    public Double getPrecominimo() {
        return precominimo;
    }

    public void setPrecominimo(Double precominimo) {
        this.precominimo = precominimo;
    }
}
