package domaintest;

import static org.mockito.Mockito.mock;
import model.Empresa;

import org.uqbar.commons.utils.ApplicationContext;

import repositories.RepositorioEmpresa;

public class SemanticaVariableTest {

	RepositorioEmpresa mockRepo;

	public void init() {
		mockRepo = mock(RepositorioEmpresa.class);
		ApplicationContext.getInstance().configureSingleton(Empresa.class,
				mockRepo);

	}

}
