package domaintest;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import model.Empresa;

import org.junit.Before;
import org.junit.Test;
import org.uqbar.commons.utils.ApplicationContext;

import repositories.RepositorioEmpresa;

public class SemanticaVariableTest {
	
	RepositorioEmpresa mockRepo;
	
	public void init(){
		mockRepo = mock(RepositorioEmpresa.class);
	    ApplicationContext.getInstance().configureSingleton(Empresa.class, mockRepo);
		
	}

}
