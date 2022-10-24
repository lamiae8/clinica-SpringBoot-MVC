/**
 * 
 */
package ar.edu.unju.fi.clinica.TestDTO;

import static org.junit.jupiter.api.Assertions.*;

import java.lang.reflect.Type;
import java.time.LocalDateTime;

import org.apache.log4j.Logger;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import ar.edu.unju.fi.clinica.GsonConv.LocalDateTimeConverter;

/**
 * @author Diaz, Sebastián Darío
 *
 */
class DeserializerTestCase {
	private static final Type LOCAL_DATE_TIME_TYPE = new TypeToken<LocalDateTime>() {}.getType();

	private static Logger log = Logger.getLogger(DeserializerTestCase.class);
	/**
	 * @throws java.lang.Exception
	 */
	@BeforeEach
	void setUp() throws Exception {

	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void testSerialisation() {
		try {
			final Gson gson = registerLocalDateTime(new GsonBuilder()).create();

			final LocalDateTime localDateTime = LocalDateTime.parse("1969-07-21T12:56:00");
			final String json = gson.toJson(localDateTime);

			assertTrue(json.equals("\"1969-07-21T12:56:00\""));
		} catch (Exception e) {
			log.debug(e.getCause());
		}
		
	}

	@Test
	void testDeserialisation() {

		final Gson gson = registerLocalDateTime(new GsonBuilder()).create();

	    final String json = "\"1969-07-21T12:56:00\"";
	    final LocalDateTime localDateTime = gson.fromJson(json, LocalDateTime.class);

	    assertTrue(localDateTime.equals(LocalDateTime.parse("1969-07-21T12:56:00")));
	}

	private static GsonBuilder registerLocalDateTime(GsonBuilder builder) {
		builder.registerTypeAdapter(LOCAL_DATE_TIME_TYPE, new LocalDateTimeConverter());

		return builder;
	}
}
