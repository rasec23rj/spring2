package com.spring.spring.dto;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import com.spring.spring.domain.Produto;

public class ProdutoDTO implements Serializable {


	private static final long serialVersionUID = 1L;
	
	private Integer id;
	@NotEmpty(message="Preenchimento obrigatório")
	@Length(min=5,max=80, message="Preenchimento deve ser entre 5 a 80 caracteres")
	private String name;
	
	private Double preco;
	
	public ProdutoDTO(){
		
	}
	
	public ProdutoDTO(Produto obj) {
		id = obj.getId();
		name = obj.getName();
		preco = obj.getPreco();
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

	public Double getPreco() {
		return preco;
	}

	public void setPreco(Double preco) {
		this.preco = preco;
	}
	
	


	
}
