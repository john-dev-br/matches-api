package au.com.phoenixhsl.api.matches.jersey;

import java.io.IOException;
import java.time.format.DateTimeFormatter;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import au.com.phoenixhsl.api.matches.model.Match;

public class MatchSerializer extends StdSerializer<Match> {

	private static final long serialVersionUID = -3711087537160915682L;

	public MatchSerializer() {
		this(null);
	}

	public MatchSerializer(Class<Match> t) {
		super(t);
	}

	@Override
	public void serialize(Match value, JsonGenerator jgen, SerializerProvider provider)
			throws IOException, JsonProcessingException {

		jgen.writeStartObject();
		jgen.writeNumberField("id", value.getId());
		jgen.writeStringField("league", value.getLeague().getName());
		jgen.writeStringField("home", value.getHomeTeam().getName());
		jgen.writeStringField("away", value.getAwayTeam().getName());
		jgen.writeStringField("date", DateTimeFormatter.ofPattern("dd/MM/yyyy").format(value.getDate()));
		jgen.writeNumberField("home_score", value.getHomeScore());
		jgen.writeNumberField("away_score", value.getAwayScore());
		jgen.writeStringField("Match_Difficult", value.getMatchDifficult().getDescription());
		jgen.writeEndObject();
	}
}