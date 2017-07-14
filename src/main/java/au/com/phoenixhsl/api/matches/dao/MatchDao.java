package au.com.phoenixhsl.api.matches.dao;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.hibernate.Session;

import au.com.phoenixhsl.api.matches.model.League;
import au.com.phoenixhsl.api.matches.model.Match;
import au.com.phoenixhsl.api.matches.model.MatchDifficult;
import au.com.phoenixhsl.api.matches.model.Team;

public class MatchDao {

	private final Session session;

	public MatchDao(Session session) {
		this.session = session;
	}

	public List<Match> getBy(String home, String away, MatchDifficult matchDifficult, LocalDate startDate, LocalDate endDate) {
		
		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<Match> criteria = builder.createQuery(Match.class);
		Root<Match> matches = criteria.from(Match.class);

		matches.fetch("homeTeam");
		matches.fetch("awayTeam");
		matches.fetch("league");

		List<Predicate> predicates = new ArrayList<>();
		
		if (home != null) {
			predicates.add(builder.like(builder.upper(matches.join("homeTeam").get("name")), "%" + home.toUpperCase() + "%"));
		}
		
		if (away != null) { 
			predicates.add(builder.like(builder.upper(matches.join("awayTeam").get("name")), "%" + away.toUpperCase() + "%"));
		}
		
		if (matchDifficult != null) { 
			predicates.add(builder.equal(matches.get("matchDifficult"), matchDifficult));
		}
		
		if (startDate != null) { 
			predicates.add(builder.greaterThanOrEqualTo(matches.get("date"), startDate));
		}
		
		if (endDate != null) { 
			predicates.add(builder.lessThanOrEqualTo(matches.get("date"), endDate));
		}
		
		criteria.select(matches).where(predicates.toArray(new Predicate[]{}));

		return session.createQuery(criteria).getResultList();
	}
	
	public void persist(Match match) {
		
		persist(match.getHomeTeam());
		persist(match.getAwayTeam());
		persist(match.getLeague());
		
		this.session.persist(match);
	}

	public void update(Match match) {

		persist(match.getHomeTeam());
		persist(match.getAwayTeam());
		persist(match.getLeague());

		this.session.update(match);
	}
	
	private void persist(Team newTeam) {

		Team team = session.createQuery("FROM Team WHERE name = :name", Team.class)
							.setParameter("name", newTeam.getName())
							.getResultList()
							.stream()
							.findFirst()
							.orElse(null);

		if (team != null) {
			newTeam.setId(team.getId());
		}
		else {
			session.persist(newTeam);
		}
	}
	
	private void persist(League newLeague) {

		League league = session.createQuery("FROM League WHERE name = :name", League.class)
								.setParameter("name", newLeague.getName())
								.getResultList()
								.stream()
								.findFirst()
								.orElse(null);

		if (league != null) {
			newLeague.setId(league.getId());
		}
		else {
			session.persist(newLeague);
		}
	}
}