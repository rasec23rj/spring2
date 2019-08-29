
package com.spring.spring.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.spring.spring.domain.Categoria;
import com.spring.spring.dto.CategoriaDTO;
import com.spring.spring.repositories.CategoriaRepository;
import com.spring.spring.service.exceptions.DataIntegrityException;
import com.spring.spring.service.exceptions.ObjectNotFoundException;

@Service
public class CategoriaService {

	@Autowired
	private CategoriaRepository repo;

	public List<Categoria> index() {

		List<Categoria> obj = repo.findAll();
		return obj;
	}

	public Categoria find(Integer id) {

		Optional<Categoria> obj = repo.findById(id);
		
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! id: " + id + ", Tipo: " + Categoria.class.getName()));
	}

	public Categoria insert(Categoria obj) {
		obj.setId(null);
		return repo.save(obj);
	}

	
	
	public Categoria update(Categoria obj) {
		Categoria newObj = find(obj.getId());
		updateData(newObj, obj);
		return repo.save(newObj);

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
	
	
	public Page<Categoria> findPage(Integer page, Integer linesPerPage, String orderBy, String direction){
		
		PageRequest pageRequest = new PageRequest(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return repo.findAll(pageRequest);
		
	}
	
	public Categoria fromDTO(CategoriaDTO objDto) {
		return new Categoria(objDto.getId(), objDto.getName());
	}
	
	
	private void updateData(Categoria newObj, Categoria obj) {
		newObj.setName(obj.getName());
	}
	
}
