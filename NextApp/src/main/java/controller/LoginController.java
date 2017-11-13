package controller;

import java.io.UnsupportedEncodingException;

import main.app.Token;
import main.repositories.RepositorioUsuario;
import model.User;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import com.auth0.jwt.exceptions.JWTCreationException;

public class LoginController {

	public static ModelAndView home(Request req, Response res) {
		if (!Token.estaLogueado(req)) {
			return new ModelAndView(null, "login/login.hbs");
		} else {
			res.redirect("/empresas");
			return new ModelAndView(null, "login/login.hbs");
		}
	}

	public static Void login(Request req, Response res) {
		String user = req.queryParams("email");
		User usuario = RepositorioUsuario.getSingletonInstance().getUser(user);
		if (usuario != null) {
			if (usuario.getPassword().equals(req.queryParams("password"))) {
				try {

					res.cookie("authenticationToken",
							Token.getToken(usuario.getUserId()));
				} catch (UnsupportedEncodingException exception) {
					res.redirect("/");
				} catch (JWTCreationException exception) {
					res.redirect("/");
				}
				res.redirect("/empresas");
			} else {
				res.redirect("/"); // mal mail o pw
			}
		} else {
			res.redirect("/");
		}
		return null;
	}

	public static ModelAndView logout(Request req, Response res) {
		res.removeCookie("authenticationToken");
		return new ModelAndView(null, "login/login.hbs");
	}
}