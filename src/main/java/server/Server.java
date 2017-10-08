package server;

import java.io.IOException;

import spark.Spark;
import spark.debug.DebugScreen;

public class Server {
	public static void main(String[] args) throws IOException {
		Spark.port(8080);

		try {
			// persisto todo?
			DebugScreen.enableDebugScreen();
			Router.configure();
		} catch (Exception e) {
			System.out.println("Error\n");
		}
	}
}