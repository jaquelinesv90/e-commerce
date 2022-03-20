package com.ecommerce.relacionamentos;

import com.ecommerce.EntityManagerTest;
import com.ecommerce.model.Pedido;
import org.junit.Assert;
import org.junit.Test;

// Uma entidade referenciada nada mais é que uma entidade pai com vários
// filhos, primeiro deve-se remover os filhos depois excluir o registro pai
// pode ser utilizado operações em cascata
public class RemovendoEntidadesReferenciadasTest extends EntityManagerTest {

    @Test
    public void removerEntidadeRelacionada(){
        Pedido pedido = entityManager.find(Pedido.class,1);

        Assert.assertFalse(pedido.getItens().isEmpty());

        entityManager.getTransaction().begin();
        pedido.getItens().forEach(i->entityManager.remove(i));
        entityManager.remove(pedido);
        entityManager.getTransaction().commit();

        entityManager.clear();
    }
}
