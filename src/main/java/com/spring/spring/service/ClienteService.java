
package com.spring.spring.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.spring.spring.domain.Cidade;
import com.spring.spring.domain.Cliente;
import com.spring.spring.domain.Endereco;
import com.spring.spring.domain.enums.TipoCliente;
import com.spring.spring.dto.ClienteDTO;
import com.spring.spring.dto.ClienteNweDTO;
import com.spring.spring.repositories.CidadeRepository;
import com.spring.spring.repositories.ClienteRepository;
import com.spring.spring.repositories.EnderecoRepository;
import com.spring.spring.service.exceptions.DataIntegrityException;
import com.spring.spring.service.exceptions.ObjectNotFoundException;

@Service
public class ClienteService {

	@Autowired
	private static ClienteRepository repo;

	@Autowired
	private CidadeRepository cida;
	
	@Autowired
	private EnderecoRepository endereco;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;


	public Cliente busca(Integer id) {

		Optional<Cliente> obj = repo.findById(id);

		if (obj == null) {

			throw new ObjectNotFoundException("Cliente não encontrado! id: " + id + "Tipo" + Cidade.class.getName());

		}

		return obj.orElse(null);

	}

	public List<Cliente> index() {

		List<Cliente> obj = repo.findAll();
		return obj;
	}

	public static Cliente find(Integer id) {

		Optional<Cliente> obj = repo.findById(id);

		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Cliente não encontrado! id: " + id + ", Tipo: " + Cliente.class.getName()));
	}

	public Cliente insert(Cliente obj) {
		obj.setId(null);
		obj = repo.save(obj);
		endereco.saveAll(obj.getEnderecos());

		return obj;
	}

	public Cliente update(Cliente obj) {
		Cliente newObj = find(obj.getId());
		updateData(newObj, obj);
		return repo.save(newObj);

	}

	public void delete(Integer id) {
		find(id);
		try {
			repo.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possível excluir uma cliente id: " + id + " que possui pedido");

		}

	}

	public Page<Cliente> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {

		PageRequest pageRequest = new PageRequest(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return repo.findAll(pageRequest);

	}

	public Cliente fromDTO(ClienteDTO objDto) {
		return new Cliente(objDto.getId(), objDto.getName(), objDto.getEmail(), null, null,null);
	}

	public Cliente fromDTO(ClienteNweDTO objDto) {
		Cliente cli = new Cliente(null, objDto.getName(), objDto.getEmail(), objDto.getCpfOuCnpj(),
				TipoCliente.toEnum(objDto.getTipo()),bCryptPasswordEncoder.encode(objDto.getSenha()));

		Cidade cidade = new Cidade(objDto.getCidadeId(),null,null);

		Endereco end = new Endereco(null, objDto.getLogradouro(), objDto.getNumero(), objDto.getComplemento(),
				objDto.getBairro(), objDto.getCep(), cli, cidade);

		cli.getEnderecos().add(end);
		cli.getTelefones().add(objDto.getTelefone1());

		
		if(objDto.getTelefone2() !=null) {
			cli.getTelefones().add(objDto.getTelefone2());
			
		}
		if(objDto.getTelefone3() !=null) {
			cli.getTelefones().add(objDto.getTelefone3());
			
		}
		
		return cli;
		

	}

	private void updateData(Cliente newObj, Cliente obj) {
		newObj.setName(obj.getName());
		newObj.setEmail(obj.getEmail());
	}

}
