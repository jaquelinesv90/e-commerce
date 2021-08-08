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

}
