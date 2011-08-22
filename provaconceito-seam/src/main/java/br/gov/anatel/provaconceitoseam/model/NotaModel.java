package br.gov.anatel.provaconceitoseam.model;

import org.jboss.seam.annotations.AutoCreate;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.TransactionPropagationType;
import org.jboss.seam.annotations.Transactional;

import br.com.diegosilva.infraseam.model.GenericSeamModel;
import br.gov.anatel.provaconceitoseam.model.iface.INotaModel;

@Name("notaModel")
@AutoCreate
@Transactional(TransactionPropagationType.SUPPORTS)
public class NotaModel extends GenericSeamModel implements INotaModel {

	/**
	 * Serial UID.
	 */
	private static final long serialVersionUID = 1L;

}
