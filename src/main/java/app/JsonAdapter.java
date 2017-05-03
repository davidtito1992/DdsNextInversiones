package app;

import java.lang.reflect.Type;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import model.Empresa;

public class JsonAdapter {

	public List<Empresa> adaptarEmpresas(String empresas) {
		Type listType = new TypeToken<List<Empresa>>() {}.getType();
		List<Empresa> lista = new Gson().fromJson(empresas, listType);
		return lista;
	}
}
