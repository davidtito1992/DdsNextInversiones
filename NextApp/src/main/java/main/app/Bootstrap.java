package main.app;

/*** Ejecutar antes de levantar el servidor por primera vez ****/

public class Bootstrap {

	public static void main(String[] args) throws Exception {
		new Bootstrap().run();
	}

	public void run() throws Exception {
		AppData appData = new AppData();

		appData.cargarUsuarios();
		appData.cargarEmpresas();
		appData.cargarIndicadores();
		appData.cargarMetodologias();
		appData.cargarPrecalculosIndicadores();

		System.out.println("TERMINE!!!!");
	}

}
