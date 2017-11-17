package main.app;

import java.util.List;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

import org.springframework.web.client.RestTemplate;

public class NextAppExternalService {

	private final static String url = "https://localhost:8080/indicadores/nuevosPrecalculos";
	private RestTemplate restTemplate = new RestTemplate();

	public void enviarEmpresasActualizadas(List<Long> idsEmpresas) {
		// header
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);

		// httpEnitity
		HttpEntity<Object> requestEntity = new HttpEntity<Object>(idsEmpresas, headers);

		restTemplate.postForEntity(url, requestEntity, String.class);
	}

}
