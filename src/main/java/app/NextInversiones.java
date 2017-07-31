package app;

import model.Empresa;
import model.Metodologia;
import model.RegistroIndicador;

import org.uqbar.arena.Application;
import org.uqbar.arena.windows.Window;
import org.uqbar.commons.utils.ApplicationContext;

import repositories.RepositorioEmpresa;
import repositories.RepositorioIndicadores;
import repositories.RepositorioMetodologias;
import repositories.RepositorioUnicoDeEmpresas;
import repositories.RepositorioUnicoDeIndicadores;
import view.MainView;

public class NextInversiones extends Application {

	public static void main(String[] args) {
		new NextInversiones().start();
	}

	@Override
	protected Window<?> createMainWindow() {
		ApplicationContext.getInstance().configureSingleton(Empresa.class,
				new RepositorioEmpresa());

		ApplicationContext.getInstance().configureSingleton(
				RegistroIndicador.class, new RepositorioIndicadores());

		ApplicationContext.getInstance().configureSingleton(
				Metodologia.class, new RepositorioMetodologias());		
//		
//		 RepositorioUnicoDeEmpresas.getSingletonInstance();
//		 RepositorioUnicoDeIndicadores.getSingletonInstance();

		return new MainView(this);
	}
}
