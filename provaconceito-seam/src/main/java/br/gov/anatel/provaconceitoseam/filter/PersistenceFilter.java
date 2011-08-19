package br.gov.anatel.provaconceitoseam.filter;

import java.io.IOException;
import java.util.logging.Logger;

import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * Filtro de Persistencia para conectar no banco de dados como e feito no
 * ambiente da anatel.
 * 
 * @author diego
 * 
 */
public class PersistenceFilter implements javax.servlet.Filter {

	private static final Logger LOGGER = Logger
			.getLogger(PersistenceFilter.class.getName());

	/**
	 * @see javax.servlet.Filter#doFilter(javax.servlet.ServletRequest,
	 *      javax.servlet.ServletResponse, javax.servlet.FilterChain)
	 */
	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		LOGGER.info("TID[" + Thread.currentThread().getId()
				+ "] Inicio Filtro Request. Acesso");
		chain.doFilter(request, response);

	}

	/**
	 * @see javax.servlet.Filter#destroy() Destroy.
	 */
	public void destroy() {
	}

	/**
	 * @see javax.servlet.Filter#init(javax.servlet.FilterConfig) init.
	 * @param filterConfig
	 *            - FilterConfig.
	 * @throws ServletException
	 *             - ServletException.
	 */
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
	}

}