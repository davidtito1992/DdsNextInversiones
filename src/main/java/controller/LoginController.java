package controller;

import java.io.UnsupportedEncodingException;

import main.app.TokenUtils;
import main.repositories.RepositorioUsuario;
import model.User;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import com.auth0.jwt.exceptions.JWTCreationException;

public class LoginController {

	public static ModelAndView home(Request req, Response res) {
		if (!TokenUtils.estaLogueado(req)) {
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

					res.cookie("authenticationToken", TokenUtils.getToken(usuario.getUserId()));
				} catch (UnsupportedEncodingException | JWTCreationException exception) {
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
		return TokenUtils.logout(req, res);
	}
}