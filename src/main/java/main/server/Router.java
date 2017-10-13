package main.server;

import controller.EmpresaController;
import main.handlebars.HandlebarsTemplateEngineBuilder;
import controller.LoginController;
import spark.Spark;
import spark.template.handlebars.HandlebarsTemplateEngine;
import static spark.Spark.*;

public class Router {
	public static void configure() {
		
		HandlebarsTemplateEngine engine = HandlebarsTemplateEngineBuilder.create().withDefaultHelpers().build();

		staticFileLocation("/public");

		LoginController loginController = new LoginController();

		// Login
		// Email - Password
		Spark.get("/", LoginController::home, engine);
		Spark.post("/login", loginController::login);

		//OJO aca iria en la url el id del usuario
		Spark.get("/empresas", EmpresaController::home,engine);

	}
}