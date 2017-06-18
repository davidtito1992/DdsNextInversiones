package DataManagment;

import model.Empresa;
import model.RegistroIndicador;

import com.google.gson.Gson;

public class AdapterToJson {

	private String stringJson;

	public AdapterToJson(RegistroIndicador unIndicador) {

		final Gson gson = new Gson();
		stringJson = gson.toJson(unIndicador);
	}

	public AdapterToJson(Empresa unaEmpresa) {

		final Gson gson = new Gson();
		stringJson = gson.toJson(unaEmpresa);
	}

	public String getstringJson() {
		return this.stringJson;
	}
}
