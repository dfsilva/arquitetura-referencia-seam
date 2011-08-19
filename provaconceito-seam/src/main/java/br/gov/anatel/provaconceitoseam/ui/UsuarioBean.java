package br.gov.anatel.provaconceitoseam.ui;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Create;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;

import br.gov.anatel.provaconceitoseam.domain.Anexo;
import br.gov.anatel.provaconceitoseam.domain.Nota;
import br.gov.anatel.provaconceitoseam.domain.Usuario;
import br.gov.anatel.provaconceitoseam.model.NotaModel;
import br.gov.anatel.provaconceitoseam.model.iface.IUsuarioModel;

import com.google.common.collect.Lists;

@Name("usuarioBean")
@Scope(ScopeType.CONVERSATION)
public class UsuarioBean {

	/**
	 * Valores a serem checados em ação
	 */
	private List<String> valoresCheck = new ArrayList<String>();

	private List<String> valoresCheckSelecionado = new ArrayList<String>();

	private List<Anexo> anexos = new ArrayList<Anexo>();

	private int indice;

	private int indice2;

	private Nota nota = new Nota();

	private String colunas[];
	private String linhas[] = { "linha1", "linha2" };

	private List<Usuario> usuarios = Lists.newArrayList(new Usuario("Diego",
			"123456", "diego@diegosilva.com.br"), new Usuario("aaaaaaaaa2",
			"2222", "222222@2222.com"), new Usuario("aaaaaaaaa3", "3333",
			"333@333.com"));

	@Create
	public void inicializar() {
		for (Usuario u : usuarios) {
			u.setNotas(new ArrayList<Nota>());
			u.getNotas().add(
					new Nota("Nota 1 " + u.getNome(), "Texto da nota 1 "
							+ u.getNome()));
			u.getNotas().add(
					new Nota("Nota 2 " + u.getNome(), "Texto da nota 2"
							+ u.getNome()));
			u.getNotas().add(
					new Nota("Nota 3 " + u.getNome(), "Texto da nota 3"
							+ u.getNome()));
		}
	}

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

	public String[] getColunas() {
		List<String> retorno = new ArrayList<String>();
		
		for (Nota n : usuarios.get(0).getNotas()) {
			retorno.add(n.getTitulo());
		}
		return (String[]) retorno.toArray();
	}

	public void setColunas(String[] colunas) {
		this.colunas = colunas;
	}

	public String[] getLinhas() {
		return linhas;
	}

	public void setLinhas(String[] linhas) {
		this.linhas = linhas;
	}

	public List<Usuario> getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}

}
