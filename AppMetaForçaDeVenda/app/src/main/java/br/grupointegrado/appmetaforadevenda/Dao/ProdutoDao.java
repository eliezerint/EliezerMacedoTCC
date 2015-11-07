package br.grupointegrado.appmetaforadevenda.Dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import java.util.ArrayList;
import java.util.List;

import br.grupointegrado.appmetaforadevenda.Produtos.Grupos_Produtos;
import br.grupointegrado.appmetaforadevenda.Produtos.Produtos;
import br.grupointegrado.appmetaforadevenda.Produtos.TabelaItenPreco;
import br.grupointegrado.appmetaforadevenda.Produtos.Tabelapreco;
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


        getWritableDatabase().insert("Produto", null, cv);

    }

    public List<Produtos> list() {
        Cursor c = getReadableDatabase().rawQuery("Select idProduto, grp.idGrupo_produto, um.idUnidadeMedida  ,p.Descricao , um.Sigla, " +
                "grp.Descricao from Produto p, UnidadeMedida um , GrupoProduto grp " +
                "where um.idUnidadeMedida = p.idUnidadeMedida and grp.idGrupo_produto = p.idGrupo_produto ", null);

        List<Produtos> produtos = new ArrayList<>();


        while (c.moveToNext()) {

            Produtos produto = new Produtos();
            produto.setIdproduto(c.getInt(0));
            produto.setIdgrupopoduto(c.getInt(1));
            produto.setIdUnidademedida(c.getInt(2));
            produto.setDescricao(c.getString(3));
            produto.setDescricaoUnidademedida(c.getString(4));
            produto.setDescricaoGrupoProduto(c.getString(5));


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
    public void savePreco(Tabelapreco tabela_preco){
        ContentValues cv = new ContentValues();
        cv.put("idProduto", tabela_preco.getIdProduto());
        cv.put("descricao", tabela_preco.getDescricao());

        getWritableDatabase().insert("TabelaPreco", null, cv);

    }


    public List<Tabelapreco> listPrecoVEnda(String id) {
        Cursor c = getReadableDatabase().rawQuery("Select idTabelapreco, idProduto, descricao" +
                " from TabelaPreco where  idProduto = ? ", new String []{id});

        List<Tabelapreco> tabela_precos = new ArrayList<>();


        while (c.moveToNext()) {

            Tabelapreco tabela_preco = new Tabelapreco();
            tabela_preco.setIdTabelapreco(c.getInt(0));
            tabela_preco.setIdProduto(c.getInt(1));
            tabela_preco.setDescricao(c.getString(2));

            tabela_precos.add(tabela_preco);

        }
        c.close();
        return tabela_precos;

    }

    //Tabela de Preço iten
    public void saveItemtabelaPreco(TabelaItenPreco tabela_preco){
        ContentValues cv = new ContentValues();
        cv.put("idTabelapreco", tabela_preco.getIdtabelapreco());
        cv.put("descricao", tabela_preco.getDescricao());
        cv.put("vlunitario", tabela_preco.getVlunitario());

        getWritableDatabase().insert("TabelaItenPreco", null, cv);

    }


    public List<TabelaItenPreco> listPrecoVEndaIten(String id) {
        Cursor c = getReadableDatabase().rawQuery("Select idTabelaItenpreco, idTabelapreco, descricao, vlunitario" +
                " from TabelaItenPreco where  idTabelapreco = ? ", new String []{id});

        List<TabelaItenPreco> tabela_precos = new ArrayList<>();


        while (c.moveToNext()) {

            TabelaItenPreco  itentabela_preco = new TabelaItenPreco();
            itentabela_preco.setIdtabelaItenpreco(c.getInt(0));
            itentabela_preco.setIdtabelapreco(c.getInt(1));
            itentabela_preco.setDescricao(c.getString(2));
            itentabela_preco.setVlunitario(Double.valueOf(c.getString(3)));

            tabela_precos.add(itentabela_preco);

        }
        c.close();

        return tabela_precos;

    }





}
