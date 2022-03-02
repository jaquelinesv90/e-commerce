package com.ecommerce.iniciandocomjpa;

import com.ecommerce.EntityManagerTest;
import com.ecommerce.model.Cliente;
import org.junit.Test;

public class ClienteOperacaoTest extends EntityManagerTest {

    @Test
    public void inserir(){
        Cliente cliente = new Cliente();

        cliente.setId(3);
        cliente.setNome("Carl");

        entityManager.getTransaction().begin();
        entityManager.persist(cliente);
        entityManager.getTransaction().commit();

        entityManager.clear();
    }
    @Test
    public void atualizar(){
        Cliente cliente = new Cliente();

        cliente.setId(1);
        cliente.setNome("Joao");

        entityManager.getTransaction().begin();
        entityManager.merge(cliente);
        entityManager.getTransaction().commit();

        entityManager.clear();

    }
    @Test
    public void remover(){
        Cliente cliente = entityManager.find(Cliente.class,1);
        cliente.setId(1);

        entityManager.getTransaction().begin();
        entityManager.remove(cliente);
        entityManager.getTransaction().commit();
    }



}
