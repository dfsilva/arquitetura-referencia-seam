package br.com.diegosilva.infraseam.ui;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.commons.lang.exception.ExceptionUtils;
import org.jboss.seam.annotations.Create;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Logger;
import org.jboss.seam.annotations.datamodel.DataModel;
import org.jboss.seam.log.Log;

import br.com.diegosilva.infraseam.dto.DefaultDTO;
import br.com.diegosilva.infraseam.entity.iface.IDefaultEntity;
import br.com.diegosilva.infraseam.exception.ValidateException;
import br.com.diegosilva.infraseam.model.iface.IGenericSeamModel;
import br.com.diegosilva.infraseam.util.Constantes;
import br.com.diegosilva.infraseam.util.FacesUtil;

import com.google.common.collect.Lists;

public abstract class SeamAbstractUI<E extends IDefaultEntity<?>, T extends DefaultDTO<E>> {

	private Class<E> entityClass;
	private Class<T> dtoClass;

	@DataModel
	private List<E> list;

	private T data;

	private E objPesquisa;

	@In("genericSeamModel")
	private IGenericSeamModel genericModel;

	private List<Object[]> revisoes = new ArrayList<Object[]>();

	@Logger
	protected Log log;

	@Create
	public void create() {
		log.debug("Criando instancia da classe: " + this.getClass());
		if (getEntityClass() == null) {
			log.error("Classe de Entidade e nulla");
			throw new IllegalStateException("Classe de Entidade e nulla");
		}
		if (getDtoClass() == null) {
			log.error("Classe de Entidade e nulla");
			throw new IllegalStateException("Classe de DTO e nulla");
		}
	}

	public List<E> findAll() {
		try {
			this.list = (List<E>) getGenericModel().findAll(
					getData().getValue());
			return list;
		} catch (Exception e) {
			log.error("Erro ao buscar todos os elementos", e);
			FacesUtil
					.addErrorMessageHour("Ocorreu um erro ao buscar todos os elementos"
							+ ExceptionUtils.getRootCauseMessage(e));
			return Collections.emptyList();
		}
	}

	public String save() {
		try {
			getGenericModel().save(getData().getValue());
			return Constantes.Navegacao.SALVO;
		} catch (Exception e) {
			log.error("Erro ao salvar elemento", e);
			FacesUtil.addErrorMessageHour("Erro ao salvar elemento"
					+ ExceptionUtils.getRootCauseMessage(e));
			return Constantes.Navegacao.MESMA;
		}
	}

	public String saveWithValidation() {
		try {
			getGenericModel().saveWithValidation(getData().getValue());
			return Constantes.Navegacao.SALVO;
		} catch (ValidateException e) {
			e.montarMensagenJsf();
			return Constantes.Navegacao.MESMA;
		} catch (Exception e) {
			log.error("Erro ao salvar elemento", e);
			FacesUtil.addErrorMessageHour("Erro ao salvar elemento"
					+ ExceptionUtils.getRootCauseMessage(e));
			return Constantes.Navegacao.MESMA;
		}
	}

	public void findAllByExample() {
		try {
			this.list = (List<E>) getGenericModel().findByExampleWithParams(
					getObjPesquisa(), true, false, true);
		} catch (Exception e) {
			log.error("Erro ao salvar elemento", e);
			FacesUtil.addErrorMessageHour("Erro ao salvar elemento"
					+ ExceptionUtils.getRootCauseMessage(e));
		}
	}

	public String delete() {
		try {
			getGenericModel().delete(getData().getValue());
			if (getList() != null && !getList().isEmpty()) {
				getList().remove(getData().getValue());
			}
			return Constantes.Navegacao.EXCLUIDO;
		} catch (Exception e) {
			log.error("Erro ao excluir elemento", e);
			FacesUtil.addErrorMessageHour("Erro ao excluir elemento"
					+ ExceptionUtils.getRootCauseMessage(e));
			return Constantes.Navegacao.MESMA;
		}
	}

	public void clear() {
		try {
			setData(getDtoClass().newInstance());
		} catch (InstantiationException e) {
			log.error("Erro ao limpar objeto ", e);
			FacesUtil.addErrorMessageHour("Erro ao limpar objeto"
					+ ExceptionUtils.getRootCauseMessage(e));
		} catch (IllegalAccessException e) {
			log.error("Erro ao limpar objeto ", e);
			FacesUtil.addErrorMessageHour("Erro ao limpar objeto"
					+ ExceptionUtils.getRootCauseMessage(e));
		}
	}

	public void clearList() {
		this.list = Lists.newArrayList();
	}

