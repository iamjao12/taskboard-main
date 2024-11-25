package com.taskboard.taskboard.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.taskboard.taskboard.models.Membro;
import com.taskboard.taskboard.repository.MembroRepositorio;

@Service
public class MembroService {

    private final MembroRepositorio mr;

    public MembroService(MembroRepositorio mr) {
        this.mr = mr;
    }
    public Membro novo(Membro m) {
        return mr.save(m);
    }

    public Optional<Membro> buscaPorId(String id) {
        return mr.findById(id); 
    }

    public List<Membro> findByProjetoId(String id){
        return mr.findByProjetoId(id);
    }

    public List<Membro> todos() {
        return mr.findAll();
    }

    public Membro atualizar(Membro m){
        return mr.save(m);
    }

    public void excluir(Membro m) {
        mr.delete(m);  
    }
}
