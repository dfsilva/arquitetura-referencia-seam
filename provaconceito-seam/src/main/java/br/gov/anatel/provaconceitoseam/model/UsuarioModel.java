package br.gov.anatel.provaconceitoseam.model;

import org.jboss.seam.annotations.AutoCreate;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.TransactionPropagationType;
import org.jboss.seam.annotations.Transactional;

import br.com.diegosilva.infraseam.model.GenericSeamModel;
import br.gov.anatel.provaconceitoseam.model.iface.IUsuarioModel;

@Name("usuarioModel")
@AutoCreate
@Transactional(TransactionPropagationType.SUPPORTS)
public class UsuarioModel extends GenericSeamModel implements IUsuarioModel {

	private static final long serialVersionUID = 1L;

}
