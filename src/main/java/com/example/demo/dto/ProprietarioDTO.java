package com.example.demo.dto;

import java.util.Date;
import java.util.List;
import com.example.demo.model.Avaliacao;
import com.example.demo.model.Imovel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProprietarioDTO {
    private String userId;
	private String usuario;
	private String email;
	private String telefone;
	private byte[] foto;
    private long ProprietarioId;
	private String Nome;
	private String Cpf;
	private String Endereço;
	private int MoveisDisp;
	private int Credibilidade;
	private Date Data;
	private List<Imovel> imoveis;
	private List<Avaliacao> avaliacoes;
}
