package com.spring.spring.service.validation;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.spring.spring.domain.Cliente;
import com.spring.spring.domain.enums.TipoCliente;
import com.spring.spring.dto.ClienteNweDTO;
import com.spring.spring.repositories.ClienteRepository;
import com.spring.spring.resources.exception.FieldMessage;

public class ClienteInsertValidator implements ConstraintValidator<ClienteInsert, ClienteNweDTO> {
	
	@Autowired
	private ClienteRepository repo;
	
	@Override
	public void initialize(ClienteInsert ann) {
	}

	@Override
	public boolean isValid(ClienteNweDTO objDto, ConstraintValidatorContext context) {
		List<FieldMessage> list = new ArrayList<>();

		// inclua os testes aqui, inserindo erros na lista
		
		if(objDto.getTipo().equals(TipoCliente.PessoaFisica.getCod()) && !BR.isValidCPF(objDto.getCpfOuCnpj())) {
			
			list.add(new FieldMessage("cpfOuCnpj", "CPF inválido"));
		}
		
		if(objDto.getTipo().equals(TipoCliente.PessoJuridica.getCod()) && !BR.isValidCNPJ(objDto.getCpfOuCnpj())) {
			
			list.add(new FieldMessage("cpfOuCnpj", "CNPJ inválido"));
		}
		
		Cliente email = repo.findByEmail(objDto.getEmail());
		
		if(email != null) {
			
			list.add(new FieldMessage("email", "E-mail já existe, favor verificar!"));
		}
		
		for (FieldMessage e : list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName())
					.addConstraintViolation();
		}
		return list.isEmpty();
	}
}