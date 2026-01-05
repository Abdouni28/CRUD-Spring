package com.munir.crud_pessoa.entidades;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

import com.munir.crud_pessoa.enums.SimNaoEnum;

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
//@Audited
@Entity
@Table(name = "pessoa")
@NoArgsConstructor
@AllArgsConstructor
public class Pessoa implements Serializable {

	private static final long serialVersionUID = -4253028561247952390L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
	private Long id;
	
	@Column(name = "nome")
	private String nome;
	
	@Column(name = "cpf")
	private String cpf;
	
	@Column(name = "email")
	private String email;
	
	@Column(name = "data_nascimento")
	private LocalDate dataNascimento;
	
	@Column(name = "fl_ativo")
	private String flagAtivo;
	
	/*
	 * @OneToMany(mappedBy = "pessoa", fetch = FetchType.EAGER) private
	 * List<Endereco> enderecos = new ArrayList<>();
	 * 
	 * @OneToMany(mappedBy = "pessoa", fetch = FetchType.EAGER) private
	 * List<Telefone> telefones = new ArrayList<>();
	 */
	
	public Boolean isAtivo() {
		
		return this.flagAtivo.equalsIgnoreCase(SimNaoEnum.SIM.getFlag());
	}

	@Override
	public int hashCode() {
		return Objects.hash(cpf, dataNascimento, email, flagAtivo, id, nome);
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
		return Objects.equals(cpf, other.cpf) && Objects.equals(dataNascimento, other.dataNascimento)
				&& Objects.equals(email, other.email) && Objects.equals(flagAtivo, other.flagAtivo)
				&& Objects.equals(id, other.id) && Objects.equals(nome, other.nome);
	}
}
