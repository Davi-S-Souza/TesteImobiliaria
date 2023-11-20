
package com.example.demo.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.dto.EmpresaDTO;
import com.example.demo.dto.FuncionariosDTO;
import com.example.demo.exception.ResourceBadRequestException;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.Empresa;
import com.example.demo.model.Funcionarios;
import com.example.demo.repository.EmpresaRepository;
import com.example.demo.repository.FuncionariosRepository;


@Service
public class EmpresaService {
    @Autowired
    private EmpresaRepository empresaRepository;
    @Autowired
    private FuncionariosRepository funcionariosRepository;

    public List<EmpresaDTO> listarEmpresas(){
        List<Empresa> empresas = empresaRepository.findAll();
        if(empresas.isEmpty()){
            throw new ResourceNotFoundException("Nenhuma empresa foi encontrada!");
        }
        List<EmpresaDTO> empresaDTOs = empresas.stream()
                .map(e -> new ModelMapper().map(e, EmpresaDTO.class))
                .collect(Collectors.toList());
        return empresaDTOs;
    }

    public EmpresaDTO detalharEmpresa(String empresaId){
        Empresa empresa = empresaRepository.findById(empresaId).orElseThrow(() -> new ResourceNotFoundException("Empresa não encontrada!"));
        EmpresaDTO empresaDTO = new ModelMapper().map(empresa, EmpresaDTO.class);
        return empresaDTO;
    }

    public EmpresaDTO criarEmpresa(EmpresaDTO empresaDTO){
        Optional<Empresa> empresaOpt = empresaRepository.findById(empresaDTO.getCnpj());
        if(!empresaOpt.isEmpty()){
            throw new ResourceBadRequestException("Esta empresa ja esta registrada!");
        }
        Empresa empresa = new ModelMapper().map(empresaDTO, Empresa.class);
        empresaRepository.save(empresa);
        return empresaDTO;
    }

    public EmpresaDTO alterarEmpresa(EmpresaDTO empresaDTO, String empresaId){
        Empresa empresa = empresaRepository.findById(empresaId).orElseThrow(() -> new ResourceNotFoundException("Empresa não encontrada!"));
        empresa = new ModelMapper().map(empresaDTO, Empresa.class);
        empresaRepository.save(empresa);
        return empresaDTO;
    }

    public List<FuncionariosDTO> removerFuncionario(String empresaId, String funcionarioId){
        Empresa empresa = empresaRepository.findById(empresaId).orElseThrow(() -> new ResourceNotFoundException("Empresa não encontrada!")); 
        Funcionarios funcionario = funcionariosRepository.findById(funcionarioId).orElseThrow(() -> new ResourceNotFoundException("Funcionario não encontrado!"));
        List<Funcionarios> funcionarios = empresa.getFuncionarios();
        funcionarios.remove(funcionario);
        funcionario.setEmpresa(null);
        empresa.setFuncionarios(funcionarios);
        funcionariosRepository.save(funcionario);
        empresaRepository.save(empresa);
        List<FuncionariosDTO> funcionariosDTOs = funcionarios.stream()
                .map(f -> new ModelMapper().map(f, FuncionariosDTO.class))
                .collect(Collectors.toList());
        return funcionariosDTOs;
        
    }

    public void deletarEmpresa(String empresaId){
        Empresa empresa = empresaRepository.findById(empresaId).orElseThrow(() -> new ResourceNotFoundException("Empresa não encontrada!"));
        List<Funcionarios> funcionarios = empresa.getFuncionarios();
        empresa.setFuncionarios(null);
        for(Funcionarios f:funcionarios){
            f.setEmpresa(null);
            funcionariosRepository.delete(f);
        }
        empresaRepository.delete(empresa);
    }
            
    }

