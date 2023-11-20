package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.Proprietario;

public interface ProprietarioRepository extends JpaRepository<Proprietario, Long>{
    
}
