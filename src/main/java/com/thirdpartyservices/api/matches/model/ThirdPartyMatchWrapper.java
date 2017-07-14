package com.thirdpartyservices.api.matches.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ThirdPartyMatchWrapper {

	private Map<String, List<ThirdPartyMatch>> matches;

	public ThirdPartyMatchWrapper() {
	}

	public ThirdPartyMatchWrapper(String key, List<ThirdPartyMatch> values) {

		matches = new HashMap<>();
		matches.put(key, values);
	}

	public Map<String, List<ThirdPartyMatch>> getMatches() {
		return matches;
	}

	public void setMatches(Map<String, List<ThirdPartyMatch>> matches) {
		this.matches = matches;
	}
}