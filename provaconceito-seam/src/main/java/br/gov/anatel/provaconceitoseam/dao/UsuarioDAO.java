package br.gov.anatel.provaconceitoseam.dao;

import org.jboss.seam.annotations.AutoCreate;
import org.jboss.seam.annotations.Name;

import br.com.diegosilva.infraseam.dao.GenericDAO;
import br.gov.anatel.provaconceitoseam.dao.iface.IUsuarioDAO;

@Name("usuarioDao")
@AutoCreate
public class UsuarioDAO extends GenericDAO implements IUsuarioDAO {

}
