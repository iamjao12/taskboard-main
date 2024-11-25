package com.taskboard.taskboard.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.taskboard.taskboard.models.Projeto;

@Repository
public interface ProjetoRepositorio extends JpaRepository< Projeto,String > {
    
}
