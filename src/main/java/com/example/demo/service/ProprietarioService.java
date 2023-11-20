package com.example.demo.service;

import java.util.Calendar;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.AvaliacaoDTO;
import com.example.demo.dto.FuncionariosDTO;
import com.example.demo.dto.ProprietarioDTO;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.Avaliacao;
import com.example.demo.model.Funcionarios;
import com.example.demo.model.Imovel;
import com.example.demo.model.Proprietario;
import com.example.demo.model.Proprietario;
import com.example.demo.repository.AvaliacaoRepository;
import com.example.demo.repository.ImovelRepository;
import com.example.demo.repository.ProprietarioRepository;

@Service
public class ProprietarioService {
    @Autowired
    ProprietarioRepository proprietarioRepository;
    
    @Autowired
    ImovelRepository imovelRepository;
    
    @Autowired
    AvaliacaoRepository avaliacaoRepository;
    
    public ProprietarioDTO criarProprietario(ProprietarioDTO proprietarioDTO){
        Proprietario proprietario = new ModelMapper().map(proprietarioDTO, Proprietario.class);
        proprietario = proprietarioRepository.save(proprietario);
        proprietarioDTO.setProprietarioId(proprietario.getProprietarioId());
        return proprietarioDTO;
    }
        
    	public ProprietarioDTO alterarProprietario(ProprietarioDTO proprietarioDTO, long id){
    		Proprietario proprietario = proprietarioRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Proprietário não encontrado!"));
    		proprietario = new ModelMapper().map(proprietarioDTO, Proprietario.class);
            proprietarioRepository.save(proprietario);
            return proprietarioDTO;
    	}
    	
    	public void deletarProprietario(long id){
    		Proprietario proprietario = proprietarioRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Proprietario não encontrado!"));
            List<Imovel> imoveis = proprietario.getImoveis();
            for( Imovel imovel : imoveis) {
            	imoveis.remove(imovel);
            	imovel.setProprietario(null);
            	imovelRepository.delete(imovel);
            }
            List<Avaliacao> avaliacoes = proprietario.getAvaliacoes();
            for( Avaliacao avaliacao : avaliacoes) {
            	avaliacoes.remove(avaliacao);
            	avaliacao.setProprietario(null);
            	avaliacaoRepository.delete(avaliacao);
            }
            proprietarioRepository.delete(proprietario);
            
    	}
    	
    	public ProprietarioDTO detalharProprietario(ProprietarioDTO proprietarioDTO, long id){
    		Proprietario proprietario = proprietarioRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Proprietario não encontrado!"));
    	      ProprietarioDTO proprietarioDTO2 = new ModelMapper().map(proprietario, ProprietarioDTO.class);
    	      return proprietarioDTO2;
    	}
    	
    	
    	
    	public AvaliacaoDTO avaliarProprietario(AvaliacaoDTO avaliacaoDTO, long id){
    		Proprietario proprietario = proprietarioRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Proprietario não encontrado!"));
  	      	List<Avaliacao> avaliacoes = proprietario.getAvaliacoes();
  	      	avaliacaoDTO.setData(Calendar.getInstance()); //Pode estar dando erro
  	      	avaliacoes.add(avaliacaoDTO);
  	      	proprietario.setAvaliacoes(avaliacoes);
    		return avaliacaoDTO;
    	 
    	}
    	
    	
        
    }
