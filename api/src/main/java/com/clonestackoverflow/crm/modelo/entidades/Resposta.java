package com.clonestackoverflow.crm.modelo.entidades;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.clonestackoverflow.crm.modelo.entidades.enums.RankResposta;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@EqualsAndHashCode
@Entity
public class Resposta {
	
	public static SimpleDateFormat sdf = new SimpleDateFormat();
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Getter
	private Long id;
	
	@Getter 
	@Setter
	@Column(nullable = false)
	private String texto;
	
	
	@Getter 
	@Setter
	@Enumerated(EnumType.STRING)
	private RankResposta rank;
	
	 
	@Column(nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataPub;
	
	public String getDataPub() {
		return sdf.format(dataPub);
	}
	
	@Getter 
	@Setter
	@ManyToOne
	private Pergunta pergunta;
	
	
	/*public void avaliarResposta(RankResposta rank) {
		this.rank = rank;
	}*/
	
}
