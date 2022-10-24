/**
 * 
 */
package ar.edu.unju.fi.clinica.GsonConv;

import java.io.IOException;
import java.time.LocalDateTime;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

/**
 * @author Diaz, Sebastián Darío
 *
 */
public class LocalDateTimeDesearilizer extends JsonDeserializer<LocalDateTime> {

	@Override
	public LocalDateTime deserialize(JsonParser arg0, DeserializationContext arg1) throws IOException {
        return LocalDateTime.parse(arg0.getText());
    }

}
