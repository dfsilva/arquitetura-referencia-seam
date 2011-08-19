package br.gov.anatel.provaconceitoseam.domain;

import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

public class AuditListener {

	@PrePersist
	@PreUpdate
	public void auditar(Usuario usuario) {
		System.out.println(usuario);
	}

}
