package com.example.demo.service;

import com.example.demo.exceptions.RecursoNaoEncontradoException;
import com.example.demo.model.Produto;
import com.example.demo.repository.ProdutoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProdutoService {
    private final ProdutoRepository produtoRepository;

    public ProdutoService(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    public List<Produto> listarProdutos() {
        return produtoRepository.findAll();
    }

    public Produto buscarPorID(Long id) {
        return produtoRepository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Produto não encontrado com o ID: " + id));
    }

    public Produto salvarProduto(Produto produto) {
        return produtoRepository.save(produto);
    }

    public void deletarProduto(Long id) {
        if (!produtoRepository.existsById(id)){
            throw new RecursoNaoEncontradoException("Produto não encontrado com o ID: " + id);
        }
        produtoRepository.deleteById(id);
    }
}
