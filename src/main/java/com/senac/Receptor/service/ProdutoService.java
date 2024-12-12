package com.senac.Receptor.service;

import com.senac.Receptor.entities.Produto;
import com.senac.Receptor.repositories.ProdutoRepository;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;

public class ProdutoService {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    private ProdutoRepository produtoRepository;

    public ProdutoService(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    @RabbitListener(queues = "fila-get-produto")
    private void subscriber(String opcao){
        System.out.println(produtoRepository.findAll());

    }
    @RabbitListener(queues = "fila-post-produto")
    private void salvarProduto(Produto produto) {
        this.produtoRepository.save(produto);
    }



}

