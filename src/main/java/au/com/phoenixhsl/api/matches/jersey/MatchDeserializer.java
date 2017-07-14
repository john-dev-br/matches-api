package au.com.phoenixhsl.api.matches.jersey;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.ws.rs.core.Response;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.fasterxml.jackson.databind.node.IntNode;

import au.com.phoenixhsl.api.matches.exception.ApiException;
import au.com.phoenixhsl.api.matches.model.Match;

public class MatchDeserializer extends StdDeserializer<Match> {

	private static final long serialVersionUID = -3079885053048885077L;

	public MatchDeserializer() {
		this(null);
	}

	public MatchDeserializer(Class<?> vc) {
		super(vc);
	}

	@Override
	public Match deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException, JsonProcessingException {

		Match match = null;

		try {

			JsonNode node = jp.getCodec().readTree(jp);

			long id = (int) ((IntNode) node.get("id")).numberValue();
			String league = node.get("league").asText();
			String homeTeam = node.get("home").asText();
			String awayTeam = node.get("away").asText();
			LocalDate date = LocalDate.parse(node.get("date").asText(), DateTimeFormatter.ofPattern("dd/MM/yyyy"));
			int homeScore = (int) ((IntNode) node.get("home_score")).numberValue();
			int awayScore = (int) ((IntNode) node.get("away_score")).numberValue();

			match = new Match(id, league, homeTeam, awayTeam, date, homeScore, awayScore);

		} catch (Exception e) {

			throw new ApiException(Response.Status.BAD_REQUEST.getStatusCode(), "Wrong match information.");
		}

		return match;
	}
}