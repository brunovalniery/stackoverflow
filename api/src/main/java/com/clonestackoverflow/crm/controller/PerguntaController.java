package com.clonestackoverflow.crm.controller;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.clonestackoverflow.crm.modelo.entidades.Pergunta;
import com.clonestackoverflow.crm.repositorios.PerguntaRepositorio;

@RestController
@RequestMapping("/perguntas")
public class PerguntaController {
	
	@Autowired
	private PerguntaRepositorio perguntaRep;
	
	@GetMapping
	public List<Pergunta> listarPerguntas(){
		return perguntaRep.findAll();
	}
	
	@GetMapping(path = {"/{id}"})
	public ResponseEntity<Pergunta> buscarPorId(@PathVariable Long id) {
		Optional<Pergunta> pergunta = perguntaRep.findById(id);
		if(pergunta.isPresent()) {
			return new ResponseEntity<>(pergunta.get(),HttpStatus.OK);
		}else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@DeleteMapping(path = {"/{id}"})
	public ResponseEntity<Pergunta> rmvPergunta(@PathVariable(value = "id") Long id){
		Optional<Pergunta> pergunta = perguntaRep.findById(id);
		if(pergunta.isPresent()) {
			perguntaRep.deleteById(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@PostMapping(path = {"/add"})
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<Pergunta> addPergunta(@RequestBody Pergunta pergunta){
		ResponseEntity<Pergunta> response;
		try {
			response = new ResponseEntity<Pergunta>(perguntaRep.save(new Pergunta(pergunta.getId(),pergunta.getTitulo()
					,pergunta.getTexto(),new Date(),null)),HttpStatus.CREATED);
		}catch(Exception e) {
			response = new ResponseEntity<Pergunta>(HttpStatus.BAD_REQUEST);
		}
		return response;
	}
	
	@PutMapping(path = {"/{id}"})
	public ResponseEntity<Pergunta> attPergunta(@PathVariable(value = "id") Long id,@RequestBody Pergunta newPergunta) {
		Optional<Pergunta> pergunta = perguntaRep.findById(id);
		if(pergunta.isPresent()) {
			Pergunta oldPergunta = pergunta.get();
			oldPergunta.setTitulo(newPergunta.getTitulo());
			oldPergunta.setTexto(newPergunta.getTexto());
			return new ResponseEntity<Pergunta>(oldPergunta,HttpStatus.OK);
		}else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
}
