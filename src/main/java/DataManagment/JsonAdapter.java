package DataManagment;

import java.lang.reflect.Type;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.Year;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

import model.Empresa;
import model.Indicador;

import org.eclipse.jface.bindings.keys.ParseException;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.reflect.TypeToken;

public class JsonAdapter implements DataAdapter {

	private static final Gson gson = new GsonBuilder().registerTypeAdapter(Year.class,
			new JsonDeserializer<Year>() {
				@Override
				public Year deserialize(JsonElement json, Type type,
						JsonDeserializationContext jsonDeserializationContext)
						throws JsonParseException {
					return Year.of(json.getAsInt());
				}
			}).create();

	public List<Empresa> adaptarEmpresas(String empresas) throws Exception {
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

	public List<Indicador> adaptarIndicadores(String indicadores)
			throws Exception {
		List<Indicador> listaIndicadores = new ArrayList<Indicador>();
		try {
			Type listType = new TypeToken<List<Indicador>>() {
			}.getType();
			listaIndicadores = gson.fromJson(indicadores, listType);

		} catch (Exception e) {
			throw new ParseException(
					"El archivo no pudo ser leido correctamente, verifique la sintaxis y vuelva a intentarlo.");
		}
		return listaIndicadores;
	}
}
