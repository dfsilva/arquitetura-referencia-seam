package br.com.diegosilva.infraseam.util;

import java.io.IOException;
import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;

import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Classe utilitaria para o JavaServer Faces.
 */
public abstract class FacesUtil {

	/**
	 * Obt�m o <code>FacesContext</code>.
	 * 
	 * @return {@link FacesContext}
	 */
	public static FacesContext getFacesContext() {
		return FacesContext.getCurrentInstance();
	}

	/**
	 * Obt�m o <code>ExternalContext</code>.
	 * 
	 * @return {@link ExternalContext}
	 */
	public static ExternalContext getExternalContext() {
		return getFacesContext().getExternalContext();
	}

	/**
	 * Obt�m o <code>ServletContext</code>.
	 * 
	 * @return {@link ServletContext}
	 */
	public static ServletContext getServletContext() {
		return (ServletContext) getExternalContext().getContext();
	}

	/**
	 * Obt�m o <code>HttpSession</code>.
	 * 
	 * @return {@link HttpSession}
	 */
	public static HttpSession getHttpSession() {
		return (HttpSession) getExternalContext().getSession(false);
	}

	/**
	 * Obt�m o <code>HttpServletRequest</code>.
	 * 
	 * @return {@link HttpServletRequest}
	 */
	public static HttpServletRequest getHttpServletRequest() {
		return (HttpServletRequest) getExternalContext().getRequest();
	}

	/**
	 * Obt�m o <code>HttpServletResponse</code>.
	 * 
	 * @return {@link HttpServletResponse}
	 */
	public static HttpServletResponse getHttpServletResponse() {
		return (HttpServletResponse) getExternalContext().getResponse();
	}

	/**
	 * Adiciona uma mensagem de informa��o no <code>FacesContext</code>.
	 * 
	 * @param msg
	 *            A mensagem a ser adicionada.
	 */
	public static void addInfoMessage(final String msg) {
		addInfoMessage(null, msg);
	}

	/**
	 * Adiciona uma mensagem de informa��o no <code>FacesContext</code> para
	 * um cliente espec�fico.
	 * 
	 * @param clientId
	 *            O ID do cliente.
	 * @param msg
	 *            A mensagem a ser adicionada.
	 */
	public static void addInfoMessage(final String clientId, final String msg) {
		getFacesContext().addMessage(clientId,
				new FacesMessage(FacesMessage.SEVERITY_INFO, msg, msg));
	}

	/**
	 * Adiciona uma mensagem de informa��o da bundle no
	 * <code>FacesContext</code> para um cliente espec�fico.
	 * 
	 * @param clientId
	 *            O ID do cliente.
	 * @param key
	 *            A chave da mensagem na bundle.
	 * @param params
	 *            Os par�metros que a mensagem recebe.
	 */
	public static void addInfoMessageFromBundle(final String clientId,
			final String key, final String... params) {
		addInfoMessage(null, getMessage(key, params));
	}

	/**
	 * Adiciona uma mensagem de erro no <code>FacesContext</code>.
	 * 
	 * @param msg
	 *            A mensagem a ser adicionada.
	 */
	public static void addErrorMessage(final String msg) {
		addErrorMessage(null, msg);
	}

	/**
	 * Adiciona uma mensagem de erro no <code>FacesContext</code>. com a hora do
	 * erro
	 * 
	 * @param msg
	 *            A mensagem a ser adicionada.
	 */
	public static void addErrorMessageHour(final String msg) {
		String horaErro = "";
		try {
			horaErro = new SimpleDateFormat("HH:mm:ss").format(new Date());
			horaErro += horaErro.concat(" - ");
		} catch (Exception e) {
			// do nothing
		}
		addErrorMessage(null, horaErro + msg);
	}

	/**
	 * Adiciona uma mensagem de erro no <code>FacesContext</code>. com a hora do
	 * erro
	 * 
	 * @param msg
	 *            - A mensagem a ser adicionada.
	 * @param clientId
	 *            - Id do cliente.
	 * 
	 * 
	 */
	public static void addErrorMessageHour(String clientId, String msg) {
		String horaErro = "";
		try {
			horaErro = new SimpleDateFormat("HH:mm:ss").format(new Date());
			horaErro = horaErro.concat(" - ");
		} catch (Exception e) {
			horaErro = "";
		}
		addErrorMessage(clientId, horaErro + msg);
	}

