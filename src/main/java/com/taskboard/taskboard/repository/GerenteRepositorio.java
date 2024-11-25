package com.taskboard.taskboard.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.taskboard.taskboard.models.Gerente;

@Repository
public interface GerenteRepositorio extends JpaRepository< Gerente,String > {
    List<Gerente> findByProjetoId(String idProjeto);
}
