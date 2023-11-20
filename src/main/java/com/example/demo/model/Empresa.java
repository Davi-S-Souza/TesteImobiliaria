package com.example.demo.model;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Empresa{
	@Id
	private String cnpj;
	private String nome;
	private String endereço;
	private String telefone;
	private String email;
	private byte[] foto;
	private int quantidadeImoveis;
	@OneToMany(mappedBy = "empresa", cascade = CascadeType.ALL)
	private List<Funcionarios> funcionarios;
}
