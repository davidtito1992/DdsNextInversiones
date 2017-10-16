package controller;

import java.io.UnsupportedEncodingException;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;

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
				try {
				    Algorithm algorithm = Algorithm.HMAC256("secret");
				    String token = JWT.create()
				        .withIssuer("auth0")
				        .withClaim("userId", usuario.getUserId())
				        .sign(algorithm);
				res.cookie("authenticationToken", token);
				} catch (UnsupportedEncodingException exception){
					res.redirect("/");
				} catch (JWTCreationException exception){
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
}