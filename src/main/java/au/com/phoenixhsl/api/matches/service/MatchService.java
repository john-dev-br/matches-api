package au.com.phoenixhsl.api.matches.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;

import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.persistence.OptimisticLockException;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import com.thirdpartyservices.api.matches.model.ThirdPartyMatch;
import com.thirdpartyservices.api.matches.model.ThirdPartyMatchWrapper;

import au.com.phoenixhsl.api.matches.dao.DaoFactory;
import au.com.phoenixhsl.api.matches.exception.ApiException;
import au.com.phoenixhsl.api.matches.model.ImportMessage;
import au.com.phoenixhsl.api.matches.model.Match;
import au.com.phoenixhsl.api.matches.model.MatchDifficult;
import au.com.phoenixhsl.api.matches.model.MatchWrapper;

@Path("/matches")
public class MatchService {

	@Inject
	private DaoFactory daoFactory;

	@Context
	private UriInfo uri;
	
	@GET @RolesAllowed("SPORTS_ANALIST")
	@Path("/search")
	@Produces(MediaType.APPLICATION_JSON)
	public MatchWrapper get() {

		MultivaluedMap<String, String> queryParams = uri.getQueryParameters();

		String home = queryParams.getFirst("home");
		String away = queryParams.getFirst("away");
		String pMatchDifficult = queryParams.getFirst("Match_Difficult");
		String pStartDate = queryParams.getFirst("start_date");
		String pEndDate = queryParams.getFirst("end_date");

		MatchDifficult matchDifficult = null;

		try {
			if (pMatchDifficult != null) {
				matchDifficult = MatchDifficult.valueOf(pMatchDifficult.toUpperCase());
			}
		} catch (Exception e) {
			throw new ApiException(Response.Status.BAD_REQUEST.getStatusCode()
					, "Wrong 'Match_Difficult' parameter. Values allowed: " + Arrays.toString(MatchDifficult.values()));
		}

		DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

		LocalDate startDate = null;

		try {
			if (pStartDate != null) {
				startDate = LocalDate.parse(pStartDate, dateFormatter);
			}
		} catch (Exception e) {
			throw new ApiException(Response.Status.BAD_REQUEST.getStatusCode()
					, "Wrong 'start_date' format. Format allowed: dd/mm/yyyy");
		}

		LocalDate endDate = null;

		try {
			if (pEndDate != null) {
				endDate = LocalDate.parse(pEndDate, dateFormatter);
			}
		} catch (Exception e) {
			throw new ApiException(Response.Status.BAD_REQUEST.getStatusCode()
					, "Wrong 'end_date' format. Format allowed: dd/mm/yyyy");
		}

		return new MatchWrapper("match", daoFactory.getMatchDao().getBy(home, away, matchDifficult, startDate, endDate));
	}

	@PUT @RolesAllowed("SPORTS_ANALIST")
	@Path("/update")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response update(Match match) {

		try {

			daoFactory.beginTransaction();
			daoFactory.getMatchDao().update(match);
			daoFactory.commit();

		} catch (OptimisticLockException e) {

			daoFactory.rollback();
			throw new ApiException(Response.Status.NOT_FOUND.getStatusCode()
					, "Match id " + match.getId() + " not found. Unable to update.");
			
		} catch (Exception e) {

			daoFactory.rollback();
			throw new ApiException(Response.Status.INTERNAL_SERVER_ERROR.getStatusCode()
					, "Error: " + e.getMessage());
		}
		
		return Response.ok().build();
	}

	@PUT
	@Path("/update-from-third-party")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public ImportMessage update(ThirdPartyMatchWrapper thirdPartyMatchWrapper) {

		List<ThirdPartyMatch> matches = thirdPartyMatchWrapper.getMatches().get("match");
		ImportMessage reportMessage = new ImportMessage();
		
		for (ThirdPartyMatch match : matches) {

			try {

				daoFactory.beginTransaction();

				daoFactory.getMatchDao().persist(new Match(match.getId()
													, match.getLeague()
													, match.getHome()
													, match.getAway()
													, match.getDate()
													, match.getHomeScore()
													, match.getAwayScore()));

				daoFactory.commit();

				reportMessage.addSavedMatch(match);
				
			} catch (Exception e) {

				reportMessage.addUnsavedMatch(match);
				daoFactory.rollback();
			}
		}

		return reportMessage;
	}

	@GET
	@Path("/run-update-from-third-party")
	@Produces(MediaType.APPLICATION_JSON)
	public ImportMessage runUpdateFromThirdParty() {
		
		Client client = ClientBuilder.newClient();
		WebTarget target = client.target(uri.getBaseUri()).path("third-party-matches/all");
		Invocation.Builder builder = target.request(MediaType.APPLICATION_JSON_TYPE);
	
		return update(builder.get().readEntity(ThirdPartyMatchWrapper.class));
	}
}