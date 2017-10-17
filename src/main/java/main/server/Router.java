package main.server;

import controller.EmpresaController;
import controller.IndicadorController;
import controller.LoginController;
import controller.MetodologiaController;
import main.handlebars.HandlebarsTemplateEngineBuilder;
import spark.Spark;
import spark.template.handlebars.HandlebarsTemplateEngine;

import static spark.Spark.staticFileLocation;

public class Router {
	public static void configure() {

		HandlebarsTemplateEngine engine = HandlebarsTemplateEngineBuilder
				.create().withDefaultHelpers().build();

		staticFileLocation("/public");

		LoginController loginController = new LoginController();
		//EmpresaController empresaController = new EmpresaController();
		IndicadorController indicadorController = new IndicadorController();
		MetodologiaController metodologiaController = new MetodologiaController();

		// Login
		// Email - Password
		Spark.get("/", LoginController::home, engine);
		Spark.post("/login", loginController::login);

		// Empresas
		Spark.get("/empresas", EmpresaController::home, engine);

		// Indicadores
		Spark.get("/indicadores", IndicadorController::home, engine);
		Spark.get("/indicadores/borrar/:indicadorId", indicadorController::delete);
		Spark.get("/indicadores/agregar", IndicadorController::agregarView, engine);
		Spark.post("/indicadores/agregar", indicadorController::agregar);
		

		// Metodologias
		Spark.get("/metodologias", MetodologiaController::home, engine);
		Spark.get("/metodologias/borrar/:metodologiaId", metodologiaController::delete);
		Spark.get("/metodologias/consultar", MetodologiaController::consultarView, engine);
		//Spark.post("/metodologias/consultar", metodologiaController::consultar);

	}
}