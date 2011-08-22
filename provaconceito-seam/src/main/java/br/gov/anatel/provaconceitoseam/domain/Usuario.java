package br.gov.anatel.provaconceitoseam.domain;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.envers.Audited;
import org.hibernate.validator.Length;
import org.hibernate.validator.NotNull;

import br.com.diegosilva.infraseam.entity.DefaultEntity;

@Entity
@Table(name = "Usuario")
@Audited
@EntityListeners(value = AuditListener.class)
public class Usuario extends DefaultEntity<Integer> implements ILogable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", nullable = false)
	private Integer id;

	@Column(name = "nome", nullable = false)
	@NotNull
	private String nome;

	@Column(name = "senha", nullable = false, length = 32)
	@NotNull
	@Length(max = 32)
	private String senha;

	@Column(name = "email", nullable = false, length = 100)
	@NotNull
	@Length(max = 100)
	private String email;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Nota> notas = new ArrayList<Nota>(0);

	public Usuario() {
	}

	public Usuario(Integer id) {
		this.id = id;
	}

	public Usuario(String nome, String senha, String email) {
		this.nome = nome;
		this.senha = senha;
		this.email = email;
	}

	public Usuario(String nome, String senha, String email, List<Nota> notas) {
		this.nome = nome;
		this.senha = senha;
		this.email = email;
		this.notas = notas;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSenha() {
		return this.senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public List<Nota> getNotas() {
		return this.notas;
	}

	public void setNotas(List<Nota> notas) {
		this.notas = notas;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (getId() != null ? getId().hashCode() : 0);
		return hash;
	}

	/**
	 * @see br.gov.anatel.arquitetura.referencia.util
	 *      .common.BaseObject#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object object) {
		if (!(object instanceof Usuario)) {
			return false;
		}
		Usuario other = (Usuario) object;
		if ((this.getId() == null && other.getId() != null)
				|| (this.getId() != null && !this.getId().equals(other.getId()))) {
			return false;
		}
		return true;
	}

	@Override
	public String getFuncionalidade() {
		return "Manter Usuário";
	}

}
