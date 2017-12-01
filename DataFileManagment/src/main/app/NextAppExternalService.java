package main.app;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

public class NextAppExternalService {

	private final static String url = "http://localhost:8080/indicadores/nuevosPrecalculos";
	private RestTemplate restTemplate = new RestTemplate();

	public void enviarUsuariosARecalcular(String idsUsuarios) {
		// header
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);

		// httpEnitity
		HttpEntity<Object> requestEntity = new HttpEntity<Object>(idsUsuarios,
				headers);

		restTemplate.postForEntity(url, requestEntity, String.class);
	}

}
