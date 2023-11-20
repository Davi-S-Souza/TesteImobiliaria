package com.example.demo.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.dto.FuncionariosDTO;
import com.example.demo.exception.ResourceBadRequestException;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.Empresa;
import com.example.demo.model.Funcionarios;
import com.example.demo.repository.EmpresaRepository;
import com.example.demo.repository.FuncionariosRepository;


@Service
public class FuncionariosService {
    @Autowired
    private FuncionariosRepository funcionariosRepository;
    @Autowired
    private EmpresaRepository empresaRepository;

    public List<FuncionariosDTO> listarFuncionarios(){
      List<Funcionarios> funcionarios = funcionariosRepository.findAll();
      if(funcionarios.isEmpty()){
        throw new ResourceNotFoundException("Nenhum funcionario encontrado!");
      }
      List<FuncionariosDTO> funcionariosDTO = funcionarios.stream()
            .map(f -> new ModelMapper().map(f, FuncionariosDTO.class))
            .collect(Collectors.toList());
      return funcionariosDTO;
    }

    public FuncionariosDTO detalharFuncionario(String funcionarioId){
      Funcionarios funcionario = funcionariosRepository.findById(funcionarioId).orElseThrow(() -> new ResourceNotFoundException("Funcionario não encontrado!"));
      FuncionariosDTO funcionariosDTO = new ModelMapper().map(funcionario, FuncionariosDTO.class);
      return funcionariosDTO;
    }

    public FuncionariosDTO criarFuncionario(FuncionariosDTO funcionariosDTO, String empresaId){
       Empresa empresa = empresaRepository.findById(empresaId).orElseThrow(() -> new ResourceNotFoundException("Empresa não encontrada"));
       Funcionarios funcionarios = new ModelMapper().map(funcionariosDTO, Funcionarios.class);
       List<Funcionarios> listaFuncionarios = empresa.getFuncionarios();
       for(Funcionarios f:listaFuncionarios){
            if(f.equals(funcionarios)){
              throw new ResourceBadRequestException("Este funcionario ja esta cadastrado!");  
            }
       }
       funcionarios.setEmpresa(empresa);
       funcionariosRepository.save(funcionarios);
       return funcionariosDTO;
    }

    public FuncionariosDTO alterarFuncionarios(String funcionarioId, FuncionariosDTO funcionariosDTO){
      Funcionarios funcionarios = funcionariosRepository.findById(funcionarioId).orElseThrow(()-> new ResourceNotFoundException("Funcionario não encontrado!"));
      funcionarios = new ModelMapper().map(funcionariosDTO, Funcionarios.class);
      funcionariosRepository.save(funcionarios);
      return funcionariosDTO;
    }

    public void deletarFuncionario(String funcionarioId){
      Funcionarios funcionario = funcionariosRepository.findById(funcionarioId).orElseThrow(() -> new ResourceNotFoundException("Funcionario não encontrado!"));
      funcionario.setEmpresa(null);
      funcionariosRepository.delete(funcionario);
    }
            
    }