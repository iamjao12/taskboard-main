package com.taskboard.taskboard.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.taskboard.taskboard.models.Gerente;
import com.taskboard.taskboard.repository.GerenteRepositorio;

@Service
public class GerenteService {

    private final GerenteRepositorio gr;

    public GerenteService(GerenteRepositorio gr) {
        this.gr = gr;
    }
    public Gerente novo(Gerente g) {
        return gr.save(g);
    }

    public Optional<Gerente> buscaPorId(String id) {
        return gr.findById(id); 
    }

    public List<Gerente> findByProjetoId(String id){
        return gr.findByProjetoId(id);
    }

    public List<Gerente> todos() {
        return gr.findAll();
    }

    public Gerente atualizar(Gerente g){
        return gr.save(g);
    }

    public void excluir(Gerente g) {
        gr.delete(g);  
    }
}
