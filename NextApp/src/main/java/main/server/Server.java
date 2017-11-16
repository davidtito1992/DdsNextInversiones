package main.server;

import spark.Spark;
import spark.debug.DebugScreen;
import main.server.Router;

public class Server {

	public static void main(String[] args) {

		System.out.println("Iniciando servidor...");

		Spark.port(8080);
		DebugScreen.enableDebugScreen();
		Router.configure();

	}
}