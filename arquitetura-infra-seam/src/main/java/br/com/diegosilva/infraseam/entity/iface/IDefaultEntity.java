package br.com.diegosilva.infraseam.entity.iface;

import java.io.Serializable;

public interface IDefaultEntity<E extends Serializable> extends Serializable {

	E getId();
}
