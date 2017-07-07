package dataManagment;

import model.Empresa;
import model.Metodologia;
import model.RegistroIndicador;

import com.google.gson.Gson;

public class StringToJson {

	public String getStringRegistroIndicador(RegistroIndicador unIndicador) {

		final Gson gson = new Gson();
		return gson.toJson(unIndicador);
	}

	public String getStringEmpresa(Empresa unaEmpresa) {

		final Gson gson = new Gson();
		return gson.toJson(unaEmpresa);
	}

	public String getStringMetodologia(Metodologia unaMetodologia) {

		final Gson gson = new Gson();
		return gson.toJson(unaMetodologia);
	}

}
