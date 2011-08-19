package br.gov.anatel.provaconceitoseam.domain;

import java.util.Date;

import org.hibernate.envers.RevisionListener;

/**
 * Classe RevisaoListener.java, listener ao ser executado no momento do log.
 * 
 * @author diego.dba
 * @since 12/01/2011
 */
public class RevisaoListener implements RevisionListener {

	/**
	 * Metodo invocado no memento do log.
	 * 
	 * @see org.hibernate.envers.RevisionListener#newRevision(java.lang.Object)
	 * @param revisionEntity
	 *            - Entidade Revisao.
	 */
	@Override
	public void newRevision(Object revisionEntity) {
		Revisao auditoria = (Revisao) revisionEntity;
		auditoria.setNuUsuario(0);
		auditoria.setLoginUsuario("SISTEMA");
		auditoria.setNoUsuario("SISTEMA");
		auditoria.setDhAtualizacao(new Date());
	}

}
