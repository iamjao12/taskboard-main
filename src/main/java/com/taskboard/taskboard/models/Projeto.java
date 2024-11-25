package com.taskboard.taskboard.models;

import java.sql.Date;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

@Entity
@Table(name="projetos")
public class Projeto {
    @Id
    @Column(name="pro_codigo",nullable = false)
    private String id;

    @Column(name="pro_nome",nullable = false,length = 100)
    private String nome;

    @Column(name="pro_data_inicio")
    private Date dataInicio;

    @Column(name="pro_data_fim")
    private Date dataFim;

    @Column(name="pro_status",length = 30)
    private String status;

    @OneToMany(mappedBy = "projeto") 
    private List<Gerente> gerentes;

    @OneToMany(mappedBy = "projeto") 
    private List<Membro> membros;
}
