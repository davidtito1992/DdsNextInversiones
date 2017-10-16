package main.server;

import controller.EmpresaController;
import controller.IndicadorController;
import controller.LoginController;
import controller.MetodologiaController;
import main.handlebars.HandlebarsTemplateEngineBuilder;
import model.Metodologia;
import spark.Spark;
import spark.template.handlebars.HandlebarsTemplateEngine;

import static spark.Spark.staticFileLocation;

public class Router {
    public static void configure() {

        HandlebarsTemplateEngine engine = HandlebarsTemplateEngineBuilder.create().withDefaultHelpers().build();

        staticFileLocation("/public");

        LoginController loginController = new LoginController();

        // Login
        // Email - Password
        Spark.get("/", LoginController::home, engine);
        Spark.post("/login", loginController::login);

        // Empresas
        Spark.get("/empresas/:userId", EmpresaController::home, engine);

        // Indicadores
        Spark.get("/indicadores/:userId", IndicadorController::home, engine);

        // Metodologias
        Spark.get("/metodologias/:userId", MetodologiaController::home, engine);

    }
}