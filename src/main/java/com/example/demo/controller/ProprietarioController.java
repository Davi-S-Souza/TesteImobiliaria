package com.example.demo.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.EmpresaDTO;
import com.example.demo.dto.FuncionariosDTO;
import com.example.demo.dto.ImovelDTO;
import com.example.demo.dto.ProprietarioDTO;
import com.example.demo.model.Empresa;
import com.example.demo.model.Filtro;
import com.example.demo.model.Proprietario;
import com.example.demo.service.ProprietarioService;

@RestController
@RequestMapping("/proprietario")
public class ProprietarioController {
    @Autowired
    ProprietarioService proprietarioService;

    @PostMapping("/criarProprietario")
	public ResponseEntity<ProprietarioDTO> criarProprietario(@RequestBody ProprietarioDTO proprietario){
		 proprietario = proprietarioService.criarProprietario(proprietario);
		return new ResponseEntity<>(proprietario, HttpStatus.OK);
	}

	

	@PutMapping("/alterarProprietario/{id}")
	public ResponseEntity<ProprietarioDTO> AlterarProprietario(@RequestBody ProprietarioDTO proprietario, @PathVariable long id){
		 proprietario = proprietarioService.alterarProprietario(proprietario, id);
		return new ResponseEntity<>(proprietario, HttpStatus.OK);
	}

	

	@DeleteMapping("/deletarProprietario")
	public ResponseEntity<ProprietarioDTO> DeletarProprietario(@RequestBody ProprietarioDTO proprietario){
		 proprietario = proprietarioService.deletarProprietario();
		return new ResponseEntity<>(proprietario, HttpStatus.OK);
	}

    @GetMapping("/detalharProprietario")
	public ResponseEntity<ProprietarioDTO> DetalharProprietario(){
		ProprietarioDTO proprietario = proprietarioService.detalharProprietario();
		return new ResponseEntity<>(proprietario, HttpStatus.OK);
	}

	@GetMapping("/avaliarProprietario/{id}")
	public ResponseEntity<ProprietarioDTO> avaliarProprietario(@RequestBody AvaliaçãoDTO avaliacao, @PathVariable long id){
		ProprietarioDTO proprietario = proprietarioService.avaliarProprietario(id);
		return new ResponseEntity<>(proprietario, HttpStatus.OK);
		 
	}
	
}