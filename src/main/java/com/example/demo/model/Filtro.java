package com.example.demo.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Filtro {
	private int Area;
	private int PrecoMin;
	private int PrecoMax;
	private String TipoAloc;
	private String Mobilia;
	private boolean Relevancia;
	private String Localizacao;
}
