package au.com.phoenixhsl.api.matches.model;

public enum MatchDifficult {

	// using constructor to follow string format on user stories
	TOUGH_GAME("Tough_game"), AVERAGE_GAME("Average_game"), MAJOR_WIN("Major_win");

	private String description;

	private MatchDifficult(String description) {
		this.description = description;
	}

	public String getDescription() {
		return description;
	}
	
	@Override
	public String toString() {
		return this.getDescription();
	}
}