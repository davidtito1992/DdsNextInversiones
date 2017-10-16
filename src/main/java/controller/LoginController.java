package controller;

import main.repositories.RepositorioUsuario;
import model.User;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

public class LoginController {

	public static ModelAndView home(Request req, Response res) {
		return new ModelAndView(null, "login/login.hbs");
	}

	public Void login(Request req, Response res) {
		String user = req.queryParams("email");

		User usuario = RepositorioUsuario.getSingletonInstance().getUser(user);

		if (usuario != null) {

			if (usuario.getPassword().equals(req.queryParams("password"))) {
				res.redirect("/empresas/" + usuario.getUserId());
			} else {
				res.redirect("/"); // mal mail o pw
			}
		} else {
			res.redirect("/");
		}
		return null;
	}

}