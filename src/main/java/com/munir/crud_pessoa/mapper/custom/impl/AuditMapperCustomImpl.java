package com.munir.crud_pessoa.mapper.custom.impl;

import java.lang.reflect.Field;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.envers.RevisionType;
import org.springframework.stereotype.Component;

import com.munir.crud_pessoa.dtos.response.AuditModificationsDTO;
import com.munir.crud_pessoa.dtos.response.AuditResponseDTO;
import com.munir.crud_pessoa.entidades.Audit;
import com.munir.crud_pessoa.enums.AuditOperationTypeENUM;
import com.munir.crud_pessoa.mapper.custom.AuditMapperCustom;
import com.munir.crud_pessoa.utils.ReflexaoUtils;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class AuditMapperCustomImpl implements AuditMapperCustom {
	
	@Override
	@SuppressWarnings("unchecked")
	public <responseDTO, P> responseDTO toResponseDTO(P param) {

		List<Object[]> revisions = (List<Object[]>) param;
		
		if(revisions.size() == 1) {
			
			RevisionType operationType = (RevisionType) revisions.get(0)[2];
			
			if(operationType == RevisionType.MOD) {
				
				return (responseDTO) new ArrayList<>();
				
			} else {
				
				AuditResponseDTO responseDTO = mapAudit(null, revisions.get(0));
				
				return (responseDTO) List.of(responseDTO);
			}
		}

		AuditResponseDTO firstAuditResponseDTO = mapAudit(null, revisions.get(0));
		
		List<AuditResponseDTO> responseDTO = new ArrayList<>();		
		responseDTO.add(firstAuditResponseDTO);
		
		Object[] previousRevision = revisions.remove(0);
		
		for(Object[] currentRevision : revisions) {
			
			AuditResponseDTO auditoriaResponseDTO = mapAudit(previousRevision, currentRevision);
			
			responseDTO.add(auditoriaResponseDTO);
			
			previousRevision = currentRevision;
		}
		
		return (responseDTO) responseDTO;
	}
	
	private AuditResponseDTO mapAudit(Object[] previousRevision, Object[] currentRevision) {
		
		Audit audit = (Audit) currentRevision[1];
		
		Long revisionId = Long.valueOf(audit.getId());
		LocalDateTime revisionDate = Instant.ofEpochMilli(audit.getTimestamp()).atZone(ZoneId.systemDefault()).toLocalDateTime();
		String operationType = AuditOperationTypeENUM.fromRevisionType((RevisionType) currentRevision[2]).getDescription();
		String ipAddress = audit.getIpAddress();
		String author = audit.getAuthor();
		
		List<AuditModificationsDTO> modifications = mapModifications(previousRevision, currentRevision);
		
		AuditResponseDTO responseDTO = new AuditResponseDTO(revisionId, revisionDate, operationType, ipAddress, author, modifications);
		
		return responseDTO;
	}
	
	private List<AuditModificationsDTO> mapModifications(Object[] previousRevision, Object[] currentRevision) {

		List<AuditModificationsDTO> modifications = new ArrayList<>();
		
		if(previousRevision != null && currentRevision != null) {
			
			Object previousEntity = previousRevision[0];
			Object currentEntity = currentRevision[0];
			
			Class<?> clazz = currentEntity.getClass();
			
			for(Field field : clazz.getDeclaredFields()) {
				
				field.setAccessible(true);
				
				if(ReflexaoUtils.isNativeField(field)) {
					
					try {
						
						Object oldValue = field.get(previousEntity);
						Object newValue = field.get(currentEntity);
						
						if((oldValue == null && newValue != null) || (oldValue != null && !oldValue.equals(newValue))) {
							
							AuditModificationsDTO modification = new AuditModificationsDTO(field.getName(), oldValue.toString(), newValue.toString());
							modifications.add(modification);
						}
						
					} catch (IllegalAccessException e) {
						
						e.printStackTrace();
					}
				}				
			}
		}
		
		return modifications;
	}
}