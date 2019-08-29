package com.spring.spring.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.spring.domain.Estado;
import com.spring.spring.repositories.EstadoRepository;
import com.spring.spring.service.exceptions.ObjectNotFoundException;

@Service
public class EstadoService {

	@Autowired
	private EstadoRepository repo;
	
	public List<Estado> index(){
		
		List<Estado> obj = repo.findAll();
		return obj;
	}
	
	public Estado busca(Integer id) {
		
		Optional<Estado> obj = repo.findById(id); 
		if (obj == null) {
			
			throw new ObjectNotFoundException("Objeto não encontrado! id: " + id + "Tipo" + Estado.class.getName());
		}
		
		return obj.orElse(null);
	}
	
	
}
