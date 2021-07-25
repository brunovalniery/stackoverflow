package com.clonestackoverflow.crm.modelo.entidades;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import com.clonestackoverflow.crm.modelo.entidades.enums.RankUsuario;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RequiredArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper=false)
@Entity
public class UsuarioEstudante extends Usuario{

	@Getter 
	@Setter
	@Enumerated(EnumType.STRING)
	private RankUsuario rank;
	
	/*
	 * @OneToMany
	 * JoinColumn()
	 * private List<Pergunta> perguntas
	 */

}
