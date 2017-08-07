package indicadoresCondicionados;
import repositories.RepositorioUnicoDeEmpresas;
import repositories.RepositorioUnicoDeIndicadores;
import repositories.RepositorioUnicoDeMetodologias;
import app.AplicacionContexto;

public class ControladorDeMetodologia {
	
	public RepositorioUnicoDeMetodologias getRepoMetodologias(){
		return AplicacionContexto.getInstance().getInstanceRepoMetodologias();
	}
	
	public RepositorioUnicoDeIndicadores getRepoIndicadores(){
		return AplicacionContexto.getInstance().getInstanceRepoIndicadores();
	}
	
	public RepositorioUnicoDeEmpresas getRepoEmpresas(){
		return AplicacionContexto.getInstance().getInstanceRepoEmpresas();
	}
}