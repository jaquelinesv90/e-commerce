package com.ecommerce.util;

import com.ecommerce.model.Produto;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.EntityManagerFactory;

// Aqui é instanciada a interface do EntityManager
// é através da instancia do EntityManager que a gente faz todas as operações com JPA
public class IniciarUnidadeDePersistencia {

    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory =  Persistence
                .createEntityManagerFactory("Ecommerce-PU");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        Produto produto = entityManager.find(Produto.class, 1);
        System.out.println(produto.getNome());

        //fechando o entityManager
        entityManager.close();
        entityManagerFactory.close();
    }
}
