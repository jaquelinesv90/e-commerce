package com.ecommerce.conhecendoentitymanager;

import com.ecommerce.EntityManagerTest;
import com.ecommerce.model.Produto;
import org.junit.Test;

public class CachePrimeiroNivelTest extends EntityManagerTest {

    @Test
    public void verificarCache(){
        Produto produto = entityManager.find(Produto.class,1);
        System.out.println(produto.getNome());

        System.out.println("---------");

        //entityManager.close();
        //entityManager = entityManagerFactory.createEntityManager();

        Produto produtoResgatado = entityManager.find(Produto.class, produto.getId());
        System.out.println(produtoResgatado.getNome());
    }
}
