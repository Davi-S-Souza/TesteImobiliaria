package com.example.demo.model;

import java.util.Date;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@DiscriminatorValue("P") 
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Proprietario extends Usuario{
	@jakarta.persistence.Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="proprietario_id")
	private long ProprietarioId;
	private String Nome;
	private String Cpf;
	private String Endereço;
	private int MoveisDisp;
	private int Credibilidade;
	private Date Data;
	@OneToMany(mappedBy="proprietario")
	private List<Imovel> imoveis;
	@OneToMany(mappedBy = "proprietario")
	private List<Avaliacao> avaliacoes;
}




//- Proprietario:
//	  - Id*
//	  - Nome
//	  - cpf
//	  - Endereço
//	  - telefone
//	  - email
//	  - foto
//	  - Quantidade de imoveis disponiveis
//	  - imovel   Imovel
//	  - credibilidade
//	  - mensagem   Msg
//	  - data de cadastro  