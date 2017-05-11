package app;

import model.Empresa;

import org.uqbar.arena.Application;
import org.uqbar.arena.windows.Window;
import org.uqbar.commons.utils.ApplicationContext;

import repositories.RepositorioEmpresa;
import view.MainView;

public class NextInversiones extends Application {

	public static void main(String[] args) {
		new NextInversiones().start();
	}

	@Override
	protected Window<?> createMainWindow() {
		ApplicationContext.getInstance().configureSingleton(Empresa.class, new RepositorioEmpresa());

			try {
				new InicializadorDeData().cargarEmpresas();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		return new MainView(this);
	}
}
