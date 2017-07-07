package dataManagment;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import model.Empresa;
import model.Metodologia;
import model.RegistroIndicador;

import org.eclipse.jface.bindings.keys.ParseException;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class JsonAdapter implements DataAdapter {

	Gson gson = GsonFactory.getGson();

	public List<Empresa> adaptarEmpresas(String empresas) throws ParseException {
		List<Empresa> listaEmpresas = new ArrayList<Empresa>();
		try {
			Type listType = new TypeToken<List<Empresa>>() {
			}.getType();
			listaEmpresas = gson.fromJson(empresas, listType);

		} catch (Exception e) {
			throw new ParseException(
					"El archivo no pudo ser leido correctamente, verifique la sintaxis y vuelva a intentarlo.");
		}
		return listaEmpresas;
	}

	public List<RegistroIndicador> adaptarIndicadores(String indicadores)
			throws ParseException {
		List<RegistroIndicador> listaIndicadores = new ArrayList<RegistroIndicador>();
		try {
			Type listType = new TypeToken<List<RegistroIndicador>>() {
			}.getType();
			listaIndicadores = gson.fromJson(indicadores, listType);

		} catch (Exception e) {
			throw new ParseException(
					"El archivo no pudo ser leido correctamente, verifique la sintaxis y vuelva a intentarlo.");
		}
		return listaIndicadores;
	}
	
	public List<Metodologia> adaptarMetodologias(String metodologias)
			throws ParseException {
		List<Metodologia> listaMetodologias = new ArrayList<Metodologia>();
		try {
			Type listType = new TypeToken<List<Metodologia>>() {
			}.getType();
			listaMetodologias = gson.fromJson(metodologias, listType);

		} catch (Exception e) {
			throw new ParseException(
					"El archivo no pudo ser leido correctamente, verifique la sintaxis y vuelva a intentarlo.");
		}
		return listaMetodologias;
	}
}
