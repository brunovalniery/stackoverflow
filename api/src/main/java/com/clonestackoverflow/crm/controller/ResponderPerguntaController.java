package com.clonestackoverflow.crm.controller;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.clonestackoverflow.crm.modelo.entidades.Pergunta;
import com.clonestackoverflow.crm.modelo.entidades.Resposta;
import com.clonestackoverflow.crm.modelo.entidades.enums.RankResposta;
import com.clonestackoverflow.crm.repositorios.PerguntaRepositorio;
import com.clonestackoverflow.crm.repositorios.RespostasRepositorio;

@RestController
@RequestMapping("/respostas")
public class ResponderPerguntaController {

	@Autowired
	private RespostasRepositorio respRep;

	@Autowired
	private PerguntaRepositorio perguntaRep;

	// add resposta a pergunta existente
	@PostMapping(path = {"/add/{id}"})
	public ResponseEntity<List<Resposta>> addResposta(@PathVariable Long id, @RequestBody Resposta resposta) {
		Optional<Pergunta> pergunta = perguntaRep.findById(id);
		ResponseEntity<List<Resposta>> response;
		if (pergunta.isPresent()) {
			resposta.setPergunta(pergunta.get());
			respRep.save(new Resposta(resposta.getId(),resposta.getTexto(),
					RankResposta.BOA,new Date(),resposta.getPergunta()));
			List<Resposta> list = pergunta.get().getRespostas();
			response = new ResponseEntity<List<Resposta>>(list, HttpStatus.OK);
		} else {
			response = new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return response;
	}
}
