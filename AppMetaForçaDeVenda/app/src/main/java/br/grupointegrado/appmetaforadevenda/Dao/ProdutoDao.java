package br.grupointegrado.appmetaforadevenda.Dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import java.util.ArrayList;
import java.util.List;

import br.grupointegrado.appmetaforadevenda.Produtos.Grupos_Produtos;
import br.grupointegrado.appmetaforadevenda.Produtos.Produtos;
import br.grupointegrado.appmetaforadevenda.Produtos.Tabela_preco;
import br.grupointegrado.appmetaforadevenda.Produtos.UnidadeMedida;

/**
 * Created by eli on 12/10/2015.
 */
public class ProdutoDao extends AppDao {
    public ProdutoDao(Context context) {
        super(context);
    }





    public void saveProduto(Produtos produto){
        ContentValues cv = new ContentValues();
        cv.put("idGrupo_produto", produto.getIdgrupopoduto());
        cv.put("idUnidadeMedida", produto.getIdUnidademedida());
        cv.put("Descricao", produto.getDescricao());
        cv.put("Quantidade_estoque", produto.getQuantidaestoque());
        cv.put("Valor_Unitario", produto.getVlunitario());


        getWritableDatabase().insert("Produto", null, cv);

    }

    public List<Produtos> list() {
        Cursor c = getReadableDatabase().rawQuery("Select idProduto, idGrupo_produto, idUnidadeMedida,Descricao ,Quantidade_estoque,Valor_Unitario from Produto ", null);

        List<Produtos> produtos = new ArrayList<>();


        while (c.moveToNext()) {

            Produtos produto = new Produtos();
            produto.setIdproduto(c.getInt(0));
            produto.setIdgrupopoduto(c.getInt(1));
            produto.setIdUnidademedida(c.getInt(2));
            produto.setDescricao(c.getString(3));
            produto.setQuantidaestoque(c.getDouble(4));
            produto.setVlunitario(c.getDouble(5));

            produtos.add(produto);

        }
        c.close();
        return produtos;

    }

    //Tabela de Grupos de Produto
    public void saveGrupoProduto(Grupos_Produtos grupos_produtos){
        ContentValues cv = new ContentValues();
        cv.put("Descricao", grupos_produtos.getDescricao());

        getWritableDatabase().insert("GrupoProduto", null, cv);

    }

    public List<Grupos_Produtos> listGruposProduto() {
        Cursor c = getReadableDatabase().rawQuery("Select idProduto, Descricao from GrupoProduto ", null);

        List<Grupos_Produtos> grupos_produtos = new ArrayList<>();


        while (c.moveToNext()) {

            Grupos_Produtos grupos_produto = new Grupos_Produtos();
            grupos_produto.setIdproduto(c.getInt(0));
            grupos_produto.setDescricao(c.getString(1));

            grupos_produtos.add(grupos_produto);

        }
        c.close();
        return grupos_produtos;

    }

    //Tabela de Unidade de Medida
    public void saveUnidadeMedida(UnidadeMedida unidademedida){
        ContentValues cv = new ContentValues();
        cv.put("Descricao", unidademedida.getDescricao());
        cv.put("Sigla", unidademedida.getSigla());

        getWritableDatabase().insert("UnidadeMedida", null, cv);

    }

    public List<UnidadeMedida> listUnidadeMedida() {
        Cursor c = getReadableDatabase().rawQuery("Select idUnidadeMedida, Descricao, Sigla, from UnidadeMedida ", null);

        List<UnidadeMedida> unidademedidas = new ArrayList<>();


        while (c.moveToNext()) {

            UnidadeMedida unidademedida = new UnidadeMedida();
            unidademedida.setIdunidademedida(c.getInt(0));
            unidademedida.setDescricao(c.getString(1));
            unidademedida.setSigla(c.getString(2));

            unidademedidas.add(unidademedida);

        }
        c.close();
        return unidademedidas;

    }

    //Tabela de Preço
    public void savePreco(Tabela_preco tabela_preco){
        ContentValues cv = new ContentValues();
        cv.put("idProduto", tabela_preco.getIdProduto());
        cv.put("Tipo_venda", tabela_preco.getTp_venda());
        cv.put("preco_minimo", tabela_preco.getPrecominimo());
        cv.put("preco_maximo", tabela_preco.getPrecomaximo());

        getWritableDatabase().insert("TabelaPreco", null, cv);

    }


    public List<Tabela_preco> listPrecoVEnda() {
        Cursor c = getReadableDatabase().rawQuery("Select idTabela_preco, idProduto, Tipo_venda, preco_minimo, preco_maximo from UnidadeMedida ", null);

        List<Tabela_preco> tabela_precos = new ArrayList<>();


        while (c.moveToNext()) {

            Tabela_preco tabela_preco = new Tabela_preco();
            tabela_preco.setIdTabelapreco(c.getInt(0));
            tabela_preco.setIdProduto(c.getInt(1));
            tabela_preco.setTp_venda(c.getString(2));
            tabela_preco.setPrecominimo(c.getDouble(3));
            tabela_preco.setPrecomaximo(c.getDouble(4));

            tabela_precos.add(tabela_preco);

        }
        c.close();
        return tabela_precos;

    }



}
