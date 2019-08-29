package com.spring.spring.resources;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.spring.spring.domain.Cidade;
import com.spring.spring.service.CidadeService;

@RestController
@RequestMapping(value="/cidade")
public class CidadeResources {
	
	@Autowired
	private CidadeService service;
	
	@RequestMapping(value="/", method=RequestMethod.GET)
	public ResponseEntity<?> index() {
		
		List<Cidade> obj = service.index();
		
		return ResponseEntity.ok().body(obj);
		
		
	}

	
	@RequestMapping(value="/lista/{id}", method=RequestMethod.GET)
	public ResponseEntity<?> lista(@PathVariable Integer id) {
		
		Cidade obj = service.busca(id);
		
		return ResponseEntity.ok().body(obj);
		
		
	}

}
