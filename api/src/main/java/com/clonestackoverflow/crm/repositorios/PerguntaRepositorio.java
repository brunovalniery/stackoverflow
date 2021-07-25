package com.clonestackoverflow.crm.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.clonestackoverflow.crm.modelo.entidades.Pergunta;

@Repository
public interface PerguntaRepositorio extends JpaRepository<Pergunta,Long> {

}
