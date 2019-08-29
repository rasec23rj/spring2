
package com.spring.spring.dto;

import java.io.Serializable;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import com.spring.spring.domain.Cliente;
import com.spring.spring.service.validation.ClienteUpdate;

@ClienteUpdate
public class ClienteDTO implements Serializable {


	private static final long serialVersionUID = 1L;
	
	private Integer id;
	
	@NotEmpty(message="Preenchimento obrigatório")
	@Length(min=5,max=120, message="Preenchimento deve ser entre 5 a 80 caracteres")
	private String name;
	
	@NotEmpty(message="Preenchimento obrigatório")
	@Email(message="Email invalido")
	private String email;
		
	public ClienteDTO(){
		
	}

	public ClienteDTO(Cliente obj) {
		super();
		id = obj.getId();
		name = obj.getEmail();
		email = obj.getEmail();
		
	}

	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


 
}

