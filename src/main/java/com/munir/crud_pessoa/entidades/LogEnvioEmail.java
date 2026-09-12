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
@Table(name = "log_envio_emails")
@NoArgsConstructor
@AllArgsConstructor
public class LogEnvioEmail implements Serializable {

	private static final long serialVersionUID = 7825672115703082470L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
	private Long id;
	
	@Column(name = "destinatarios")
	private String destinatarios;
	
	@Column(name = "assunto")
	private String assunto;
	
	@Column(name = "corpo")
	private String corpo;
	
	@Column(name = "mensagem_erro")
	private String mensagemErro;
	
	@Column(name = "data_tentativa_envio")
	private LocalDateTime dataTentativaEnvio;

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
		LogEnvioEmail other = (LogEnvioEmail) obj;
		return Objects.equals(id, other.id);
	}
}