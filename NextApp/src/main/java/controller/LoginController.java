package controller;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import main.app.Token;
import main.converter.SnapshotIndicadorConverter;
import main.dataManagment.dataUploader.AdapterToJson;
import main.repositories.RepositorioIndicador;
import main.repositories.RepositorioUsuario;
import model.RegistroIndicador;
import model.SnapshotIndicador;
import model.User;
import redis.clients.jedis.Jedis;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import com.auth0.jwt.exceptions.JWTCreationException;

public class LoginController {

	private static Jedis jedisCache = new Jedis();

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
					res.cookie("authenticationToken", Token.getToken(usuario.getUserId()));
					precalculoIndicadores(usuario.getUserId());
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
		jedisCache.flushAll();
		res.removeCookie("authenticationToken");
		return new ModelAndView(null, "login/login.hbs");
	}

	public static void precalculoIndicadores(Long usuarioId) {
		SnapshotIndicadorConverter snapshotIndicadorConverter = new SnapshotIndicadorConverter();
		AdapterToJson adapter = new AdapterToJson();

		List<RegistroIndicador> indicadoresObtenidas = usuarioId != null
				? RepositorioIndicador.getSingletonInstance().allInstancesUser(usuarioId)
				: new ArrayList<>();

		indicadoresObtenidas.forEach(indicador -> {
			List<SnapshotIndicador> snapshots = snapshotIndicadorConverter.snapshotsOf(usuarioId, indicador);
			String snapshotsJson = adapter.getStringListRegistroIndicador(snapshots);
			jedisCache.set(indicador.getNombre(), snapshotsJson);
		});
	
	}

}