package com.ecommerce.conhecendoentitymanager;

import com.ecommerce.EntityManagerTest;
import com.ecommerce.model.Pedido;
import com.ecommerce.model.StatusPedido;
import org.junit.Test;

public class FlushTest extends EntityManagerTest {

    @Test(expected = Exception.class)
    public void chamarFlush(){
        try{
           entityManager.getTransaction().begin();

           Pedido pedido = entityManager.find(Pedido.class, 1);
           pedido.setStatus(StatusPedido.PAGO);

           entityManager.flush();

           if(pedido.getPagamento() == null){
               throw new RuntimeException("Pedido ainda não foi pago");
           }
           // Uma consulta obriga o JPA a sincronizar o que ele tem na memória (sem usar o flush explicitamente).
            /*
            Pedido pedidoPago = entityManager
                    .createQuery("select p from Pedido where p.id = 1",Pedido.class)
                    .getSingleResult();
             */
           entityManager.getTransaction().commit();
        }catch(Exception e){
            entityManager.getTransaction().rollback();
            throw e;
        }
    }
}
