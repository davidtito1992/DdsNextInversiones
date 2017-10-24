package controller;

import java.io.UnsupportedEncodingException;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;

import spark.Request;
import spark.Response;

public class Controller {
	public static Long autenticar(Request req, Response res) {
		Long idUsuario = null;
		String token = req.cookie("authenticationToken");
		try {
			Algorithm algorithm = Algorithm.HMAC256("secret");
			JWTVerifier verifier = JWT.require(algorithm).withIssuer("auth0").build(); // Reusable verifier instance
			verifier.verify(token);
			DecodedJWT jwtDecoded = JWT.decode(token);
			Claim claim = jwtDecoded.getClaim("userId");

			idUsuario = claim.asLong();
		} catch (JWTDecodeException exception) {
			res.redirect("/");
		} catch (UnsupportedEncodingException exception) {
			res.redirect("/");
		} catch (JWTVerificationException exception) {
			res.redirect("/");
		} catch (NullPointerException exception) {
			res.redirect("/");
		}
		return idUsuario;
	}
	
	public static boolean estaLogueado(Request req){
		String token = req.cookie("authenticationToken");
		try {
			Algorithm algorithm = Algorithm.HMAC256("secret");
			JWTVerifier verifier = JWT.require(algorithm).withIssuer("auth0").build(); // Reusable verifier instance
			verifier.verify(token);
			DecodedJWT jwtDecoded = JWT.decode(token);
			jwtDecoded.getClaim("userId");
			return true;
		} catch (JWTDecodeException exception) {
			return false;
		} catch (UnsupportedEncodingException exception) {
			return false;
		} catch (JWTVerificationException exception) {
			return false;
		} catch (NullPointerException exception) {
			return false;
		}
	}
}