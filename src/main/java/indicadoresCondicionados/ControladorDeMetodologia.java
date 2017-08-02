package indicadoresCondicionados;
import model.Empresa;
import model.Metodologia;
import model.RegistroIndicador;
import org.uqbar.commons.utils.ApplicationContext;
import repositories.RepositorioEmpresa;
import repositories.RepositorioIndicadores;
import repositories.RepositorioMetodologias;

public class ControladorDeMetodologia {


	public RepositorioMetodologias getRepoMetodologias() {
		return ApplicationContext.getInstance().getSingleton(Metodologia.class);
	}

	public RepositorioEmpresa getRepoEmpresas() {
		return ApplicationContext.getInstance().getSingleton(Empresa.class);
	}

	public RepositorioIndicadores getRepoIndicadores() {
		return ApplicationContext.getInstance().getSingleton(
				RegistroIndicador.class);
	}
}