package indicadoresCondicionados;
import repositories.RepositorioUnicoDeEmpresas;
import repositories.RepositorioUnicoDeIndicadores;
import repositories.RepositorioUnicoDeMetodologias;
import app.AplicacionContexto;

public class ControladorDeMetodologia {


//	public RepositorioMetodologias getRepoMetodologias() {
//		return ApplicationContext.getInstance().getSingleton(Metodologia.class);
//	}
//
//	public RepositorioEmpresa getRepoEmpresas() {
//		return ApplicationContext.getInstance().getSingleton(Empresa.class);
//	}
//
//	public RepositorioIndicadores getRepoIndicadores() {
//		return ApplicationContext.getInstance().getSingleton(RegistroIndicador.class);
//	}
	
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