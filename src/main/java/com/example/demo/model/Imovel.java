package com.example.demo.model;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
public class Imovel {
	@jakarta.persistence.Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long ImovelId;
	private String Titulo;
	private String Tipo;
	private String Alocação;
	private Double Preço;
	private String Iptu;
	@ManyToMany
	private List<Imagens> Imagens;
	private String Condominio;
	private String Localizacao;
	private String Mobiliado;
	private String Descricao;
	private String Tamanho;
	private int Comodos;
	private int Quartos;
	private String Cep;
	private int NumClicks;
	@ManyToOne
	@JoinColumn(name="proprietario_id")
	private Proprietario proprietario;
	
	
	
	public void setProprietario(Proprietario proprietario) {
		this.proprietario = proprietario;
	  }
}
