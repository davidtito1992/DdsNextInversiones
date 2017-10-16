package main.app;

import java.io.IOException;
import main.server.Server;

//import org.uqbar.arena.Application;
//import org.uqbar.arena.windows.Window;

public class NextInversiones {

	public static void main(String[] args) throws IOException {
		AplicacionContexto.getInstance();
		AplicacionContexto.getInstance().getInstanceRepoEmpresas();
		AplicacionContexto.getInstance().getInstanceRepoIndicadores();
		AplicacionContexto.getInstance().getInstanceRepoMetodologias();
		AplicacionContexto.getInstance().getInstanceRepoUsuarios();
		
		new Server();
		Server.main();
		// new NextInversiones().start();

	}

	// @Override
	// protected Window<?> createMainWindow() {
	// AplicacionContexto.getInstance().getInstanceRepoEmpresas();
	// AplicacionContexto.getInstance().getInstanceRepoIndicadores();
	// AplicacionContexto.getInstance().getInstanceRepoMetodologias();
	//
	// //return new MainView(this);
	// }
}
