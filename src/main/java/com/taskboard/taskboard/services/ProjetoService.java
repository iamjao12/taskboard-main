package com.taskboard.taskboard.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.taskboard.taskboard.models.Projeto;
import com.taskboard.taskboard.repository.ProjetoRepositorio;

@Service
public class ProjetoService {

    private final ProjetoRepositorio pr;

    public ProjetoService(ProjetoRepositorio pr) {
        this.pr = pr;
    }
    public Projeto novo(Projeto p) {
        return pr.save(p);
    }

    public Optional<Projeto> buscaPorId(String id) {
        return pr.findById(id); 
    }

    public List<Projeto> todos() {
        return pr.findAll();
    }

    public Projeto atualizar(Projeto p){
        return pr.save(p);
    }

    public void excluir(Projeto p) {
        pr.delete(p);  
    }
}
