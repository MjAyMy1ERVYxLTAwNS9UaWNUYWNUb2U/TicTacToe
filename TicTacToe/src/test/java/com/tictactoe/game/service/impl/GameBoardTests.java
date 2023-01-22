package com.tictactoe.game.service.impl;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import com.tictactoe.game.enumeration.Player;
import com.tictactoe.game.enumeration.Position;

@RunWith(MockitoJUnitRunner.class)
public class GameBoardTests {

	private GameBoard gameBoard;

	@Before
	public void setUp() {
		gameBoard = new GameBoard();
	}

	@Test
	public void playerXShouldBeMarkedInGameBoardWithGivenPosition() {
		gameBoard.setPlayerInPosition(Position.TWO, Player.X);
		assertThat(gameBoard.getPlayerInPosition(Position.TWO)).isEqualTo(Player.X.getValue());
	}

	@Test
	public void getCountPositionsOccupiedOnGameBoard() {
		assertThat(gameBoard.getCountOfPositionsOccupied()).isZero();
	}
}