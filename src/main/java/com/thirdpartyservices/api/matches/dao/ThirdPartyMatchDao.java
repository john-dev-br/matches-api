package com.thirdpartyservices.api.matches.dao;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

import com.thirdpartyservices.api.matches.model.ThirdPartyMatch;

public class ThirdPartyMatchDao {

	private static List<ThirdPartyMatch> database = new ArrayList<>();
	private static long count = 0;

	static {
		
		database.add(new ThirdPartyMatch(++count, "Premier League", "AFC Bournemouth", "Hull City", LocalDate.of(2016, Month.OCTOBER, 15), 6, 1));
		database.add(new ThirdPartyMatch(++count, "Premier League", "Chelsea", "Everton", LocalDate.of(2016, Month.NOVEMBER, 5), 5, 0));
		database.add(new ThirdPartyMatch(++count, "Premier League", "Liverpool", "Watford", LocalDate.of(2016, Month.NOVEMBER, 6), 6, 1));
		database.add(new ThirdPartyMatch(++count, "Premier League", "Tottenham Hotspur", "Swansea City", LocalDate.of(2016, Month.DECEMBER, 3), 5, 0));
		database.add(new ThirdPartyMatch(++count, "Premier League", "Manchester City", "Crystal Palace", LocalDate.of(2017, Month.MAY, 6), 5, 0));
		database.add(new ThirdPartyMatch(++count, "Campeonato Brasileiro", "Vasco da Gama", "Atletico Goianiense", LocalDate.of(2017, Month.JUNE, 25), 1, 0));
		database.add(new ThirdPartyMatch(++count, "Campeonato Brasileiro", "Atletico-PR", "Vitoria", LocalDate.of(2017, Month.JUNE, 25), 4, 1));
		database.add(new ThirdPartyMatch(++count, "Campeonato Brasileiro", "Gremio", "Corinthians", LocalDate.of(2017, Month.JUNE, 25), 0, 1));
		database.add(new ThirdPartyMatch(++count, "Campeonato Brasileiro", "Bahia", "Flamengo", LocalDate.of(2017, Month.JUNE, 25), 0, 1));
		database.add(new ThirdPartyMatch(++count, "Campeonato Brasileiro", "Sao Paulo", "Fluminense", LocalDate.of(2017, Month.JUNE, 25), 1, 1));
		database.add(new ThirdPartyMatch(++count, "Campeonato Brasileiro", "Cruzeiro", "Coritiba", LocalDate.of(2017, Month.JUNE, 25), 2, 0));
		database.add(new ThirdPartyMatch(++count, "Campeonato Brasileiro", "Ponte Preta", "Palmeiras", LocalDate.of(2017, Month.JUNE, 25), 1, 2));
		database.add(new ThirdPartyMatch(++count, "Campeonato Brasileiro", "Chapecoense", "Atletico Mineiro", LocalDate.of(2017, Month.JUNE, 25), 0, 1));
	}
	
	public List<ThirdPartyMatch> getAll() {
		return database;
	}
}