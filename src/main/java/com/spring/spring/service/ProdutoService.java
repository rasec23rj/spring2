
package com.spring.spring.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.spring.spring.domain.Categoria;
import com.spring.spring.domain.Produto;
import com.spring.spring.repositories.CategoriaRepository;
import com.spring.spring.repositories.ProdutoRepository;
import com.spring.spring.service.exceptions.ObjectNotFoundException;

@Service
public class ProdutoService {

	@Autowired
	private ProdutoRepository repo;
	
	@Autowired
	private CategoriaRepository cat;
	
	public Produto busca(Integer id) {
		
		Optional<Produto> obj = repo.findById(id);
		
		if (obj == null) {
			throw new ObjectNotFoundException("Objeto não encontrado! id: " + id
					+ ", Tipo: " + Produto.class.getName()
					);
		}
		return obj.orElse(null);
	}
	
	public List<Produto> index() {

		List<Produto> obj = repo.findAll();
		return obj;
	}
	
	public Page<Produto> search(String name, List<Integer> ids, Integer page, Integer linesPerPage, String orderBy, String direction){
		
		PageRequest pageRequest = new PageRequest(page, linesPerPage, Direction.valueOf(direction), orderBy);
		List<Categoria> categorias = cat.findAllById(ids);
		return repo.findDistinctByNameContainingAndCategoriasIn(name, categorias, pageRequest);
		
	}
	
	
	
}
