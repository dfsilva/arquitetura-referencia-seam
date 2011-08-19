package br.gov.anatel.provaconceitoseam.model;

import org.jboss.seam.annotations.AutoCreate;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.TransactionPropagationType;
import org.jboss.seam.annotations.Transactional;

import br.gov.anatel.provaconceitoseam.model.iface.INotaModel;

@Name("notaModel")
@AutoCreate
@Transactional(TransactionPropagationType.SUPPORTS)
public class NotaModel implements INotaModel {

}
