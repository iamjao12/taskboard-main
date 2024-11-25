package com.taskboard.taskboard.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "membros")
public class Membro extends Pessoa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="mem_id", nullable = false)
    private String id;

    @Column(name="mem_cargo", nullable = false)
    private String cargo;
    
    @ManyToOne
    @JoinColumn(name = "projeto_id")  
    private Projeto projeto;
}

