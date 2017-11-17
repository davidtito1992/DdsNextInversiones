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

		// Login
		// Email - Password
		Spark.get("/", LoginController::home, engine);
		Spark.post("/login", LoginController::login);

		// Logout
		Spark.get("/cerrarSesion", LoginController::logout, engine);

		// Empresas
		Spark.get("/empresas", EmpresaController::home, engine);

		// Indicadores
		Spark.get("/indicadores", IndicadorController::home, engine);
		Spark.post("/indicadores/nuevoIndicador", IndicadorController::agregar);
		Spark.post("/indicadores/baja/:indicadorId", IndicadorController::delete);
		Spark.get("/indicadores/nuevoIndicador", IndicadorController::agregarView, engine);
		Spark.get("/indicadores/consultas/:indicadorId", IndicadorController::consultarView, engine);
		Spark.post("/indicadores/nuevosPrecalculos", IndicadorController::actualizarPrecalculos);


		// Metodologias
		Spark.get("/metodologias", MetodologiaController::home, engine);
		Spark.post("/metodologias/baja/:metodologiaId", MetodologiaController::delete);
		Spark.get("/metodologias/consultas/:metodologiaId", MetodologiaController::consultarView, engine);
		Spark.post("/metodologias/nuevaMetodologia", MetodologiaController::agregarNombre);
		Spark.get("/metodologias/nuevaCondicion", MetodologiaController::agregarCondicionesView, engine);
		Spark.post("/metodologias/nuevaCondicion/:nombreMetodologia", MetodologiaController::agregarMetodologia);
		Spark.post("/metodologias/condicionesReset/:nombreMetodologia", MetodologiaController::reiniciar);

	}
}