package com.munir.crud_pessoa.security.entidades;

import java.io.Serializable;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "perfil")
@NoArgsConstructor
@AllArgsConstructor
public class Perfil implements Serializable {

	private static final long serialVersionUID = -5308118885492662103L;
	
	public static final String ROLE_ADMIN = "ROLE_ADMIN";
	public static final String ROLE_PESSOA = "ROLE_PESSOA";

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
	private Long id;
	
	@Column(name = "nome")
	private String nome;

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Perfil other = (Perfil) obj;
		return Objects.equals(id, other.id);
	}
	
	@Getter
	@AllArgsConstructor
	public enum PerfilENUM {
		ADMIN(1L, "ADMIN"),
		PESSOA(2L, "PESSOA");
		
		private final Long id;
		private final String nome;
	}
}