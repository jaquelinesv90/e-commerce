package com.ecommerce.relacionamentos;

import com.ecommerce.EntityManagerTest;
import com.ecommerce.model.Produto;
import org.junit.Test;

public class RelacionamentoManyToOneItemPedido extends EntityManagerTest {

    @Test
    public void verificarRelacionamento(){
        Produto produto = entityManager.find(Produto.class,1);
    }

}
