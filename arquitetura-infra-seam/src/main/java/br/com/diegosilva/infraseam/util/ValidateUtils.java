package br.com.diegosilva.infraseam.util;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.validator.ClassValidator;
import org.hibernate.validator.InvalidValue;

import br.com.diegosilva.infraseam.exception.ValidateException;

/**
 * Classe de validacao do hibernate.
 * 
 * @author diego.dba
 * 
 */
public class ValidateUtils {

	/**
	 * Metodo para validacao de campos genéricos.
	 * 
	 * @param obj
	 *            objeto a ser validado
	 * @throws SaciiValidateException
	 *             excecao de validacao
	 */
	@SuppressWarnings("unchecked")
	public static void validate(Object obj) throws ValidateException {

		ClassValidator validator = new ClassValidator(obj.getClass());

		InvalidValue[] iv = validator.getInvalidValues(obj);

		if (iv != null && iv.length > 0) {
			List<String> violacoes = new ArrayList<String>();
			// montando mensagens com os erros
			for (InvalidValue i : iv) {
				String propertyName = StringUtils.capitalizeSplitCammelCase(i
						.getPropertyName());
				String msg = "";
				if (propertyName != null) {
					propertyName = propertyName.concat(": ");
					msg = propertyName;
				}

				msg = msg.concat(i.getMessage());
				violacoes.add(msg.replace("null", ""));
			}
			throw new ValidateException(violacoes);
		}

	}

}
