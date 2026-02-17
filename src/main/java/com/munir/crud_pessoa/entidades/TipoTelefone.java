package com.munir.crud_pessoa.entidades;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
//@Audited
@Entity
@Table(name = "tipo_telefone")
@NoArgsConstructor
@AllArgsConstructor
public class TipoTelefone implements Serializable {

	private static final long serialVersionUID = 8692803294818155594L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
	private Long id;
	
	@Column(name = "descricao")
	private String descricao;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "tipoTelefone")
    private List<Telefone> telefones = new ArrayList<>();

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
		TipoTelefone other = (TipoTelefone) obj;
		return Objects.equals(id, other.id);
	}
}
