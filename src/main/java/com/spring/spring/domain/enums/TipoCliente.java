package com.spring.spring.domain.enums;

public enum TipoCliente {


	PessoaFisica(0,"Pesso Física"),
	PessoJuridica(1, "Pessoa Jurídica");
	
	private int cod;
	private String descrico;
	
	private TipoCliente(int cod, String descricao) {
	
		this.cod = cod;
		this.descrico = descricao;
	}
	
	public int getCod() {
		return cod;
	}
	
	public String getDescricao() {
		return descrico;
	}
	
	public static TipoCliente toEnum(Integer cod) {
		if (cod == null) {
			return null;
		}
		for (TipoCliente x: TipoCliente.values()) {
			if (cod.equals(x.getCod())) {
				return x;
			}
			
		}
		
		throw new IllegalArgumentException("id invalido: " + cod);
		
	
	}
	
}
