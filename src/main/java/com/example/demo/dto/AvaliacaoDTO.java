package com.example.demo.dto;

import java.util.Date;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class AvaliacaoDTO {
    private String avaliacaoId;
    private int nota;
    private String comentario;
    private Date dataAvaliacao;
}
