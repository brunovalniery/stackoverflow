package com.clonestackoverflow.crm.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.clonestackoverflow.crm.modelo.entidades.Pergunta;
import com.clonestackoverflow.crm.repositorios.PerguntaRepositorio;

@RestController
@RequestMapping("/pesquisar")
public class PesquisaPerguntaController {
	
	@Autowired
	private PerguntaRepositorio perguntaRep;
	
	@GetMapping(path = "/{text}")
	public List<Pergunta> listarPerguntas(@PathVariable String text){
		return perguntaRep.findAll().stream()
				.filter(p -> p.getTitulo().contains(text)||p.getTexto().contains(text))
				.collect(Collectors.toList());
	}
}
