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
		AplicacionContexto.getInstance().getInstanceRepoEmpresas();
		AplicacionContexto.getInstance().getInstanceRepoIndicadores();
		AplicacionContexto.getInstance().getInstanceRepoMetodologias();
		return new MainView(this);
	}
}
