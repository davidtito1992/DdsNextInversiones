package controller;

import main.repositories.RepositorioMetodologia;
import model.Metodologia;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MetodologiaController {

	private static LoginController loginController;

	public MetodologiaController(LoginController log) {
		MetodologiaController.loginController = log;
	}

	public static ModelAndView home(Request req, Response res) {
		HashMap<String, List<Metodologia>> mapMetodologias = new HashMap<>();
		List<Metodologia> metodologiasObtenidas = getIdUsuario() != null ? RepositorioMetodologia
				.getSingletonInstance().findFromUser(getIdUsuario())
				: new ArrayList<>();
		mapMetodologias.put("metodologias", metodologiasObtenidas);
		return new ModelAndView(mapMetodologias, "homePage/metodologias.hbs");
	}

	public Void redirect(Request req, Response res) {
		res.redirect("/metodologias/" + getIdUsuario());
		return null;
	}

	public static Long getIdUsuario() {
		return loginController.getIdUsuario();
	}

}
