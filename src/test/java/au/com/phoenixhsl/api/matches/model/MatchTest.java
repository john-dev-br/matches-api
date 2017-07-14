package au.com.phoenixhsl.api.matches.model;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class MatchTest {

	@Test
	public void shouldReturnToughGame() {
		
		Match match = new Match(1L, null, null, null, null, 1, 1);
		assertEquals(MatchDifficult.TOUGH_GAME, match.getMatchDifficult());
	}

	@Test
	public void shouldReturnAverageGame() {

		Match match = new Match(1L, null, null, null, null, 3, 1);
		assertEquals(MatchDifficult.AVERAGE_GAME, match.getMatchDifficult());
	}

	@Test
	public void shouldReturnMajorWin() {

		Match match = new Match(1L, null, null, null, null, 4, 1);
		assertEquals(MatchDifficult.MAJOR_WIN, match.getMatchDifficult());
	}
}