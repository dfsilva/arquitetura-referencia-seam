package br.com.diegosilva.infraseam.entity;

import java.io.Serializable;

public interface DefaultEntity<E extends Serializable> {

	E getId();
}
