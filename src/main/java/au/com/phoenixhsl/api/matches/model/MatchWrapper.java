package au.com.phoenixhsl.api.matches.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MatchWrapper {

	private Map<String, List<Match>> matches;

	public MatchWrapper() {
	}

	public MatchWrapper(String key, List<Match> values) {

		matches = new HashMap<>();
		matches.put(key, values);
	}

	public Map<String, List<Match>> getMatches() {
		return matches;
	}

	public void setMatches(Map<String, List<Match>> matches) {
		this.matches = matches;
	}
}