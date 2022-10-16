package com.ecommerce.conhecendoentitymanager;

import com.ecommerce.EntityManagerTest;
import com.ecommerce.model.Cliente;
import com.ecommerce.model.Pedido;
import com.ecommerce.model.StatusPedido;
import org.junit.Assert;
import org.junit.Test;

public class CallBacksTest  extends EntityManagerTest {

    // a gente aciona callback quando aciona o banco de dados, neste caso ao persistir ou atualizar
    // nos métodos na classe Pedido
    @Test
    public void acionarCallBacks(){
        Cliente cliente = entityManager.find(Cliente.class,1);

        Pedido pedido = new Pedido();
        pedido.setCliente(cliente);
        pedido.setStatus(StatusPedido.AGUARDANDO);

        entityManager.getTransaction().begin();

        entityManager.persist(pedido);
        entityManager.flush();

        pedido.setStatus(StatusPedido.PAGO);
        entityManager.getTransaction().commit();

        entityManager.clear();

        Pedido pedidoVerificacao = entityManager.find(Pedido.class, pedido.getId());
        Assert.assertNotNull(pedidoVerificacao.getDataCriacao());
    }
}
