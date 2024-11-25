package com.taskboard.taskboard.models;
import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;

@MappedSuperclass
@Getter
@Setter
public abstract class Pessoa implements Serializable {

    @Column(nullable = false)
    private String nome;
    @Column(nullable = false)
    private String tel;
    @Column(nullable = false)
    private String email;

}

