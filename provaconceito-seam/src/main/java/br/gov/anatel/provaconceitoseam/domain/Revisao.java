package br.gov.anatel.provaconceitoseam.domain;

import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.envers.RevisionEntity;
import org.hibernate.envers.RevisionNumber;
import org.hibernate.envers.RevisionTimestamp;

import br.com.diegosilva.infraseam.entity.DefaultEntity;

/**
 * Classe representante da Tabela TB_AUDITORIA.
 * 
 * @author dferreira
 */
@Entity
@Table(name = "TB_REVISAO")
@RevisionEntity(RevisaoListener.class)
public class Revisao extends DefaultEntity<Long> {

	/**
	 * serial uid.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * codigo.
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "NU_REVISAO")
	@RevisionNumber
	private Long nuRevisao;

	/**
	 * Numero matricula empregado.
	 */
	@Column(name = "CO_LOGON")
	private String loginUsuario;

	/**
	 * Variável noUsuario do tipo String.
	 */
	@Column(name = "NO_USUARIO")
	private String noUsuario;

	/**
	 * Numero matricula usuario.
	 */
	@Column(name = "NU_USUARIO")
	private Integer nuUsuario;

	/**
	 * data hora atualizacao.
	 */
	@Basic(optional = false)
	@RevisionTimestamp
	@Column(name = "DH_ATUALIZACAO")
	@Temporal(TemporalType.TIMESTAMP)
	private Date dhAtualizacao;

	/**
	 * construtor padrao.
	 */
	public Revisao() {
	}

	/**
	 * @param nuAuditoria
	 *            - long.
	 */
	public Revisao(final Long nuAuditoria) {
		this.nuRevisao = nuAuditoria;
	}

	/**
	 * @param nuAuditoria
	 *            - numero.
	 * @param icOperacaoRealizada
	 *            - enum.
	 * @param dhAtualizacao
	 *            - data.
	 */
	public Revisao(Long nuAuditoria, Date dhAtualizacao) {
		this.nuRevisao = nuAuditoria;
		this.dhAtualizacao = dhAtualizacao;
	}

	/**
	 * Retorna o Atributo <code>nuRevisao</code> do tipo <code>Long</code>.
	 * 
	 * @return the nuRevisao
	 */
	public Long getNuRevisao() {
		return nuRevisao;
	}

	/**
	 * Seta o Paramero <code>nuRevisao</code> recebido no atributo
	 * <code>nuRevisao</code> do tipo <code>Long</code>.
	 * 
	 * @param nuRevisao
	 *            the nuRevisao to set
	 */
	public void setNuRevisao(Long nuRevisao) {
		this.nuRevisao = nuRevisao;
	}

	/**
	 * @return nuUsuario - numero.
	 */
	public Integer getNuUsuario() {
		return nuUsuario;
	}

	/**
	 * @param nuUsuario
	 *            - numero.
	 */
	public void setNuUsuario(Integer nuUsuario) {
		this.nuUsuario = nuUsuario;
	}

	/**
	 * @return - dhAtualizacao - data.
	 */
	public Date getDhAtualizacao() {
		return dhAtualizacao;
	}

	/**
	 * @param dhAtualizacao
	 *            - data.
	 */
	public void setDhAtualizacao(Date dhAtualizacao) {
		this.dhAtualizacao = dhAtualizacao;
	}

	/**
	 * @see br.gov.anatel.arquitetura.referencia.pattern.entity.iface.Identifiable#setId(java.io.Serializable)
	 * @param id
	 *            - numero.
	 */
	public void setId(Long id) {
		setNuRevisao(id);

	}

	/**
	 * @see br.gov.anatel.arquitetura.referencia.pattern.entity.iface.Identifiable#getId()
	 * @return nuAuditoria - numero.
	 */
	public Long getId() {
		return getNuRevisao();
	}

	public String getLoginUsuario() {
		return loginUsuario;
	}

	public void setLoginUsuario(String loginUsuario) {
		this.loginUsuario = loginUsuario;
	}

	/**
	 * Metodo getter para a variavel noUsuario.
	 * 
	 * @return the noUsuario - noUsuario.
	 */
	public String getNoUsuario() {
		return noUsuario;
	}

	/**
	 * Metodo setter para o campo noUsuario.
	 * 
	 * @param noUsuario
	 *            the noUsuario to set noUsuario.
	 */
	public void setNoUsuario(String noUsuario) {
		this.noUsuario = noUsuario;
	}

}
