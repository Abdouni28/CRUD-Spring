package com.munir.crud_pessoa.entidades;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.hibernate.envers.Audited;

import com.munir.crud_pessoa.security.entidades.User;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@Audited
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "pessoa")
public class Person implements Serializable {

	private static final long serialVersionUID = -4253028561247952390L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
	private Long id;
	
	@Column(name = "nome")
	private String name;
	
	@Column(name = "cpf")
	private String cpf;
	
	@Column(name = "email")
	private String email;
	
	@Column(name = "data_nascimento")
	private LocalDate birthDate;
	
	@Column(name = "ativa")
	private Boolean active;
	
	@OneToOne(mappedBy = "person")
	private User user;
	
	@OneToMany(mappedBy = "person", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Address> addresses = new ArrayList<>();

	@OneToMany(mappedBy = "person", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Telephone> telephones = new ArrayList<>();

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
		Person other = (Person) obj;
		return Objects.equals(id, other.id);
	}
}