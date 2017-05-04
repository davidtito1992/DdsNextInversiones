package app;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import model.Empresa;

public class JsonAdapter {

	public List<Empresa> adaptarEmpresas(String empresas) throws Exception {
		List<Empresa> lista = new ArrayList<Empresa>();
		try {
			Type listType = new TypeToken<List<Empresa>>() {}.getType();
			lista = new Gson().fromJson(empresas, listType);

		} catch (Exception e) {
			throw new Exception("El archivo no pudo ser leido correctamente, verifique la sintaxis y vuelva a intentarlo.");
		}
		return lista;
	}
}
