package com.munir.crud_pessoa.listeners;

import org.hibernate.envers.RevisionListener;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import com.munir.crud_pessoa.entidades.Audit;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class AuditListener implements RevisionListener {
	
	private final HttpServletRequest request;

	@Override
	public void newRevision(Object revisionEntity) {

		Audit audit = (Audit) revisionEntity;
		
		String ipAddress = getIpAddress();
		String user = getUser();

		audit.setIpAddress(ipAddress);
		audit.setAuthor(user);
	}
	
	private String getIpAddress() {
		
		String enderecoIp = request.getHeader("X-Forwarded-For");
		
		if (enderecoIp == null || enderecoIp.isEmpty()) {
			enderecoIp = request.getRemoteAddr();
		}
		
		return enderecoIp;
	}

	private String getUser() {
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

	    if (authentication == null || !authentication.isAuthenticated()) {
	        return null;
	    }
	    
	    return authentication.getName();
	}
}