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

		HandlebarsTemplateEngine engine = HandlebarsTemplateEngineBuilder.create().withDefaultHelpers().build();

		staticFileLocation("/public");

		LoginController loginController = new LoginController();
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
		Spark.post("/indicadores/nuevoIndicador", indicadorController::agregar);
		Spark.post("/indicadores/baja/:indicadorId", indicadorController::delete);
		Spark.get("/indicadores/nuevoIndicador", IndicadorController::agregarView, engine);



		// Metodologias
		Spark.get("/metodologias", MetodologiaController::home, engine);
		Spark.post("/metodologias/baja/:metodologiaId", metodologiaController::delete);
		Spark.get("/metodologias/consultas/:metodologiaId", MetodologiaController::consultarView, engine);
		Spark.get("/metodologias/nuevaMetodologia", MetodologiaController::agregarNombreView, engine);
		Spark.post("/metodologias/nuevaMetodologia", metodologiaController::agregarNombre);
		Spark.get("/metodologias/nuevaCondicion", MetodologiaController::agregarCondicionesView, engine);
		Spark.post("/metodologias/nuevaCondicion", metodologiaController::agregarMetodologia);
		Spark.post("/metodologias/condicionesReset", metodologiaController::reiniciar);

	}
}