package com.thirdpartyservices.api.matches.model;

import java.io.Serializable;
import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;

import au.com.phoenixhsl.api.matches.jersey.LocalDateDeserializer;
import au.com.phoenixhsl.api.matches.jersey.LocalDateSerializer;

public class ThirdPartyMatch implements Serializable {

	private static final long serialVersionUID = 4623165543808066402L;

	@JsonSerialize(using = ToStringSerializer.class)
	private long id;

	private String league;
	private String home;
	private String away;

	@JsonDeserialize(using = LocalDateDeserializer.class)  
	@JsonSerialize(using = LocalDateSerializer.class)
	private LocalDate date;

	@JsonProperty("home_score")
	@JsonSerialize(using = ToStringSerializer.class)
	private int homeScore;

	@JsonProperty("away_score")
	@JsonSerialize(using = ToStringSerializer.class)
	private int awayScore;

	private ThirdPartyMatch() {
	}
	
	public ThirdPartyMatch(long id, String league, String home, String away, LocalDate date, int homeScore, int awayScore) {

		this.id = id;
		this.league = league;
		this.home = home;
		this.away = away;
		this.date = date;
		this.homeScore = homeScore;
		this.awayScore = awayScore;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getLeague() {
		return league;
	}

	public String getHome() {
		return home;
	}

	public String getAway() {
		return away;
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
}