	public void clearEntity() {
		try {
			getData().setValue(getEntityClass().newInstance());
		} catch (InstantiationException e) {
			log.error("Erro ao limpar entidade ", e);
			FacesUtil.addErrorMessageHour("Erro ao limpar entidade"
					+ ExceptionUtils.getRootCauseMessage(e));
		} catch (IllegalAccessException e) {
			log.error("Erro ao limpar objeto ", e);
			FacesUtil.addErrorMessageHour("Erro ao limpar entidade"
					+ ExceptionUtils.getRootCauseMessage(e));
		}
	}

	/**
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public Class<E> getEntityClass() {
		if (entityClass == null) {
			Type type = getClass().getGenericSuperclass();
			if (type instanceof ParameterizedType) {
				ParameterizedType paramType = (ParameterizedType) type;
				if (paramType.getActualTypeArguments().length == 2) {
					if (paramType.getActualTypeArguments()[0] instanceof TypeVariable) {
						throw new IllegalArgumentException(
								"Nao foi possivel encontrar classe de entidade por reflexao");
					} else {
						entityClass = (Class<E>) paramType
								.getActualTypeArguments()[0];
					}
				} else {
					entityClass = (Class<E>) paramType.getActualTypeArguments()[0];
				}
			} else {
				throw new IllegalArgumentException(
						"Nao foi possivel encontrar classe de entidade por reflexao");
			}
		}
		return entityClass;
	}

	@SuppressWarnings("unchecked")
	public Class<T> getDtoClass() {
		if (dtoClass == null) {
			Type type = getClass().getGenericSuperclass();
			if (type instanceof ParameterizedType) {
				ParameterizedType paramType = (ParameterizedType) type;
				if (paramType.getActualTypeArguments().length == 2) {
					if (paramType.getActualTypeArguments()[1] instanceof TypeVariable) {
						throw new IllegalArgumentException(
								"Nao foi possivel encontrar classe de entidade por reflexao");
					} else {
						try {
							dtoClass = (Class<T>) paramType
									.getActualTypeArguments()[1];
						} catch (Exception e) {
							dtoClass = (Class<T>) ((ParameterizedType) paramType
									.getActualTypeArguments()[1]).getRawType();
						}
					}
				} else {
					dtoClass = (Class<T>) paramType.getActualTypeArguments()[1];
				}
			} else {
				throw new IllegalArgumentException(
						"Nao foi possivel encontrar classe de entidade por reflexao");
			}
		}
		return dtoClass;
	}

	/**
	 * Metodo getter para a variavel data.
	 * 
	 * @return the data - data.
	 */
	public T getData() {
		if (data == null) {
			try {
				data = getDtoClass().newInstance();
			} catch (InstantiationException e) {
				log.error(e);
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				log.error(e);
			}
		}
		return data;
	}

	/**
	 * Metodo setter para o campo data.
	 * 
	 * @param data
	 *            the data to set data.
	 */
	public void setData(T data) {
		this.data = data;
	}

	/**
	 * Metodo getter para a variavel list.
	 * 
	 * @return the list - list.
	 */
	public List<E> getList() {
		if (list == null) {
			list = Lists.newArrayList();
		}
		return list;
	}

	/**
	 * Metodo setter para o campo list.
	 * 
	 * @param list
	 *            the list to set list.
	 */
	public void setList(List<E> list) {
		this.list = list;
	}

	/**
	 * Metodo getter para a variavel objPesquisa.
	 * 
	 * @return the objPesquisa - objPesquisa.
	 */
	public E getObjPesquisa() {
		if (objPesquisa == null) {
			try {
				objPesquisa = getEntityClass().newInstance();
			} catch (Exception e) {
				log.error("Erro ao criar classe de seleção", e);
			}
		}
		return objPesquisa;
	}

	/**
	 * Metodo setter para o campo objPesquisa.
	 * 
	 * @param objPesquisa
	 *            the objPesquisa to set objPesquisa.
	 */
	public void setObjPesquisa(E objPesquisa) {
		this.objPesquisa = objPesquisa;
	}

	/**
	 * Metodo getter para a variavel revisoes.
	 * 
	 * @return the revisoes - revisoes.
	 */
	public List<Object[]> getRevisoes() {
		try {
			if (revisoes == null || revisoes.isEmpty()) {
				revisoes = getGenericModel().getRevisions(getData().getValue());
			}
		} catch (Exception e) {
			FacesUtil.addErrorMessageHour("Erro ao obter revisoes da entidade "
					+ getEntityClass() + ExceptionUtils.getRootCauseMessage(e));
		}
		return revisoes;
	}

	/**
	 * Metodo setter para o campo revisoes.
	 * 
	 * @param revisoes
	 *            the revisoes to set revisoes.
	 */
	public void setRevisoes(List<Object[]> revisoes) {
		this.revisoes = revisoes;
	}

	public void zerarRevisoes() {
		revisoes = new ArrayList<Object[]>();
	}

	public IGenericSeamModel getGenericModel() {
		return genericModel;
	}

	public void setGenericModel(IGenericSeamModel genericModel) {
		this.genericModel = genericModel;
	}

}
