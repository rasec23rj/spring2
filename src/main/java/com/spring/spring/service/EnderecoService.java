package com.spring.spring.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.spring.domain.Endereco;
import com.spring.spring.repositories.EnderecoRepository;
import com.spring.spring.service.exceptions.ObjectNotFoundException;

@Service
public class EnderecoService {

	@Autowired
	private EnderecoRepository repo;

	public List<Endereco> index() {

		List<Endereco> obj = repo.findAll();
		return obj;
	}

	public Endereco busca(Integer id) {

		Optional<Endereco> obj = repo.findById(id);

		if (obj == null) {

			throw new ObjectNotFoundException("Objeto não encontrado! id: " + id + "Tipo" + Endereco.class.getName());
		}

		return obj.orElse(null);
	}
}
