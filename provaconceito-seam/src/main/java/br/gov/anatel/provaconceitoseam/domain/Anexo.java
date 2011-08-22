package br.gov.anatel.provaconceitoseam.domain;

import java.io.Serializable;

public class Anexo implements Serializable {

	private static final long serialVersionUID = 1L;
	private byte[] data;
	private String tipo;
	private String nome;
	private long lenght;

	public byte[] getData() {
		return data;
	}

	public void setData(byte[] data) {
		this.data = data;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public long getLenght() {
		return lenght;
	}

	public void setLenght(long lenght) {
		this.lenght = lenght;
	}

}
