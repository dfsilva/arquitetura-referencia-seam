package br.com.diegosilva.jsfcomponents.servlet;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Resouce servlet responsável por encontrar imagens utilizadas no componente
 * 
 * @author diego.dba
 * 
 *         Exemplo de Utilizacao:
 * 
 *         <servlet>
 *         		<servlet-name>Facelets-Components-Resource-Servlet</servlet-name>
	 *         <servlet-class>
	 *         		br.gov.anatel.faceletscomponents.servlet.ResourceServlet
	 *         </servlet-class> 
 *         </servlet> 
 *         <servlet-mapping>
 *         		<servlet-name>Facelets-Components-Resource-Servlet</servlet-name>
 *         		<url-pattern>/faceletscomponentes/resources/*</url-pattern>
 *         </servlet-mapping>
 * 
 */
public class ResourceServlet extends HttpServlet {

	/**
	 * Variável serialVersionUID do tipo long.
	 */
	private static final long serialVersionUID = 1L;

	private org.apache.log4j.Logger log = org.apache.log4j.Logger
			.getLogger(ResourceServlet.class);

	/** default constructor */
	public ResourceServlet() {
	}

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String path = request.getPathInfo();
		if (path == null) {
			path = request.getRequestURI().substring(
					request.getContextPath().length());
		}

		log.debug("Buscando por " + path + " no classpath.");
		URL resource = Thread.currentThread().getContextClassLoader()
				.getResource(path.substring(1));
		if (resource == null) {
			resource = this.getClass().getClassLoader()
					.getResource(path.substring(1));
		}
		if (resource == null) {
			resource = this.getClass().getResource(path.substring(1));
		}
		if (resource == null) {
			response.sendError(404, path + " nao encontrado no classpath");
		} else {
			URLConnection connection = resource.openConnection();
			long lastModified = connection.getLastModified();
			long ifModifiedSince = request.getDateHeader("If-Modified-Since");
			if (ifModifiedSince != -1 && lastModified <= ifModifiedSince) {
				response.setStatus(HttpServletResponse.SC_NOT_MODIFIED);
				return;
			}

			response.setContentType(getServletContext().getMimeType(path));
			OutputStream out = new BufferedOutputStream(
					response.getOutputStream(), 512);
			InputStream in = new BufferedInputStream(resource.openStream(), 512);
			try {
				int len;
				byte[] data = new byte[512];
				while ((len = in.read(data)) != -1) {
					out.write(data, 0, len);
				}
			} finally {
				out.close();
				in.close();
				if (connection.getInputStream() != null) {
					connection.getInputStream().close();
				}
			}
		}
	}
}