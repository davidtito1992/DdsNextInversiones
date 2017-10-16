package controller;

import main.repositories.RepositorioIndicador;
import model.RegistroIndicador;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class IndicadorController {

	private static LoginController loginController;

	public IndicadorController(LoginController log) {
		IndicadorController.loginController = log;
	}

	public static ModelAndView home(Request req, Response res) {
		HashMap<String, List<RegistroIndicador>> mapIndicadores = new HashMap<>();
		List<RegistroIndicador> indicadoresObtenidas = getIdUsuario() != null ? RepositorioIndicador
				.getSingletonInstance().findFromUser(getIdUsuario())
				: new ArrayList<>();
		mapIndicadores.put("indicadores", indicadoresObtenidas);
		return new ModelAndView(mapIndicadores, "homePage/indicadores.hbs");
	}

	public Void redirect(Request req, Response res) {
		res.redirect("/indicadores/" + getIdUsuario());
		return null;
	}

	public static Long getIdUsuario() {
		return loginController.getIdUsuario();
	}

}
