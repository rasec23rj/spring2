package com.spring.spring.dto;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import com.spring.spring.domain.Pedido;

public class PedidoDTO implements Serializable {


	private static final long serialVersionUID = 1L;
	
	private Integer id;
	@NotEmpty(message="Preenchimento obrigatório")
	@Length(min=5,max=80, message="Preenchimento deve ser entre 5 a 80 caracteres")
	private String instante;

	public PedidoDTO(){
		
	}

	public PedidoDTO(Integer id, String instante) {
		super();
		this.id = id;
		this.instante = instante;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getInstante() {
		return instante;
	}

	public void setInstante(String instante) {
		this.instante = instante;
	}
	
	
	


	
}
