
package com.example.demo.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.dto.ImovelDTO;
import com.example.demo.exception.ResourceInternalServerException;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.Filtro;
import com.example.demo.model.Imagens;
import com.example.demo.model.Imovel;
import com.example.demo.model.Proprietario;
import com.example.demo.repository.ImovelRepository;
import com.example.demo.repository.ProprietarioRepository;

@Service
public class ImovelService {
    @Autowired
    private ImovelRepository imovelRepository;
    @Autowired
    private ProprietarioRepository proprietarioRepository;




    public List<ImovelDTO> listarTodosImoveis(){
        List<Imovel> listaDeImoveis = imovelRepository.findAll();
        List<ImovelDTO> listaDeImoveisDTO = listaDeImoveis.stream()
            .map(i -> new ModelMapper().map(i, ImovelDTO.class))
            .collect(Collectors.toList());
            return listaDeImoveisDTO;
    }

    public List<ImovelDTO> listarImoveisProprietario(long proprietarioId){
        List<Imovel> listaDeImoveis = imovelRepository.listarImovelPorProprietario(proprietarioId);
        List<ImovelDTO> listaDeImoveisDTO = listaDeImoveis.stream()
            .map(i -> new ModelMapper().map(i, ImovelDTO.class))
            .collect(Collectors.toList());
            return listaDeImoveisDTO;
            }

	public List<ImovelDTO> listarImoveisFiltro(Filtro filtro){
		List<Imovel> imoveis = imovelRepository.findImoveisByFiltro(filtro.getArea(), filtro.getPrecoMin(), filtro.getPrecoMax(), filtro.getTipoAloc(), filtro.getMobilia(), filtro.getLocalizacao());
        if(imoveis.isEmpty()){
            throw new ResourceNotFoundException("Nenhuma imovel foi encontrado!");
        }
        List<ImovelDTO> imovelDTOs = imoveis.stream()
                .map(i -> new ModelMapper().map(i, ImovelDTO.class))
                .collect(Collectors.toList());
        if(filtro.isRelevancia() == true){
            Collections.sort(imovelDTOs, Comparator.comparingInt(ImovelDTO::getNumClicks));
        }
        return imovelDTOs;
    }

   
	public List<ImovelDTO> listarImoveisPesquisa(String pesquisa){
		List<Imovel> imoveis = imovelRepository.findImoveisByTextoPesquisa(pesquisa);
        if(imoveis.isEmpty()){
            throw new ResourceNotFoundException("Nenhum imovel encontrado!");
        }
        List<ImovelDTO> imovelDTOs = imoveis.stream() 
                .map(i -> new ModelMapper().map(i, ImovelDTO.class))
                .collect(Collectors.toList());
        return imovelDTOs;
	}

	public List<ImovelDTO> listarImoveisPorRelevancia(boolean bool){
		List<Imovel> imoveis = imovelRepository.findAll();
        if(imoveis.isEmpty()){
            throw new ResourceNotFoundException("Nenhum imovel encontrado!");
        }
        List<ImovelDTO> imovelDTOs = imoveis.stream()
                .map(i -> new ModelMapper().map(i, ImovelDTO.class))
                .collect(Collectors.toList());
        if(bool == true){
            Collections.sort(imovelDTOs, Comparator.comparingInt(ImovelDTO::getNumClicks));
        } else if(bool == false){
            Collections.sort(imovelDTOs, Comparator.comparingInt(ImovelDTO::getNumClicks).reversed());
        }
        return imovelDTOs;
	}

	public List<ImovelDTO> listarImoveisPorTipo(String Tipo){
		List<Imovel> imoveis = imovelRepository.findByAlocacao(Tipo);
        if(imoveis.isEmpty()){
            throw new ResourceNotFoundException("Imoveis não encontrados!");
        }
        List<ImovelDTO> imovelDTOs = imoveis.stream() 
                .map(i -> new ModelMapper().map(i, ImovelDTO.class))
                .collect(Collectors.toList());
        return imovelDTOs;
	}

