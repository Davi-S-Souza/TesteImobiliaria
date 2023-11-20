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
import com.example.demo.service.EmpresaService;
@RestController
@RequestMapping("/empresa")
public class EmpresaController {
    @Autowired
    EmpresaService empresaService;

    @GetMapping("/listarEmpresas")
	public ResponseEntity<List<EmpresaDTO>> listarEmpresas(){
		List<EmpresaDTO> empresas = empresaService.listarEmpresas();
		return new ResponseEntity<>(empresas, HttpStatus.OK);
	}

    @GetMapping("/detalharEmpresa/{empresaId}")
	public ResponseEntity<EmpresaDTO> detalharEmpresa(@PathVariable String empresaId){
		EmpresaDTO empresa = empresaService.detalharEmpresa(empresaId);
		return new ResponseEntity<>(empresa, HttpStatus.OK);
	}

    @PostMapping("/criarEmpresa")
	public ResponseEntity<EmpresaDTO> criarEmpresa(@RequestBody EmpresaDTO empresaDTO){
		empresaDTO = empresaService.criarEmpresa(empresaDTO);
		return new ResponseEntity<>(empresaDTO, HttpStatus.CREATED);
	}

    @PutMapping("/alterarEmpresa/{empresaId}")
    public ResponseEntity<EmpresaDTO> alterarEmpresa(@RequestBody EmpresaDTO empresaDTO, @PathVariable String empresaId){
        empresaDTO = empresaService.alterarEmpresa(empresaDTO, empresaId);
		return new ResponseEntity<>(empresaDTO, HttpStatus.OK);
    }

	@PutMapping("/removerFuncionario/{empresaId}/{funcionarioId}")
	public ResponseEntity<List<FuncionariosDTO>> removerFuncionario(@PathVariable String empresaId, @PathVariable String funcionarioId){
		List<FuncionariosDTO> funcionarios = empresaService.removerFuncionario(empresaId, funcionarioId);
		return new ResponseEntity<>(funcionarios, HttpStatus.OK);
	}

	@DeleteMapping("/deletarEmpresa/{empresaId}")
	public ResponseEntity<?> deletarEmpresa(@PathVariable String empresaId){
		empresaService.deletarEmpresa(empresaId);
		return new ResponseEntity<>("Empresa deletada com sucesso!", HttpStatus.OK);
	}
	
	
	
}