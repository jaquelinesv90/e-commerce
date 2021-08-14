package com.ecommerce.iniciandocomjpa;

import com.ecommerce.model.Produto;
import org.junit.*;

//classe generica para testes
public class ConsultandoRegistrosTest extends EntityManagerTest{

    @Test
    public void buscarPorIdentificador(){
        Produto produto = entityManager.find(Produto.class, 1);
        Assert.assertNotNull(produto);
        Assert.assertEquals("kindle", produto.getNome());
    }

    @Test
    public void atualizarReferencia(){
        Produto produto = entityManager.find(Produto.class,1);
        produto.setNome("microfone Samson");

        entityManager.refresh(produto);

        Assert.assertEquals("kindle", produto.getNome());
    }
}
