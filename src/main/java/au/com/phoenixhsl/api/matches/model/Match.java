package au.com.phoenixhsl.api.matches.model;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import au.com.phoenixhsl.api.matches.jersey.MatchDeserializer;
import au.com.phoenixhsl.api.matches.jersey.MatchSerializer;

@Entity
@JsonSerialize(using = MatchSerializer.class)
@JsonDeserialize(using = MatchDeserializer.class)
public class Match implements Serializable {

	private static final long serialVersionUID = -6053630560208221597L;

	@Id
	@Column(nullable = false)
	private Long id;

	@ManyToOne
	@JoinColumn(nullable = false)
	private League league;

	@ManyToOne
	@JoinColumn(nullable = false)
	private Team homeTeam;

	@ManyToOne
	@JoinColumn(nullable = false)
	private Team awayTeam;

	@Column(nullable = false)
	private LocalDate date;

	@Column(nullable = false)
	private int homeScore;

	@Column(nullable = false)
	private int awayScore;

	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private MatchDifficult matchDifficult;

	private Match() {
	}

	public Match(Long id, String league, String homeTeam, String awayTeam, LocalDate date, int homeScore, int awayScore) {

		this.id = id;
		this.league = new League(league);
		this.homeTeam = new Team(homeTeam);
		this.awayTeam = new Team(awayTeam);
		this.date = date;
		this.homeScore = homeScore;
		this.awayScore = awayScore;
		
		updateMatchDifficult();
	}

	public Long getId() {
		return id;
	}

	public League getLeague() {
		return league;
	}

	public Team getHomeTeam() {
		return homeTeam;
	}

	public Team getAwayTeam() {
		return awayTeam;
	}

	public LocalDate getDate() {
		return date;
	}

	public int getHomeScore() {
		return homeScore;
	}

	public int getAwayScore() {
		return awayScore;
	}

	public MatchDifficult getMatchDifficult() {
		return matchDifficult;
	}

	public void updateMatchDifficult() {
	
		int absoluteValue = Math.abs(homeScore - awayScore);

		if (absoluteValue == 0) {
			this.matchDifficult = MatchDifficult.TOUGH_GAME;
		} else if (absoluteValue <= 2) {
			this.matchDifficult = MatchDifficult.AVERAGE_GAME;
		} else {
			this.matchDifficult = MatchDifficult.MAJOR_WIN;
		}
	}
}