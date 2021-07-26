package com.clonestackoverflow.crm.modelo.entidades;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RequiredArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Entity
public class Pergunta {
	
	public static SimpleDateFormat sdf = new SimpleDateFormat();
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Getter 
	@Setter
	private Long id;
	
	@Getter 
	@Setter
	@Column(nullable = false)
	private String titulo;
	
	@Getter 
	@Setter
	@Column(nullable = false)
	private String texto;
	
	@Column(nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataPub;
	
	public String getDataPub() {
		return sdf.format(dataPub);
	}
	
	@Getter
	@OneToMany
	@JoinColumn(name="pergunta_id")
	private List<Resposta> respostas;
	
	public List<Resposta> addResposta(Resposta resposta) {
		if(resposta!=null)
			respostas.add(resposta);
		return respostas;
	}
}
