package com.example.demo.dto;

import java.util.List;

import com.example.demo.model.Proprietario;
import com.example.demo.model.Imagens;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ImovelDTO {
    private long ImovelId;
	private String Titulo;
	private String Tipo;
	private String Alocação;
	private Double Preço;
	private String Iptu;
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
	private Proprietario proprietario;
}
