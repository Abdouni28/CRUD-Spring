package com.munir.crud_pessoa.listeners;

import org.hibernate.envers.RevisionListener;
import org.springframework.stereotype.Component;

import com.munir.crud_pessoa.entidades.Revisao;

import jakarta.persistence.PrePersist;
import jakarta.persistence.PreRemove;
import jakarta.persistence.PreUpdate;
import jakarta.servlet.http.HttpServletRequest;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Component
@RequiredArgsConstructor
public class RevisaoListener implements RevisionListener{
	
	@NonNull
	private HttpServletRequest request;

	@Override
	@PrePersist
    @PreUpdate
    @PreRemove
	public void newRevision(Object objeto) {

		Revisao revisao = (Revisao) objeto;
		revisao.setIpAddress(request.getRemoteAddr());		
	}
}