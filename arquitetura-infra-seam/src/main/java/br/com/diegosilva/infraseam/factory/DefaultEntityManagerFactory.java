package br.com.diegosilva.infraseam.factory;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Scope;
import org.jboss.seam.annotations.Startup;
import org.jboss.seam.annotations.intercept.BypassInterceptors;
import org.jboss.seam.persistence.EntityManagerFactory;

@Scope(ScopeType.APPLICATION)
@BypassInterceptors
@Startup
public class DefaultEntityManagerFactory extends EntityManagerFactory {

}
