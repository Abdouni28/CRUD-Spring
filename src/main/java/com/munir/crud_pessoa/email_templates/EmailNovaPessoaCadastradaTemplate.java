package com.munir.crud_pessoa.email_templates;

import java.util.List;

import com.munir.crud_pessoa.dtos.response.PessoaResponseDTO;
import com.munir.crud_pessoa.enums.EmailsENUM;

public class EmailNovaPessoaCadastradaTemplate extends EmailTemplate {

	private final PessoaResponseDTO pessoa;
	
	public EmailNovaPessoaCadastradaTemplate(PessoaResponseDTO pessoa) {

		this.pessoa = pessoa;
	}
	
	@Override
	protected List<String> getDestinatarios(){
		return List.of(pessoa.email());
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
	protected String preencherParametrosCorpo(String corpoEmail) {
		
		corpoEmail = corpoEmail.replace(":nome", pessoa.nome());
		corpoEmail = corpoEmail.replace(":usuario", pessoa.usuario().nomeUsuario());
		
		return corpoEmail;
	}
}
