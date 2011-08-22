package br.com.diegosilva.infraseam.dto;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;

import br.com.diegosilva.infraseam.dto.iface.IDefaultDTO;
import br.com.diegosilva.infraseam.entity.iface.IDefaultEntity;

public class DefaultDTO<T extends IDefaultEntity<?>> implements IDefaultDTO<T> {

	private T value;
	private Class<T> entityClass;

	public T getValue() {
		if (value == null) {
			try {
				value = getEntityClass().newInstance();
			} catch (InstantiationException e) {
				throw new RuntimeException(e);
			} catch (IllegalAccessException e) {
				throw new RuntimeException(e);
			}
		}
		return value;
	}

	public void setValue(T value) {
		this.value = value;
	}

	@SuppressWarnings("unchecked")
	public Class<T> getEntityClass() {
		if (entityClass == null) {
			Type type = getClass().getGenericSuperclass();
			if (type instanceof ParameterizedType) {
				ParameterizedType paramType = (ParameterizedType) type;
				if (paramType.getActualTypeArguments().length == 2) {
					if (paramType.getActualTypeArguments()[0] instanceof TypeVariable) {
						throw new IllegalArgumentException(
								"Nao foi possivel encontrar classe de entidade por reflexao no DTO");
					} else {
						entityClass = (Class<T>) paramType
								.getActualTypeArguments()[0];
					}
				} else {
					entityClass = (Class<T>) paramType.getActualTypeArguments()[0];
				}
			} else {
				throw new IllegalArgumentException(
						"Nao foi possivel encontrar classe de entidade por reflexao no DTO");
			}
		}
		return entityClass;
	}

}
