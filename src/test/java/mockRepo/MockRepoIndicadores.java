package mockRepo;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import repositories.RepositorioIndicadores;

public class MockRepoIndicadores {
	
	RepositorioIndicadores mockRepoIndicadores = mock(RepositorioIndicadores.class);

	public void esCuenta(){
		when(mockRepoIndicadores.esIndicador("I1")).thenReturn(false);
	}

}
