
package br.grupointegrado.appmetaforadevenda.Pedido;

import java.util.Date;

/**
 * Created by eli on 27/07/2015.
 */
public class Pedido {

    Integer Idpedido;
    Integer Idpessoa;
    Integer Idvendedor;
    Integer IdCondicaopag;
    Integer Idfilial;
    Date datapedido;
    Double total;

    public Pedido(Integer idpedido, Integer idpessoa, Integer idvendedor, Integer idCondicaopag, Integer idfilial, Date datapedido, Double total) {
        Idpedido = idpedido;
        Idpessoa = idpessoa;
        Idvendedor = idvendedor;
        IdCondicaopag = idCondicaopag;
        Idfilial = idfilial;
        this.datapedido = datapedido;
        this.total = total;
    }

    public Integer getIdpedido() {
        return Idpedido;
    }

    public void setIdpedido(Integer idpedido) {
        Idpedido = idpedido;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public Date getDatapedido() {
        return datapedido;
    }

    public void setDatapedido(Date datapedido) {
        this.datapedido = datapedido;
    }

    public Integer getIdfilial() {
        return Idfilial;
    }

    public void setIdfilial(Integer idfilial) {
        Idfilial = idfilial;
    }

    public Integer getIdCondicaopag() {
        return IdCondicaopag;
    }

    public void setIdCondicaopag(Integer idCondicaopag) {
        IdCondicaopag = idCondicaopag;
    }

    public Integer getIdvendedor() {
        return Idvendedor;
    }

    public void setIdvendedor(Integer idvendedor) {
        Idvendedor = idvendedor;
    }

    public Integer getIdpessoa() {
        return Idpessoa;
    }

    public void setIdpessoa(Integer idpessoa) {
        Idpessoa = idpessoa;
    }
}
