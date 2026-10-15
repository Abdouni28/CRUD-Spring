package com.munir.crud_pessoa.security.entidades;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Set;

import org.hibernate.envers.Audited;
import org.hibernate.envers.RelationTargetAuditMode;

import com.munir.crud_pessoa.entidades.Person;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
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
@Table(name = "usuario")
public class User implements Serializable {

	private static final long serialVersionUID = -8029204286517782809L;
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
	private Long id;
	
	@Column(name = "nome_usuario")
	private String username;
	
	@Column(name = "senha")
	private String password;
	
	@Column(name = "data_criacao")
	private LocalDateTime creationDate;
	
	@Column(name = "ativo")
	private Boolean active;
	
	@ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "usuario_perfil",
        	   joinColumns = @JoinColumn(name = "id_usuario"),
        	   inverseJoinColumns = @JoinColumn(name = "id_perfil"))
	@Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
    private Set<Profile> profiles;
	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_pessoa", referencedColumnName = "id")
	private Person person;

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
		User other = (User) obj;
		return Objects.equals(id, other.id);
	}
}