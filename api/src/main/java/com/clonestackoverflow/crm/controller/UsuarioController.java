package com.clonestackoverflow.crm.controller;

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
import org.springframework.web.bind.annotation.RestController;

import com.clonestackoverflow.crm.modelo.entidades.UsuarioEstudante;
import com.clonestackoverflow.crm.modelo.entidades.enums.RankUsuario;
import com.clonestackoverflow.crm.repositorios.UsuarioEstudRepositorio;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {
	
	@Autowired
	private UsuarioEstudRepositorio userEstRep;
	
	@GetMapping
	public List<UsuarioEstudante> listarUsuariosEstudantes() {
		return this.userEstRep.findAll();
	}
	
	@GetMapping(path = {"/{id}"})
	public ResponseEntity<UsuarioEstudante> buscarPorId(@PathVariable Long id) {
		Optional<UsuarioEstudante> user = userEstRep.findById(id);
		if(user.isPresent()) {
			return new ResponseEntity<>(user.get(),HttpStatus.OK);
		}else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@PostMapping
	public ResponseEntity<UsuarioEstudante> addUser(@RequestBody UsuarioEstudante user) {
		ResponseEntity<UsuarioEstudante> response;
		try {
			user.setRank(RankUsuario.BAIXO);
			response = new ResponseEntity<UsuarioEstudante>(userEstRep.save(user)
					,HttpStatus.CREATED);
		}catch(IllegalArgumentException e) {
			response = new ResponseEntity<UsuarioEstudante>(HttpStatus.BAD_REQUEST);
		}
		return response;
	}
	
	/*@PutMapping(path = {"/{id}"})
	public ResponseEntity<UsuarioEstudante> attUser(@PathVariable(value = "id") Long id,@RequestBody UsuarioEstudante newUser) {
		Optional<UsuarioEstudante> user = userEstRep.findById(id);
		if(user.isPresent()) {
			user.get().setEmail(newUser.getEmail());
			user.get().setNome(newUser.getNome());
			return new ResponseEntity<UsuarioEstudante>(user.get(),HttpStatus.OK);
		}else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}*/
	@PutMapping(path = {"/{id}"})
	public ResponseEntity<UsuarioEstudante> attUser(@PathVariable(value = "id") Long id,@RequestBody UsuarioEstudante newUser) {
		Optional<UsuarioEstudante> oldUser = userEstRep.findById(id);
        if(oldUser.isPresent()){
        	UsuarioEstudante usuario = oldUser.get();
        	usuario.setNome(newUser.getNome());
            userEstRep.save(usuario);
            return new ResponseEntity<UsuarioEstudante>(usuario, HttpStatus.OK);
        }
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
	@DeleteMapping(path = {"/{id}"})
	public ResponseEntity<UsuarioEstudante> rmvUser(@PathVariable(value = "id") Long id){
		Optional<UsuarioEstudante> user = userEstRep.findById(id);
		if(user.isPresent()) {
			userEstRep.deleteById(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
}
