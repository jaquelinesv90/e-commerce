package com.ecommerce.conhecendoentitymanager;

import com.ecommerce.EntityManagerTest;
import org.junit.Test;

public class GerenciandoTransacoesTest extends EntityManagerTest {

    // Transação é o período de tempo entre o begin e o commit
    @Test
    public void abrirFecharCancelarTransacao(){
        entityManager.getTransaction().begin();

        entityManager.getTransaction().commit();
    }
}