	/**
	 * Adiciona uma mensagem de erro no <code>FacesContext</code> para um
	 * cliente espec�fico.
	 * 
	 * @param clientId
	 *            O ID do cliente.
	 * @param msg
	 *            A mensagem a ser adicionada.
	 */
	public static void addErrorMessage(String clientId, String msg) {
		getFacesContext().addMessage(clientId,
				new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, msg));
	}

	/**
	 * Adiciona uma mensagem de erro da bundle no <code>FacesContext</code> para
	 * um cliente espec�fico.
	 * 
	 * @param clientId
	 *            O ID do cliente.
	 * @param key
	 *            A chave da mensagem na bundle.
	 * @param params
	 *            Os par�metros que a mensagem recebe.
	 */
	public static void addErrorMessageFromBundle(String clientId, String key,
			String... params) {
		addErrorMessage(clientId, getMessage(key, params));
	}

	/**
	 * Adiciona uma mensagem de erro da bundle no <code>FacesContext</code> para
	 * um cliente espec�fico.
	 * 
	 * @param clientId
	 *            O ID do cliente.
	 * @param key
	 *            A chave da mensagem na bundle.
	 * @param params
	 *            Os par�metros que a mensagem recebe.
	 */
	public static void addErrorMessageFromBundleHour(String clientId,
			String key, String... params) {
		addErrorMessageHour(clientId, getMessage(key, params));
	}

	/**
	 * Adiciona uma mensagem de informa��o no <code>FacesContext</code>.
	 * 
	 * @param msg
	 *            A mensagem a ser adicionada.
	 */
	public static void addWarnMessage(String msg) {
		addWarnMessage(null, msg);
	}

	/**
	 * Adiciona uma mensagem de informa��o no <code>FacesContext</code> para
	 * um cliente espec�fico.
	 * 
	 * @param clientId
	 *            O ID do cliente.
	 * @param msg
	 *            A mensagem a ser adicionada.
	 */
	public static void addWarnMessage(String clientId, String msg) {
		getFacesContext().addMessage(clientId,
				new FacesMessage(FacesMessage.SEVERITY_WARN, msg, msg));
	}

	/**
	 * Adiciona uma mensagem de informa��o da bundle no
	 * <code>FacesContext</code> para um cliente espec�fico.
	 * 
	 * @param clientId
	 *            O ID do cliente.
	 * @param key
	 *            A chave da mensagem na bundle.
	 * @param params
	 *            Os par�metros que a mensagem recebe.
	 */
	public static void addWarnMessageFromBundle(String clientId, String key,
			String... params) {
		addWarnMessage(null, getMessage(key, params));
	}

	/**
	 * Obt�m o <code>ResourceBundle</code>.
	 * 
	 * @return {@link ResourceBundle}
	 */
	public static ResourceBundle getResourceBundle() {
		return ResourceBundle.getBundle(getFacesContext().getApplication()
				.getMessageBundle());
	}

	/**
	 * Recupera uma mensagem cadastrada no arquivo de mensagens
	 * (message.properties).
	 * 
	 * @param key
	 *            Identificador da mensagem.
	 * @return A mensagem correspondente a chave informada.
	 */
	public static String getMessage(String key) {
		return getResourceBundle().getString(key);
	}

	/**
	 * Recupera uma mensagem cadastrada no arquivo de mensagens
	 * (message.properties).
	 * 
	 * @param key
	 *            Identificador da mensagem.
	 * @param params
	 *            Argumentos da mensagem.
	 * @return A mensagem correspondente a chave informada.
	 */
	public static String getMessage(String key, String... params) {
		MessageFormat form = new MessageFormat(getResourceBundle().getString(
				key));
		return form.format(params);
	}

	/**
	 * Verifica se existe alguma mensagem no facesContext atual.
	 * 
	 * @return {@code boolean} - indica se existe mensagem no facesContext
	 *         atual.
	 */
	public static boolean existMessage() {
		return FacesContext.getCurrentInstance().getMessages().hasNext();
	}

	/**
	 * Redireciona a URL passada como parametro.
	 * 
	 * @param url
	 *            - URL a ser redirecionada. Deve ser informado apenas o
	 *            conteudo da URL apos o nome do servidor. Ex: "sis", que se
	 *            tornara "http://(nomeServidor)/sis".
	 * @throws IOException
	 *             - Levanta caso ocorra algum problema ao efetuar o
	 *             redirecionamento.
	 */
	public static void redirect(String url) throws IOException {
		String serverName = getHttpServletRequest().getServerName();
		getHttpServletResponse().sendRedirect(
				"http://" + serverName + "/" + url);
	}

	/**
	 * Retorna uma instancia do bean passado como nome.
	 * 
	 * @param name
	 *            nome do bean.
	 * @return Objeto do bean.
	 */
	public static Object getBean(String name) {
		return getFacesContext().getApplication().getELResolver()
				.getValue(getFacesContext().getELContext(), null, name);
	}

}