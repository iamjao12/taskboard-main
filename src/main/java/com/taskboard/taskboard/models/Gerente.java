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
@Table(name = "gerentes")
public class Gerente extends Pessoa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="ger_id",nullable = false)
    private String id;

    @Column(name="ger_cargo", nullable = false)
    private String cargo;

    @Column(name="ger_nivel_acesso", nullable = false)
    private int nivel_acesso;

    @ManyToOne
    @JoinColumn(name = "projeto_id")  
    private Projeto projeto;
}

