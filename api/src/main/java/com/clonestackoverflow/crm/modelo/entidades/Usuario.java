package com.clonestackoverflow.crm.modelo.entidades;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RequiredArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Entity
public abstract class Usuario {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Getter 
	@Setter
	private Long id;
	
	@Getter 
	@Setter
	@Column(nullable = false)
	private String nome;

	@Getter 
	@Setter
	@Column(nullable = false)
	private String email;
}
