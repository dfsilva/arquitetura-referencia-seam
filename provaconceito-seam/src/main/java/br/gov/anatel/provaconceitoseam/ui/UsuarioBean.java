package br.gov.anatel.provaconceitoseam.ui;

import java.util.ArrayList;
import java.util.List;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;

import br.com.diegosilva.infraseam.dto.DefaultDTO;
import br.com.diegosilva.infraseam.ui.SeamAbstractUI;
import br.gov.anatel.provaconceitoseam.domain.Anexo;
import br.gov.anatel.provaconceitoseam.domain.Nota;
import br.gov.anatel.provaconceitoseam.domain.Usuario;
import br.gov.anatel.provaconceitoseam.model.NotaModel;
import br.gov.anatel.provaconceitoseam.model.iface.IUsuarioModel;

@Name("usuarioBean")
@Scope(ScopeType.CONVERSATION)
public class UsuarioBean extends SeamAbstractUI<Usuario, DefaultDTO<Usuario>> {

	/**
	 * Valores a serem checados em ação
	 */
	private List<String> valoresCheck = new ArrayList<String>();

	private List<String> valoresCheckSelecionado = new ArrayList<String>();

	private List<Anexo> anexos = new ArrayList<Anexo>();

	private int indice;

	private int indice2;

	private Nota nota = new Nota();

	@In("notaModel")
	private NotaModel notaModel;

	@In("usuarioModel")
	private IUsuarioModel usuarioModel;

	public List<String> getValoresCheck() {
		return valoresCheck;
	}

	public void setValoresCheck(List<String> valoresCheck) {
		this.valoresCheck = valoresCheck;
	}

	public List<String> getValoresCheckSelecionado() {
		return valoresCheckSelecionado;
	}

	public void setValoresCheckSelecionado(List<String> valoresCheckSelecionado) {
		this.valoresCheckSelecionado = valoresCheckSelecionado;
	}

	public List<Anexo> getAnexos() {
		return anexos;
	}

	public void setAnexos(List<Anexo> anexos) {
		this.anexos = anexos;
	}

	public int getIndice() {
		return indice;
	}

	public void setIndice(int indice) {
		this.indice = indice;
	}

	public int getIndice2() {
		return indice2;
	}

	public void setIndice2(int indice2) {
		this.indice2 = indice2;
	}

	public Nota getNota() {
		return nota;
	}

	public void setNota(Nota nota) {
		this.nota = nota;
	}

	public NotaModel getNotaModel() {
		return notaModel;
	}

	public void setNotaModel(NotaModel notaModel) {
		this.notaModel = notaModel;
	}

	public IUsuarioModel getUsuarioModel() {
		return usuarioModel;
	}

	public void setUsuarioModel(IUsuarioModel usuarioModel) {
		this.usuarioModel = usuarioModel;
	}
}
