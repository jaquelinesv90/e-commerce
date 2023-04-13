package com.ecommerce.mapeamentoavancado;

import com.ecommerce.EntityManagerTest;
import com.ecommerce.model.Cliente;
import org.junit.Assert;
import org.junit.Test;

public class PropriedadesTransientTest extends EntityManagerTest {

    @Test
    public void validarPrimeiroNome(){
        Cliente cliente = entityManager.find(Cliente.class, 1);

        Assert.assertEquals("Fernando", cliente.getNome());
    }
}
