package com.munir.crud_pessoa.entidades;

import java.io.Serializable;
import java.time.LocalDateTime;
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
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "log_envio_emails")
public class EmailDeliverLog implements Serializable {

	private static final long serialVersionUID = 7825672115703082470L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
	private Long id;
	
	@Column(name = "destinatarios")
	private String addressees;
	
	@Column(name = "assunto")
	private String subject;
	
	@Column(name = "corpo")
	private String content;
	
	@Column(name = "mensagem_erro")
	private String errorMessage;
	
	@Column(name = "data_tentativa_envio")
	private LocalDateTime deliverAttemptDate;

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
		EmailDeliverLog other = (EmailDeliverLog) obj;
		return Objects.equals(id, other.id);
	}
}