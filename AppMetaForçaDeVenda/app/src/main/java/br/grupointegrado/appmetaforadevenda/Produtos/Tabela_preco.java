package br.grupointegrado.appmetaforadevenda.Produtos;

/**
 * Created by eli on 27/07/2015.
 */
public class Tabela_preco {

    private Integer idTabelapreco;
    private Integer idProduto;
    private String Tp_venda;
    private Double preco1;
    private Double preco2;
    private Double preco3;
    private Double preco4;


    public Tabela_preco() {

    }

    public Tabela_preco(Integer idTabelapreco, Integer idProduto, Double preco1, Double preco2,
                        Double preco3, Double preco4, String Tp_venda) {
        this.idTabelapreco = idTabelapreco;
        this.idProduto = idProduto;
        this.preco1 = preco1;
        this.preco2 = preco2;
        this.preco3 = preco3;
        this.preco4 = preco4;
        this.Tp_venda = Tp_venda;
    }


    public Tabela_preco(Integer idTabelapreco, Integer idProduto, String tp_venda, Double preco1, Double preco2, Double preco3, Double preco4) {
        this.idTabelapreco = idTabelapreco;
        this.idProduto = idProduto;
        Tp_venda = tp_venda;
        this.preco1 = preco1;
        this.preco2 = preco2;
        this.preco3 = preco3;
        this.preco4 = preco4;
    }

    public Tabela_preco(Integer idTabelapreco, String tp_venda) {
        this.idTabelapreco = idTabelapreco;
        Tp_venda = tp_venda;
    }

    public Double getPreco1() {
        return preco1;
    }

    public void setPreco1(Double preco1) {
        this.preco1 = preco1;
    }

    public Double getPreco3() {
        return preco3;
    }

    public void setPreco3(Double preco3) {
        this.preco3 = preco3;
    }

    public Double getPreco2() {
        return preco2;
    }

    public void setPreco2(Double preco2) {
        this.preco2 = preco2;
    }

    public Double getPreco4() {
        return preco4;
    }

    public void setPreco4(Double preco4) {
        this.preco4 = preco4;
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

    @Override
    public String toString() {
        return this.Tp_venda;
    }
}