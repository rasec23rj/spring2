package com.spring.spring.domain.enums;

public enum Perfil {

	
	ADMIN(1,"ROLE_ADMIN"),
	CLIENTE(2, "ROLE_CLIENTE");
	
	private int cod;
	private String descrico;
	
	private Perfil(int cod, String descricao) {
	
		this.cod = cod;
		this.descrico = descricao;
	}
	
	public int getCod() {
		return cod;
	}
	
	public String getDescricao() {
		return descrico;
	}
	
	public static Perfil toEnum(Integer cod) {
		if (cod == null) {
			return null;
		}
		for (Perfil x: Perfil.values()) {
			if (cod.equals(x.getCod())) {
				return x;
			}
			
		}
		
		throw new IllegalArgumentException("id invalido: " + cod);
		
	
	}
	
}
