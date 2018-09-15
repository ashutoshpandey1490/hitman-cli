package com.assignment.hitman.state;

import static org.junit.Assert.assertTrue;

import com.assignment.hitman.util.MessageConstants;
import org.junit.Test;

/**
 * Test class for all the state enums.
 *
 * @author ashutoshp
 */
public class AllStateTest {

	@Test
	public void testContinueGameState() {
		assertTrue("should get 1", ContinueGame.CONTINUE.getKey() == 1);
		assertTrue("should get 2", ContinueGame.EXIT.getKey() == 2);

		assertTrue(
				"should get CONTINUE", ContinueGame.CONTINUE.getValue() == MessageConstants.CONTINUE);
		assertTrue("should get EXIT", ContinueGame.EXIT.getValue() == MessageConstants.EXIT);

		assertTrue(
				"Shoulld get CONTINUE", ContinueGame.CONTINUE.equals(ContinueGame.getOptionByKey(1)));
		assertTrue("Shoulld get EXIT", ContinueGame.EXIT.equals(ContinueGame.getOptionByKey(2)));
	}

	@Test
	public void testResumeGameState() {
		assertTrue("should get 1", ResumeGame.START.getKey() == 1);
		assertTrue("should get 2", ResumeGame.RESUME.getKey() == 2);
		assertTrue("should get 3", ResumeGame.EXIT.getKey() == 3);

		assertTrue("should get START", ResumeGame.START.getValue() == MessageConstants.START);
		assertTrue("should get RESUME", ResumeGame.RESUME.getValue() == MessageConstants.RESUME);
		assertTrue("should get EXIT", ResumeGame.EXIT.getValue() == MessageConstants.EXIT);

		assertTrue("Shoulld get START", ResumeGame.START.equals(ResumeGame.getOptionByKey(1)));
		assertTrue("Shoulld get RESUME", ResumeGame.RESUME.equals(ResumeGame.getOptionByKey(2)));
		assertTrue("Shoulld get EXIT", ResumeGame.EXIT.equals(ResumeGame.getOptionByKey(3)));
	}

	@Test
	public void testStartFightState() {
		assertTrue("should get 1", StartFight.FIRE.getKey() == 1);
		assertTrue("should get 2", StartFight.EXIT.getKey() == 2);

		assertTrue("should get FIRE", StartFight.FIRE.getValue() == MessageConstants.FIRE);
		assertTrue("should get EXIT", StartFight.EXIT.getValue() == MessageConstants.EXIT);

		assertTrue("Shoulld get START", StartFight.FIRE.equals(StartFight.getOptionByKey(1)));
		assertTrue("Shoulld get EXIT", StartFight.EXIT.equals(StartFight.getOptionByKey(2)));
	}

	@Test
	public void testStartFreshState() {
		assertTrue("should get 1", StartFresh.START.getKey() == 1);
		assertTrue("should get 2", StartFresh.EXIT.getKey() == 2);

		assertTrue("should get START", StartFresh.START.getValue() == MessageConstants.START);
		assertTrue("should get EXIT", StartFresh.EXIT.getValue() == MessageConstants.EXIT);

		assertTrue("Shoulld get START", StartFresh.START.equals(StartFresh.getOptionByKey(1)));
		assertTrue("Shoulld get EXIT", StartFresh.EXIT.equals(StartFresh.getOptionByKey(2)));
	}

	@Test
	public void testStartHittingState() {
		assertTrue("should get 1", StartHitting.START_HITTING.getKey() == 1);
		assertTrue("should get 2", StartHitting.EXIT.getKey() == 2);

		assertTrue(
				"should get START_HITTING",
				StartHitting.START_HITTING.getValue() == MessageConstants.START_HITTING);
		assertTrue("should get EXIT", StartHitting.EXIT.getValue() == MessageConstants.EXIT);

		assertTrue(
				"Shoulld get START_HITTING",
				StartHitting.START_HITTING.equals(StartHitting.getOptionByKey(1)));
		assertTrue("Shoulld get EXIT", StartHitting.EXIT.equals(StartHitting.getOptionByKey(2)));
	}

	@Test
	public void testStartJourneyState() {
		assertTrue("should get 1", StartJourney.START_GAME.getKey() == 1);
		assertTrue("should get 2", StartJourney.NEW_WEAPON.getKey() == 2);
		assertTrue("should get 3", StartJourney.VIEW_PLAYER.getKey() == 3);
		assertTrue("should get 4", StartJourney.EXIT.getKey() == 4);

		assertTrue(
				"should get START_GAME", StartJourney.START_GAME.getValue() == MessageConstants.START_GAME);
		assertTrue(
				"should get NEW_WEAPON", StartJourney.NEW_WEAPON.getValue() == MessageConstants.NEW_WEAPON);
		assertTrue(
				"should get VIEW_PLAYER",
				StartJourney.VIEW_PLAYER.getValue() == MessageConstants.VIEW_PLAYER);
		assertTrue("should get EXIT", StartJourney.EXIT.getValue() == MessageConstants.EXIT);

		assertTrue(
				"Shoulld get START_GAME", StartJourney.START_GAME.equals(StartJourney.getOptionByKey(1)));
		assertTrue(
				"Shoulld get NEW_WEAPON", StartJourney.NEW_WEAPON.equals(StartJourney.getOptionByKey(2)));
		assertTrue(
				"Shoulld get VIEW_PLAYER", StartJourney.VIEW_PLAYER.equals(StartJourney.getOptionByKey(3)));
		assertTrue("Shoulld get EXIT", StartJourney.EXIT.equals(StartJourney.getOptionByKey(4)));
	}

	@Test
	public void testViewPlayerState() {
		assertTrue("should get 1", ViewPlayer.GO_BACK.getKey() == 1);

		assertTrue("should get GO_BACK", ViewPlayer.GO_BACK.getValue() == MessageConstants.GO_BACK);

		assertTrue("Shoulld get GO_BACK", ViewPlayer.GO_BACK.equals(ViewPlayer.getOptionByKey(1)));
	}
}
