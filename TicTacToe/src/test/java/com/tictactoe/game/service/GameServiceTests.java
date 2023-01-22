package com.tictactoe.game.service;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import com.tictactoe.game.enumeration.Player;
import com.tictcatoe.game.exception.InvalidTurnException;

@RunWith(MockitoJUnitRunner.class)
public class GameServiceTests {

	private GameService gameService;

	@Before
	public void setUp() {
		gameService = new GameService();
	}

	@Test
	public void playerXShouldAlwaysGoFirst() {
		assertThat(gameService.playGame(Player.X)).isEqualTo("Player X moved first");
	}

	@Test(expected = InvalidTurnException.class)
	public void playGameShouldThrowInvalidTurnExceptionIfPlayerOMovesFirst() {
		gameService.playGame(Player.O);
	}
}