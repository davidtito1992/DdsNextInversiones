package main.server;

import spark.Spark;
import spark.debug.DebugScreen;

public class Server {

	public static void main(String[] args) {

		System.out.println("Iniciando servidor...");

		Spark.port(8088);
		DebugScreen.enableDebugScreen();
		Router.configure();

	}
}