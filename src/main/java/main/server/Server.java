package main.server;

import main.app.AppData;
import spark.Spark;
import spark.debug.DebugScreen;

import java.io.IOException;

public class Server {
    static AppData appData = new AppData();

    public static void main() throws IOException {
        Spark.port(8080);

        try {
            appData.cargarUsuarios();
            appData.cargarEmpresas();
            appData.cargarIndicadores();
            appData.cargarMetodologias();
            DebugScreen.enableDebugScreen();
            Router.configure();
        } catch (Exception e) {
            System.out.println("ERROR");
            e.printStackTrace();
        }
    }
}