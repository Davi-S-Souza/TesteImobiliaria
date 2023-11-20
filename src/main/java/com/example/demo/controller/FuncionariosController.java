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
import com.example.demo.dto.FuncionariosDTO;
import com.example.demo.service.FuncionariosService;

@RestController
@RequestMapping("/funcionarios")
public class FuncionariosController {
    @Autowired
    FuncionariosService funcionariosService;

	@GetMapping("/listarFuncionarios")
	public ResponseEntity<List<FuncionariosDTO>> listarFuncionarios(){
		List<FuncionariosDTO> funcionarios = funcionariosService.listarFuncionarios();
		return new ResponseEntity<>(funcionarios, HttpStatus.OK);
	}
	
	@GetMapping("/detalharFuncionario/{funcionarioId}")
	public ResponseEntity<FuncionariosDTO> detalharFuncionario(@PathVariable String funcionarioId){
		FuncionariosDTO funcionario = funcionariosService.detalharFuncionario(funcionarioId);
		return new ResponseEntity<>(funcionario, HttpStatus.OK);
	}

    @PostMapping("/criarFuncionario/{idEmpresa}")
    public ResponseEntity<FuncionariosDTO> criarFuncionario(@RequestBody FuncionariosDTO funcionariosDTO, @PathVariable String idEmpresa){
        funcionariosDTO = funcionariosService.criarFuncionario(funcionariosDTO, idEmpresa);
        return new ResponseEntity<>(funcionariosDTO, HttpStatus.CREATED);
    }
	
	@PutMapping("/alterarFuncionario/{funcionarioId}")
	public ResponseEntity<FuncionariosDTO> alterarFuncionarios(@PathVariable String funcionarioId, @RequestBody FuncionariosDTO funcionariosDTO){
		funcionariosDTO = funcionariosService.alterarFuncionarios(funcionarioId, funcionariosDTO);
		return new ResponseEntity<>(funcionariosDTO, HttpStatus.OK);
	}

	@DeleteMapping("/deletarFuncionario/{funcionarioId}")
	public ResponseEntity<?> deletarFuncionario(@PathVariable String funcionarioId){
		funcionariosService.deletarFuncionario(funcionarioId);
		return new ResponseEntity<>("Funcionario deletado com sucesso!", HttpStatus.OK);
	}

}