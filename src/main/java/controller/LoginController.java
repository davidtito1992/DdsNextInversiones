package controller;

import main.repositories.RepositorioUsuario;
import model.User;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

public class LoginController {

	private User usuario  ;
	
	public static ModelAndView home(Request req, Response res) {
		return new ModelAndView(null, "login/login.hbs");
	}
	
	public Void login(Request req, Response res) {
		String user = req.queryParams("email");
		usuario = RepositorioUsuario.getSingletonInstance().getUser(user);
		if (usuario != null) {
			if (usuario.getPassword().equals(req.queryParams("password"))) {
				setUsuario(usuario);
				res.redirect("/empresas/" + usuario.getUserId());
			} else {
				res.redirect("/"); // mal mail o pw
			}
		} else {
			res.redirect("/");
		}
		return null;
	}


	private void setUsuario(User usuario2) {
		usuario = usuario2;
	}

	public Long getIdUsuario() {
		return usuario != null ? usuario.getUserId() : null;
	}

}