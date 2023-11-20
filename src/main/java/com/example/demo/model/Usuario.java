package com.example.demo.model;

import jakarta.persistence.DiscriminatorType;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "tipo_usuario", discriminatorType = DiscriminatorType.STRING)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Usuario{
	@Id
	@GeneratedValue(strategy=GenerationType.UUID)
	@Column(name="user_id")
	private String userId;
	private String usuario;
	private String email;
	private String telefone;
	private byte[] foto;
}