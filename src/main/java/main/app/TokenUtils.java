package main.app;

import java.io.UnsupportedEncodingException;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;

import spark.ModelAndView;
import spark.Request;
import spark.Response;

public class TokenUtils {
	public static Long autenticar(Request req, Response res) {
		Long idUsuario = null;
		String token = req.cookie("authenticationToken");
		try {
			Algorithm algorithm = Algorithm.HMAC256("secret");
			JWTVerifier verifier = JWT.require(algorithm).withIssuer("auth0")
					.build(); // Reusable verifier instance
			verifier.verify(token);
			DecodedJWT jwtDecoded = JWT.decode(token);
			Claim claim = jwtDecoded.getClaim("userId");

			idUsuario = claim.asLong();
		} catch (UnsupportedEncodingException | JWTVerificationException | NullPointerException exception) {
			res.redirect("/");
		}
		
		return idUsuario;
	}

	public static boolean estaLogueado(Request req) {
		String token = req.cookie("authenticationToken");
		try {
			Algorithm algorithm = Algorithm.HMAC256("secret");
			JWTVerifier verifier = JWT.require(algorithm).withIssuer("auth0")
					.build(); // Reusable verifier instance
			verifier.verify(token);
			DecodedJWT jwtDecoded = JWT.decode(token);
			jwtDecoded.getClaim("userId");
			return true;
		} catch (UnsupportedEncodingException | JWTVerificationException | NullPointerException exception) {
			return false;
		}
	}

	public static String getToken(Long userId) throws IllegalArgumentException,
			UnsupportedEncodingException {

		Algorithm algorithm = Algorithm.HMAC256("secret");
		return JWT.create().withIssuer("auth0").withClaim("userId", userId)
				.sign(algorithm);
	}
	
	public static ModelAndView logout(Request req,Response res){
		res.removeCookie("authenticationToken");
		return new ModelAndView(null, "login/login.hbs");
	}

}