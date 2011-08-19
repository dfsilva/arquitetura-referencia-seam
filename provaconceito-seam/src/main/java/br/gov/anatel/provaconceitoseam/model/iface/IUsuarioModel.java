package br.gov.anatel.provaconceitoseam.model.iface;

import br.gov.anatel.provaconceitoseam.dto.UsuarioDTO;

public interface IUsuarioModel {

	void saveWithRoolBack(UsuarioDTO dto);

}
