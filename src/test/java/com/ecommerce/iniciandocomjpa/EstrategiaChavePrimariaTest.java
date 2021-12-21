package com.ecommerce.iniciandocomjpa;

import com.ecommerce.model.Categoria;
import org.junit.Assert;

public class EstrategiaChavePrimariaTest extends EntityManagerTest{

    public void testarEstrategiaAuto(){
        Categoria categoria = new Categoria();
        categoria.setNome("Eletronicos");

        entityManager.getTransaction().begin();
        entityManager.persist(categoria);
        entityManager.getTransaction().commit();

        entityManager.clear();

        Categoria categoriaVerificacao = entityManager.find(Categoria.class,categoria.getId());
        Assert.assertNotNull(categoriaVerificacao);
    }

}
