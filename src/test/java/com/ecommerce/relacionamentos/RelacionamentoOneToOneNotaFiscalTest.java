package com.ecommerce.relacionamentos;

import com.ecommerce.EntityManagerTest;
import com.ecommerce.model.NotaFiscal;
import com.ecommerce.model.PagamentoCartao;
import com.ecommerce.model.Pedido;
import com.ecommerce.model.StatusPagamento;
import org.junit.Assert;
import org.junit.Test;

public class RelacionamentoOneToOneNotaFiscalTest extends EntityManagerTest {

    @Test
    public void verificarRelacionamento(){
        Pedido pedido = entityManager.find(Pedido.class,1);

        NotaFiscal notaFiscal = new NotaFiscal();

        entityManager.getTransaction().begin();
        entityManager.persist(notaFiscal);
        entityManager.getTransaction().commit();

        entityManager.clear();

        Pedido pedidoVerificacao = entityManager.find(Pedido.class, pedido.getId());
        Assert.assertNotNull(pedidoVerificacao.getPagamento());
    }
}
