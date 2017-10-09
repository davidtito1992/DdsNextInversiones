package main.server;

import java.io.IOException;

import spark.Spark;
import spark.debug.DebugScreen;

public class Server {
	public static void main() throws IOException {
		Spark.port(8088);

		try {
			// persisto todo?
			DebugScreen.enableDebugScreen();
			Router.configure();
		} catch (Exception e) {
			System.out.println("Error\n");
		}
	}
}