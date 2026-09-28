package com.munir.crud_pessoa.entidades;

import java.io.Serializable;
import java.util.Objects;

import org.hibernate.envers.RevisionEntity;
import org.hibernate.envers.RevisionNumber;
import org.hibernate.envers.RevisionTimestamp;

import com.munir.crud_pessoa.listeners.AuditoriaListener;

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
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "revinfo")
@RevisionEntity(AuditoriaListener.class)
public class Auditoria implements Serializable {

	private static final long serialVersionUID = 751273159076504850L;

    @Id
    @RevisionNumber
    @Column(name = "REV")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @RevisionTimestamp
    @Column(name = "REVTSTMP")
    private Long timestamp;

    @Column(name = "AUTOR")
    private String autor;

    @Column(name = "ENDERECO_IP")
    private String enderecoIp;

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
		Auditoria other = (Auditoria) obj;
		return Objects.equals(id, other.id);
	}
}