/**
 * 
 */
package ar.edu.unju.fi.clinica.GsonConv;

import java.time.OffsetDateTime;

import com.fatboyindustrial.gsonjavatime.Converters;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * @author Diaz, Sebastián Darío
 *
 */
public class GsonConverter {

	final Gson gson = Converters.registerOffsetDateTime(new GsonBuilder()).create();
	final OffsetDateTime original = OffsetDateTime.now();

	final String json = gson.toJson(original);
	final OffsetDateTime reconstituted = gson.fromJson(json, OffsetDateTime.class);
}
