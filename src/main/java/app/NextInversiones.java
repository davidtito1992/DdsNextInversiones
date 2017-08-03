package app;

import org.uqbar.arena.Application;
import org.uqbar.arena.windows.Window;

import view.MainView;

public class NextInversiones extends Application {

	public static void main(String[] args) {
		AplicacionContexto.getInstance();
		new NextInversiones().start();

	}

	@Override
	protected Window<?> createMainWindow() {
//		ApplicationContext.getInstance().configureSingleton(Empresa.class,
//				new RepositorioEmpresa());
//
//		ApplicationContext.getInstance().configureSingleton(
//				RegistroIndicador.class, new RepositorioIndicadores());
		AplicacionContexto.getInstance().getInstanceRepoEmpresas();
		AplicacionContexto.getInstance().getInstanceRepoIndicadores();
		AplicacionContexto.getInstance().getInstanceRepoMetodologias();
		

		ApplicationContext.getInstance().configureSingleton(Metodologia.class,
				new RepositorioMetodologias());
		//
		// RepositorioUnicoDeEmpresas.getSingletonInstance();
		// RepositorioUnicoDeIndicadores.getSingletonInstance();

		return new MainView(this);
	}
}
