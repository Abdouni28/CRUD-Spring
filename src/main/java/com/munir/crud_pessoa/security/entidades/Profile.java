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
public class Profile implements Serializable {

	private static final long serialVersionUID = -5308118885492662103L;
	
	public static final String ROLE_ADMIN = "ROLE_ADMIN";
	public static final String ROLE_PERSON = "ROLE_PERSON";

	@Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "nome")
	private String name;

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
		Profile other = (Profile) obj;
		return Objects.equals(id, other.id);
	}
	
	@Getter
	@AllArgsConstructor
	public enum ProfileENUM {
		ADMIN(1L, "ADMIN"),
		PERSON(2L, "PERSON");
		
		private final Long id;
		private final String name;
	}
}