package com.munir.curso_spring.entidades;

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

@Entity
@Table(name = "pessoa")
@AllArgsConstructor
@NoArgsConstructor
public class Pessoa implements Serializable{

	private static final long serialVersionUID = -6620971587914368462L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_pessoa")
	@Getter
	@Setter
	private Long id;
	
	@Column(name = "nome_pessoa")
	@Getter
	@Setter
	private String nome;
	
	@Column(name = "endereco_pessoa")
	@Getter
	@Setter
	private String endereco;

	@Override
	public int hashCode() {
		return Objects.hash(id, nome, endereco);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Pessoa other = (Pessoa) obj;
		return Objects.equals(id, other.id);
	}
}
