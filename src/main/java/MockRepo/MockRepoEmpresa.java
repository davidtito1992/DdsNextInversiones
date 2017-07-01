package MockRepo;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import repositories.RepositorioEmpresa;

public class MockRepoEmpresa {
	
	RepositorioEmpresa mockRepo = mock(RepositorioEmpresa.class);

	public void esCuenta(){
		when(mockRepo.esCuenta("cuenta1")).thenReturn(false);
	}

}
