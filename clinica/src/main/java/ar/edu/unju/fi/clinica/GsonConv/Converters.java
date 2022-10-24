/**
 * 
 */
package ar.edu.unju.fi.clinica.GsonConv;

import java.lang.reflect.Type;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.OffsetDateTime;
import java.time.OffsetTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

import com.fatboyindustrial.gsonjavatime.InstantConverter;
import com.fatboyindustrial.gsonjavatime.LocalDateConverter;
import com.fatboyindustrial.gsonjavatime.LocalTimeConverter;
import com.fatboyindustrial.gsonjavatime.OffsetDateTimeConverter;
import com.fatboyindustrial.gsonjavatime.OffsetTimeConverter;
import com.fatboyindustrial.gsonjavatime.ZoneIdConverter;
import com.fatboyindustrial.gsonjavatime.ZonedDateTimeConverter;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

/**
 * @author Diaz, Sebastián Darío
 *
 */
public class Converters {

	public static final Type LOCAL_DATE_TYPE = new TypeToken<LocalDate>(){}.getType();

	  /** The specific genericized type for {@code LocalDateTime}. */
	  public static final Type LOCAL_DATE_TIME_TYPE = new TypeToken<LocalDateTime>(){}.getType();

	  /** The specific genericized type for {@code LocalTime}. */
	  public static final Type LOCAL_TIME_TYPE = new TypeToken<LocalTime>(){}.getType();

	  /** The specific genericized type for {@code OffsetDateTime}. */
	  public static final Type OFFSET_DATE_TIME_TYPE = new TypeToken<OffsetDateTime>(){}.getType();

	  /** The specific genericized type for {@code OffsetTime}. */
	  public static final Type OFFSET_TIME_TYPE = new TypeToken<OffsetTime>(){}.getType();

	  /** The specific genericized type for {@code ZonedDateTime}. */
	  public static final Type ZONED_DATE_TIME_TYPE = new TypeToken<ZonedDateTime>(){}.getType();

	  /** The specific genericized type for {@code Instant}. */
	  public static final Type INSTANT_TYPE = new TypeToken<Instant>(){}.getType();

	  /** The specific genericized type for {@code ZoneId}. */
	  public static final Type ZONE_ID_TYPE = new TypeToken<ZoneId>(){}.getType();

	  /** The specific genericized type for {@code Duration}. */
	  public static final Type DURATION_TYPE = new TypeToken<Duration>(){}.getType();

	  /**
	   * Registers all the Java Time converters.
	   * @param builder The GSON builder to register the converters with.
	   * @return A reference to {@code builder}.
	   */
	  public static GsonBuilder registerAll(GsonBuilder builder)
	  {
	    if (builder == null) { throw new NullPointerException("builder cannot be null"); }

	    registerLocalDate(builder);
	    registerLocalDateTime(builder);
	    registerLocalTime(builder);
	    registerOffsetDateTime(builder);
	    registerOffsetTime(builder);
	    registerZonedDateTime(builder);
	    registerInstant(builder);
	    registerZoneId(builder);
	    registerDuration(builder);

	    return builder;
	  }

	  /**
	   * Registers the {@link LocalDateConverter} converter.
	   * @param builder The GSON builder to register the converter with.
	   * @return A reference to {@code builder}.
	   */
	  public static GsonBuilder registerLocalDate(GsonBuilder builder)
	  {
	    builder.registerTypeAdapter(LOCAL_DATE_TYPE, new LocalDateConverter());

	    return builder;
	  }

	  /**
	   * Registers the {@link LocalDateTimeConverter} converter.
	   * @param builder The GSON builder to register the converter with.
	   * @return A reference to {@code builder}.
	   */
	  public static GsonBuilder registerLocalDateTime(GsonBuilder builder)
	  {
	    builder.registerTypeAdapter(LOCAL_DATE_TIME_TYPE, new LocalDateTimeConverter());

	    return builder;
	  }

	  /**
	   * Registers the {@link LocalTimeConverter} converter.
	   * @param builder The GSON builder to register the converter with.
	   * @return A reference to {@code builder}.
	   */
	  public static GsonBuilder registerLocalTime(GsonBuilder builder)
	  {
	    builder.registerTypeAdapter(LOCAL_TIME_TYPE, new LocalTimeConverter());

	    return builder;
	  }

	  /**
	   * Registers the {@link OffsetDateTimeConverter} converter.
	   * @param builder The GSON builder to register the converter with.
	   * @return A reference to {@code builder}.
	   */
	  public static GsonBuilder registerOffsetDateTime(GsonBuilder builder)
	  {
	    builder.registerTypeAdapter(OFFSET_DATE_TIME_TYPE, new OffsetDateTimeConverter());

	    return builder;
	  }

	  /**
	   * Registers the {@link OffsetTimeConverter} converter.
	   * @param builder The GSON builder to register the converter with.
	   * @return A reference to {@code builder}.
	   */
	  public static GsonBuilder registerOffsetTime(GsonBuilder builder)
	  {
	    builder.registerTypeAdapter(OFFSET_TIME_TYPE, new OffsetTimeConverter());

	    return builder;
	  }

	  /**
	   * Registers the {@link ZonedDateTimeConverter} converter.
	   * @param builder The GSON builder to register the converter with.
	   * @return A reference to {@code builder}.
	   */
	  public static GsonBuilder registerZonedDateTime(GsonBuilder builder)
	  {
	    builder.registerTypeAdapter(ZONED_DATE_TIME_TYPE, new ZonedDateTimeConverter());

	    return builder;
	  }

	  /**
	   * Registers the {@link InstantConverter} converter.
	   * @param builder The GSON builder to register the converter with.
	   * @return A reference to {@code builder}.
	   */
	  public static GsonBuilder registerInstant(GsonBuilder builder)
	  {
	    builder.registerTypeAdapter(INSTANT_TYPE, new InstantConverter());
	    
	    return builder;
	  }

	    /**
	     * Registers the {@link ZoneIdConverter} converter.
	     * @param builder The GSON builder to register the converter with.
	     * @return A reference to {@code builder}.
	     */
	   public static GsonBuilder registerZoneId(GsonBuilder builder)
	   {
	     builder.registerTypeAdapter(ZONE_ID_TYPE, new ZoneIdConverter());

	     return builder;
	   }

	  /**
	   * Registers the {@link DurationConverter} converter.
	   * @param builder The GSON builder to register the converter with.
	   * @return A reference to {@code builder}.
	   */
	  public static GsonBuilder registerDuration(GsonBuilder builder)
	  {
	    builder.registerTypeAdapter(DURATION_TYPE, new Object());

	    return builder;
	  }
}
