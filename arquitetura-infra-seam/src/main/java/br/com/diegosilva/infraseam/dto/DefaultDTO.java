package br.com.diegosilva.infraseam.dto;

import br.com.diegosilva.infraseam.dto.iface.IDefaultDTO;
import br.com.diegosilva.infraseam.entity.DefaultEntity;

public class DefaultDTO<T extends DefaultEntity<?>> implements IDefaultDTO<T> {

	private T value;

	public T getValue() {
		return value;
	}

	public void setValue(T value) {
		this.value = value;
	}

}
