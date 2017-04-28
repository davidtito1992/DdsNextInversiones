package app;

import org.json.simple.JSONObject;

import com.google.gson.Gson;

import model.Empresa;

public class JsonAdapter {

	public static Empresa adaptarEmpresa(JSONObject jsonObject) {
		Gson gson = new Gson();
		return gson.fromJson(jsonObject.toJSONString(), Empresa.class);
	}
}
