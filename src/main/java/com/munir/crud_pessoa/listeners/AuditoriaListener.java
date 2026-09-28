package com.munir.crud_pessoa.listeners;

import org.hibernate.envers.RevisionListener;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import com.munir.crud_pessoa.entidades.Auditoria;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class AuditoriaListener implements RevisionListener {
	
	private final HttpServletRequest request;

	@Override
	public void newRevision(Object revisionEntity) {

		Auditoria auditoria = (Auditoria) revisionEntity;
		
		String usuarioLogado = getUsuarioLogado();
		String enderecoIp = getEnderecoIp();
		
		auditoria.setAutor(usuarioLogado);
		auditoria.setEnderecoIp(enderecoIp);
	}
	
	private String getUsuarioLogado() {
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

	    if (authentication == null || !authentication.isAuthenticated()) {
	        return null;
	    }
	    
	    return authentication.getName();
	}
	
	private String getEnderecoIp() {
		
		String enderecoIp = request.getHeader("X-Forwarded-For");
		
		if (enderecoIp == null || enderecoIp.isEmpty()) {
			enderecoIp = request.getRemoteAddr();
		}
		
		return enderecoIp;
	}
}
