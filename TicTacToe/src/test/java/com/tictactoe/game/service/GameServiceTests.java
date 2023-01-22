package com.tictactoe.game.service;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import com.tictactoe.game.enumeration.Player;
import com.tictactoe.game.enumeration.Position;
import com.tictactoe.game.service.impl.GameBoard;
import com.tictcatoe.game.exception.InvalidTurnException;

@RunWith(MockitoJUnitRunner.class)
public class GameServiceTests {

	private GameService gameService;
	private GameBoard gameBoard;

	@Before
	public void setUp() {
		gameBoard = new GameBoard();
		gameService = new GameService(gameBoard);
	}

	@Test
	public void playerXShouldAlwaysGoFirst() {
		assertThat(gameService.playGame(Player.X, Position.ONE.getValue()).getCurrentPlayer()).isEqualTo(Player.X);
	}

	@Test(expected = InvalidTurnException.class)
	public void playGameShouldThrowInvalidTurnExceptionIfPlayerOMovesFirst() {
		gameService.playGame(Player.O, Position.TWO.getValue());
	}

	@Test
	public void getPositionFromPlayerAndSaveOnGameBoard() {
		gameService.playGame(Player.X, Position.THREE.getValue());
		assertThat(gameBoard.getPlayerInPosition(Position.THREE)).isEqualTo(Player.X.getValue());
	}

	@Test
	public void shouldDeclareWinnerIfAnyOneOfThreeRowsIsFilledBySamePlayer() {
		gameService.playGame(Player.X, Position.ONE.getValue());
		gameService.playGame(Player.O, Position.TWO.getValue());
		assertThat(gameService.playGame(Player.X, Position.THREE.getValue()).getStatus()).isEqualTo("GAME_IN_PROGRESS");
	}
}