package br.com.diegosilva.infraseam.util;

/**
 * @author diego.dba
 * 
 */
public class StringUtils {

	/**
	 * Separa uma String em espa�os em branco de acordo com os caracters
	 * uppercase.
	 * 
	 * Ex.: DiegoFerreiraDaSilva = Diego Ferreira da Silva MeuEndereco = Meu
	 * Endereco
	 * 
	 * @param str
	 *            String a ser separada
	 * @return String com espacos em branco
	 */
	public static String splitCammelCase(String str) {
		String[] strings = org.apache.commons.lang.StringUtils
				.splitByCharacterTypeCamelCase(str);
		StringBuilder retorno = new StringBuilder();
		for (int i = 0; i < strings.length; i++) {
			retorno.append(strings[i]);
			if (i < strings.length - 1) {
				retorno.append(" ");
			}
		}
		return retorno.toString();
	}

	/**
	 * Separa uma String em espa�os em branco de acordo com os caracters
	 * uppercase.
	 * 
	 * Ex.: DiegoFerreiraDaSilva = Diego Ferreira da Silva MeuEndereco = Meu
	 * Endereco
	 * 
	 * @param str
	 *            String a ser separada
	 * @return String com espacos em branco
	 */
	public static String capitalizeSplitCammelCase(String str) {

		if (str != null) {

			str = org.apache.commons.lang.StringUtils.capitalize(str);

			String[] strings = org.apache.commons.lang.StringUtils
					.splitByCharacterTypeCamelCase(str);
			StringBuilder retorno = new StringBuilder();
			for (int i = 0; i < strings.length; i++) {
				retorno.append(strings[i]);
				if (i < strings.length - 1) {
					retorno.append(" ");
				}
			}
			return retorno.toString();
		}
		return str;
	}
}
