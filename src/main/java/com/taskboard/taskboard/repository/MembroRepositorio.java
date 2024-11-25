package com.taskboard.taskboard.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.taskboard.taskboard.models.Membro;

@Repository
public interface MembroRepositorio extends JpaRepository< Membro,String > {
    List<Membro> findByProjetoId(String idProjeto);
}
