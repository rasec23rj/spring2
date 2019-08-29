
package com.spring.spring.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.spring.spring.domain.Cidade;
import com.spring.spring.dto.CidadeDTO;
import com.spring.spring.repositories.CidadeRepository;
import com.spring.spring.service.exceptions.DataIntegrityException;
import com.spring.spring.service.exceptions.ObjectNotFoundException;

@Service
public class CidadeService {

	@Autowired
	private CidadeRepository repo;
	
	public Cidade busca(Integer id) {
		
		Optional<Cidade> obj = repo.findById(id);
		
		if (obj == null) {
			throw new ObjectNotFoundException("Objeto não encontrado! id: " + id
					+ ", Tipo: " + Cidade.class.getName()
					);
		}
		return obj.orElse(null);
	}
	
	public Cidade find(Integer id) {

		Optional<Cidade> obj = repo.findById(id);
		
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! id: " + id + ", Tipo: " + Cidade.class.getName()));
	}
	
	public List<Cidade> index() {

		List<Cidade> obj = repo.findAll();
		return obj;
	}
	
	public Cidade insert(Cidade obj) {
		obj.setId(null);
		return repo.save(obj);
	}

	public Cidade update(Cidade obj) {
		find(obj.getId());
		return repo.save(obj);

	}
	
	public void delete(Integer id) {
		find(id);
		 try {
		        repo.deleteById(id);
		    }
		    catch (DataIntegrityViolationException e) {
		        throw new DataIntegrityException("Não é possível excluir uma categoria id: "+id +" que possui produtos");
		        
		        
		    }

	}
	
	
	public Page<Cidade> findPage(Integer page, Integer linesPerPage, String orderBy, String direction){
		
		PageRequest pageRequest = new PageRequest(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return repo.findAll(pageRequest);
		
	}
	
	public Cidade fromDTO(CidadeDTO objDto) {
		return new Cidade(objDto.getId(), objDto.getName(), null);
	}
	
	
}
