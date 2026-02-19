package com.munir.crud_pessoa.entidades;

import java.io.Serializable;

import org.hibernate.envers.DefaultRevisionEntity;
import org.hibernate.envers.RevisionEntity;

import com.munir.crud_pessoa.listeners.RevisaoListener;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@RevisionEntity
@Table(name = "revinfo")
@NoArgsConstructor
@EntityListeners(RevisaoListener.class)
public class Revisao extends DefaultRevisionEntity implements Serializable{

	private static final long serialVersionUID = 8807971960352867401L;
	
	@Column(name = "ip_address")
    private String ipAddress;
	
	@Getter
	@NoArgsConstructor
	@AllArgsConstructor
	public enum RevisionEntityProperties {
		
		//Colocando os nomes dos campos da entidade de revisão como ENUM
		//para poder usar em possíveis casos de ordenação ou filtros na query de revisão.
		DATA_ALTERACAO("timestamp");
		
		private String valor;
	}
}