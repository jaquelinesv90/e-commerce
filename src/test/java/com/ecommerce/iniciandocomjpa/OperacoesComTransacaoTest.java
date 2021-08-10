package com.ecommerce.iniciandocomjpa;

import com.ecommerce.model.Produto;
import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;

public class OperacoesComTransacaoTest extends EntityManagerTest{

    @Test
    public void inserirOPrimeiroObjeto(){
        Produto produto = new Produto();

        produto.setId(2);
        produto.setNome("Camera Canon");
        produto.setDescricao("A melhor definicao");
        produto.setPreco(new BigDecimal(5000));

        //abrindo a transação
        entityManager.getTransaction().begin();
        //quando a gente chama o persist ele joga o objeto na memória para gerenciar
        entityManager.persist(produto);

        entityManager.getTransaction().commit();
        //limpando a memória
        entityManager.clear();

        Produto produtoVerificacao = entityManager.find(Produto.class,produto.getId());
        Assert.assertNotNull(produtoVerificacao);
    }

    @Test
    public void removerObjeto(){
        //é necessário buscar o objeto na base antes da exclusão,pois,
        //se somente criar uma instancia de objeto e tentar excluir vai
        //gerar um erro dizendo que o objeto não é detached.
        // Produto produto = new Produto();
        //logo depois que o método find for executado ele vai montar nossa
        // entidade jogar para a memória do entityManager(para poder ser gerenciado)
        Produto produto = entityManager.find(Produto.class,3);
        produto.setId(3);

        entityManager.getTransaction().begin();
        entityManager.remove(produto);
        entityManager.getTransaction().commit();
        // o entityManager remove da base de dados e da memória dele.
        // entityManager.clear(); Não é necessario na assercao para operacao de remocao
        Produto produtoVerificacao = entityManager.find(Produto.class,3);
        Assert.assertNull(produtoVerificacao);
    }

    @Test
    public void atualizarObjeto(){
        Produto produto = new Produto();
        produto.setId(1);
        produto.setNome("kindle paperwhite");
        produto.setDescricao("Conheça o novo Kindle");
        produto.setPreco(new BigDecimal(599));

        entityManager.getTransaction().begin();
        entityManager.merge();
        entityManager.getTransaction().commit();
    }

}