	public List<ImovelDTO> listarImoveisMaiorPreco(){
		List<Imovel> imoveis = imovelRepository.findAll();
        if(imoveis.isEmpty()){
            throw new ResourceNotFoundException("Imoveis não encontrados!");
        }
        List<ImovelDTO> imovelDTOs = imoveis.stream() 
                .map(i -> new ModelMapper().map(i, ImovelDTO.class))
                .collect(Collectors.toList());
         Collections.sort(imovelDTOs, Comparator.comparingDouble(ImovelDTO::getPreço));
        return imovelDTOs;
	}

    public List<ImovelDTO> listarImoveisMenorPreco(){
		List<Imovel> imoveis = imovelRepository.findAll();
        if(imoveis.isEmpty()){
            throw new ResourceNotFoundException("Imoveis não encontrados!");
        }
        List<ImovelDTO> imovelDTOs = imoveis.stream() 
                .map(i -> new ModelMapper().map(i, ImovelDTO.class))
                .collect(Collectors.toList());
         Collections.sort(imovelDTOs, Comparator.comparingDouble(ImovelDTO::getPreço).reversed());
        return imovelDTOs;
	}

	public ImovelDTO detalharImovel(long id){
		Imovel imovel = imovelRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Imovel não encontrado!"));
        ImovelDTO imovelDTO = new ModelMapper().map(imovel, ImovelDTO.class);
        return imovelDTO;
	}

    public ImovelDTO criarImovel(ImovelDTO imovelDTO, MultipartFile[] files){
		try {
            Imovel imovel = new ModelMapper().map(imovelDTO, Imovel.class);
            List<Imagens> imagens = uploadImage(files);
            imovel.setImagens(imagens);
            imovel = imovelRepository.save(imovel);
            imovelDTO.setImovelId(imovel.getImovelId());
            return imovelDTO;
        } catch (Exception e) {
            throw new ResourceInternalServerException("Ocorreu um erro no servidor!");
        }
	}

   
	public ImovelDTO alterarImovel(ImovelDTO imovelDTO, long id){
		Imovel imovel = imovelRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Imovel não encontrado!"));
        imovel = new ModelMapper().map(imovelDTO, Imovel.class);
        imovelRepository.save(imovel);
        return imovelDTO;
	}
             
	public void deletarImovel(long id){
		Imovel imovel = imovelRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Imovel não encontrado!"));
        Proprietario proprietario = imovel.getProprietario();
        List<Imovel> imoveis = proprietario.getImoveis();
        imoveis.remove(imovel);
        imovel.setProprietario(null);
        proprietario.setImoveis(imoveis);
        proprietarioRepository.save(proprietario);
        imovelRepository.delete(imovel);
	}

    public ImovelDTO adicionarImagens(Long imovelId, MultipartFile[] files){
        Imovel imovel = imovelRepository.findById(imovelId).orElseThrow(() -> new ResourceNotFoundException("Imovel não encontrado!"));
        try {
            List<Imagens> imagens = uploadImage(files);
            List<Imagens> imagensJaExistentes = imovel.getImagens();
            imagens.addAll(imagensJaExistentes);
            imovel.setImagens(imagens);
            imovelRepository.save(imovel);
            ImovelDTO imovelDTO = new ModelMapper().map(imovel, ImovelDTO.class);
            return imovelDTO;
        } catch (Exception e) {
            throw new ResourceInternalServerException("Ocorreu um erro no servidor!");
        }
    }

     public List<Imagens> uploadImage(MultipartFile[] multipartFiles) throws IOException {
        List<Imagens> imagens = new ArrayList<>();

        for (MultipartFile file : multipartFiles) {
            Imagens imagem = new Imagens(
                    file.getOriginalFilename(),
                    file.getContentType(),
                    file.getBytes());
            imagens.add(imagem);
        }

        return imagens;
    }
            
    }

