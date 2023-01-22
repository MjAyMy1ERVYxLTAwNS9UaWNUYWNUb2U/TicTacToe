package com.tictactoe.game.service;

import org.springframework.stereotype.Service;

import com.tictactoe.game.enumeration.Player;
import com.tictactoe.game.enumeration.Position;
import com.tictactoe.game.response.GameResponse;
import com.tictactoe.game.service.impl.GameBoard;
import com.tictcatoe.game.exception.InvalidTurnException;

@Service
public class GameService {

	private final GameBoard gameBoard;
	private static final int ZERO = 0;

	public GameService(GameBoard gameBoard) {
		this.gameBoard = gameBoard;
	}

	public GameResponse playGame(Player player, int position) {
		if (isFirstTurn() && player.equals(Player.O)) {
			throw new InvalidTurnException("Player X should move first");
		}
		gameBoard.setPlayerInPosition(Position.getRowColumnValueOfPosition(position), player);
		return new GameResponse("GAME_IN_PROGRESS", getNextPlayer(player), player);
	}

	private Player getNextPlayer(Player player) {
		return player == Player.X ? Player.O : Player.X;
	}

	private boolean isFirstTurn() {
		return gameBoard.getCountOfPositionsOccupied() == ZERO;
	}
}