package com.example.demo.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.dto.ImovelDTO;
import com.example.demo.model.Filtro;
import com.example.demo.service.ImovelService;



@RestController
@RequestMapping("/imovel")
public class ImovelController {
    @Autowired
	ImovelService imovelService;

	@GetMapping("/listarTodosImoveis")
	public ResponseEntity<List<ImovelDTO>> listarTodosImoveis(){
		List<ImovelDTO> imoveis = imovelService.listarTodosImoveis();
		return new ResponseEntity<>(imoveis, HttpStatus.OK);
	}
	
	@GetMapping("/listarImoveisProprietario/{Id}")
	public ResponseEntity<List<ImovelDTO>> listarImoveisProprietario(@PathVariable long ProprietarioId){
		List<ImovelDTO> imoveis = imovelService.listarImoveisProprietario(ProprietarioId);
		return new ResponseEntity<>(imoveis, HttpStatus.OK);
	}
	
	@GetMapping("/listarImoveisFiltro")
	public ResponseEntity<List<ImovelDTO>> listarImoveisFiltro(@RequestBody Filtro filtro){
		List<ImovelDTO> imoveis = imovelService.listarImoveisFiltro(filtro);
		return new ResponseEntity<>(imoveis, HttpStatus.OK);
	}
	
	
	@GetMapping("/listarImoveisPesquisa/{pesquisa}")
	public ResponseEntity<List<ImovelDTO>> listarImoveisPesquisa(@PathVariable String pesquisa){
		List<ImovelDTO> imoveis = imovelService.listarImoveisPesquisa(pesquisa);
		return new ResponseEntity<>(imoveis, HttpStatus.OK);
	}
	
	
	@GetMapping("/listarImoveisPorRelevancia/{bool}")
	public ResponseEntity<List<ImovelDTO>> listarImoveisPorRelevancia(@PathVariable boolean bool){
		List<ImovelDTO> imoveis = imovelService.listarImoveisPorRelevancia(bool);
		return new ResponseEntity<>(imoveis, HttpStatus.OK);
	}
	
	@GetMapping("/listarImoveisPorTipo/{tipo}")
	public ResponseEntity<List<ImovelDTO>> listarImoveisPorTipo(@PathVariable String tipo){
		List<ImovelDTO> imoveis = imovelService.listarImoveisPorTipo(tipo);
		return new ResponseEntity<>(imoveis, HttpStatus.OK);
	}
	
	@GetMapping("/listarImoveisMaiorPreco")
	public ResponseEntity<List<ImovelDTO>> listarImoveisMaiorPreco(){
		List<ImovelDTO> imoveis = imovelService.listarImoveisMaiorPreco();
		return new ResponseEntity<>(imoveis, HttpStatus.OK);
	}
	
	@GetMapping("/listarImoveisMenorPreco")
	public ResponseEntity<List<ImovelDTO>> listarImoveisMenorPreco(){
		List<ImovelDTO> imoveis = imovelService.listarImoveisMenorPreco();
		return new ResponseEntity<>(imoveis, HttpStatus.OK);
	}

    @GetMapping("/detalharImovel/{id}")
	public ResponseEntity<ImovelDTO> detalharImovel(@PathVariable long id){
		ImovelDTO imovel = imovelService.detalharImovel(id);
		return new ResponseEntity<>(imovel, HttpStatus.OK);
	}

    @PostMapping(value= {"/criarImovel"}, consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
	public ResponseEntity<ImovelDTO> criarImovel(@RequestBody ImovelDTO imovel, MultipartFile[] files){
		imovel = imovelService.criarImovel(imovel, files);
		return new ResponseEntity<>(imovel, HttpStatus.OK);
	}

    @PutMapping("/alterarImovel/{id}")
	public ResponseEntity<ImovelDTO> alterarImovel(@RequestBody ImovelDTO imovel, @PathVariable long id){
		imovel = imovelService.alterarImovel(imovel, id);
		return new ResponseEntity<>(imovel, HttpStatus.OK);
	}

    @DeleteMapping("/deletarImovel/{id}")
	public ResponseEntity<?> deletarImovel(@PathVariable long id){
		imovelService.deletarImovel(id);
		return new ResponseEntity<>("Imovel deletado com sucesso!", HttpStatus.OK);
	}

	@PutMapping(value= {"/adicionarImagens/{imovelId}"}, consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
	public ResponseEntity<ImovelDTO> adicionarioImagens(@PathVariable Long imovelId, MultipartFile[] files){
		ImovelDTO imovelDTO = imovelService.adicionarImagens(imovelId, files);
		return new ResponseEntity<>(imovelDTO, HttpStatus.OK);
	}
}