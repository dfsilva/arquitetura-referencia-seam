package br.gov.anatel.provaconceitoseam.ui;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;

import br.com.diegosilva.infraseam.dto.DefaultDTO;
import br.com.diegosilva.infraseam.ui.SeamAbstractUI;
import br.gov.anatel.provaconceitoseam.domain.Usuario;

@Name("usuarioBean")
@Scope(ScopeType.CONVERSATION)
public class UsuarioBean extends SeamAbstractUI<Usuario, DefaultDTO<Usuario>> {

	public void gerarErro() {
		throw new RuntimeException("Erro causado propositalmente!!");
	}

}
