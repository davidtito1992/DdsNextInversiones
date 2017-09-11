package domaintest;

import static org.mockito.Mockito.mock;
import model.Empresa;
import repositories.RepositorioEmpresa;

import org.uqbar.commons.utils.ApplicationContext;

public class SemanticaVariableTest {

	RepositorioEmpresa mockRepo;

	public void init() {
		mockRepo = mock(RepositorioEmpresa.class);
		ApplicationContext.getInstance().configureSingleton(Empresa.class,
				mockRepo);

	}

}
