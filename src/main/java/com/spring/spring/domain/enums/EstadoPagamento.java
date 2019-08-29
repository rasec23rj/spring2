package com.spring.spring.domain.enums;

import com.spring.spring.service.exceptions.ObjectNotFoundException;

public enum EstadoPagamento {
	
	PEDENTE(0,"Pedente"),
	QUITADO(1, "Quitado"),
	CANCELADO(2, "Cancelado");
	
	private Integer id;
	private String descricao;
	
	private EstadoPagamento(Integer id, String descricao){
		this.id = id;
		this.descricao = descricao;
		
	}

	public Integer getId() {
		return id;
	}

	public String getDescricao() {
		return descricao;
	}
	
	public static EstadoPagamento toEnum(Integer id) {
		
		if(id == null) {
			return null;
						
		}
		
		for( EstadoPagamento x: EstadoPagamento.values()) {
			
			if(x.equals(x.getId())){
				return x;
			}
		}
		throw new ObjectNotFoundException("id invalido: " + id );
		
	}
	
}
