package com.munir.crud_pessoa.emails;

import java.util.List;

import com.munir.crud_pessoa.enums.EmailsENUM;

public class EmailNovaPessoaCadastrada extends Email {

	private final String nomePessoa;
	
	private final String nomeUsuario;
	
	private final String senha;
	
	public EmailNovaPessoaCadastrada(String nomePessoa, String nomeUsuario, String senha, List<String> destinatarios) {
		super(destinatarios);
		this.nomePessoa = nomePessoa;
		this.nomeUsuario = nomeUsuario;
		this.senha = senha;
	}

	@Override
	protected String getAssunto() {
		
		return EmailsENUM.NOVA_PESSOA_CADASTRADA.getAssunto();
	}

	@Override
	protected String getCorpo() {

		String corpo = EmailsENUM.NOVA_PESSOA_CADASTRADA.getCorpoEmail();
		corpo = preencherParametrosCorpo(corpo);
		
		return corpo;
	}

	@Override
	protected String preencherParametrosCorpo(String corpo) {
		
		corpo = corpo.replace(":nome", nomePessoa);
		corpo = corpo.replace(":usuario", nomeUsuario);
		corpo = corpo.replace(":senha", senha);
		
		return corpo;
	}
}
