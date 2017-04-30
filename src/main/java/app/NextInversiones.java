package app;

import model.Empresa;
import model.RepositorioMaestro;

import org.uqbar.arena.Application;
import org.uqbar.arena.windows.Window;
import org.uqbar.commons.utils.ApplicationContext;

import view.MainView;

public class NextInversiones extends Application {

	public static void main(String[] args) {
		new NextInversiones().start();
	}

	@Override
	protected Window<?> createMainWindow() {
		ApplicationContext.getInstance().configureSingleton(Empresa.class, new RepositorioMaestro());
		new Mixin().cargarEmpresas();
		return new MainView(this);
	}
}
