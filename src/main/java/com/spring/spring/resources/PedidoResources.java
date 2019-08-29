
package com.spring.spring.resources;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.spring.spring.domain.Pedido;
import com.spring.spring.service.PedidoService;

@RestController
@RequestMapping(value = "/pedidos")
public class PedidoResources {

	@Autowired
	private PedidoService service;

	@RequestMapping(value = "", method = RequestMethod.GET)
	public ResponseEntity<?> index() {

		List<Pedido> obj = service.index();

		return ResponseEntity.ok().body(obj);

	}

	@RequestMapping(value = "/lista/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> lista(@PathVariable Integer id) {

		Pedido obj = service.buscar(id);

		return ResponseEntity.ok().body(obj);

	}
	
	@Transactional(readOnly = true)
	@RequestMapping(value = "/save", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE,  produces = "application/json; charset=utf-8")
	public ResponseEntity<Void> insert(@Valid @RequestBody Pedido obj) {

		obj = service.insert(obj);

		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/lista/{id}").buildAndExpand(obj.getId())
				.toUri();

		return ResponseEntity.created(uri).build();
	}

}
