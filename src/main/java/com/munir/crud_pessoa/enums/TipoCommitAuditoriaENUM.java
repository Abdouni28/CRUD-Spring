package com.munir.crud_pessoa.enums;

import lombok.Getter;

@Getter
public enum TipoCommitAuditoriaENUM {
	
	INSERCAO("INITIAL", "INSERÇÃO"),
	ALTERACAO("UPDATE", "ALTERAÇÃO");
	
	private String valorAuditoria;
	
	private String valorExibicao;

	TipoCommitAuditoriaENUM(String valorAuditoria, String valorExibicao) {
		
		this.valorAuditoria = valorAuditoria;
		this.valorExibicao = valorExibicao;
	}
	
	public static String resolverParaExibicao(String valorAuditoria) {
		
		for(TipoCommitAuditoriaENUM tipo : TipoCommitAuditoriaENUM.values()) {
			
			if(tipo.getValorAuditoria().equals(valorAuditoria))
				return tipo.getValorExibicao();
		}
		
		return null;
	}
}
