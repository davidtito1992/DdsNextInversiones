package dataManagment.dataLoader;

import java.lang.reflect.Type;
import java.time.Year;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

public class GsonFactory {

	public static Gson getGson() {

		Gson gson = new GsonBuilder().registerTypeAdapter(Year.class,
				new JsonDeserializer<Year>() {
					@Override
					public Year deserialize(
							JsonElement json,
							Type type,
							JsonDeserializationContext jsonDeserializationContext)
							throws JsonParseException {
						return Year.of(json.getAsInt());
					}
				}).create();
		return gson;
	}
}
