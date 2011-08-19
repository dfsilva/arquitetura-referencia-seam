package br.gov.anatel.provaconceitoseam.model;

import org.jboss.seam.annotations.AutoCreate;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.TransactionPropagationType;
import org.jboss.seam.annotations.Transactional;

import br.gov.anatel.provaconceitoseam.dao.UsuarioDAO;
import br.gov.anatel.provaconceitoseam.dto.UsuarioDTO;
import br.gov.anatel.provaconceitoseam.model.iface.IUsuarioModel;

@Name("usuarioModel")
@AutoCreate
@Transactional(TransactionPropagationType.SUPPORTS)
public class UsuarioModel implements IUsuarioModel {

	/**
	 * Variavel serialVersionUID do tipo long.
	 */
	private static final long serialVersionUID = 1L;

	@In(create = true)
	private UsuarioDAO usuarioDao;

	@Override
	public void saveWithRoolBack(UsuarioDTO dto) {
		// TODO Auto-generated method stub

	}

}
