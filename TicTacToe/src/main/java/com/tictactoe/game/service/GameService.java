package com.tictactoe.game.service;

import org.springframework.stereotype.Service;

import com.tictactoe.game.enumeration.Player;
import com.tictactoe.game.response.GameResponse;
import com.tictactoe.game.service.impl.GameBoard;
import com.tictcatoe.game.exception.InvalidTurnException;

@Service
public class GameService {

	private final GameBoard gameBoard;

	public GameService(GameBoard gameBoard) {
		this.gameBoard = gameBoard;
	}

	public GameResponse playGame(Player player, int row, int column) {
		if (player == Player.X) {
			gameBoard.setPlayerInPosition(row, column, player);
		} else if (player == Player.O) {
			throw new InvalidTurnException("Player X should move first");
		}
		return new GameResponse("GAME_IN_PROGRESS", getNextPlayer(player), player);
	}

	private Player getNextPlayer(Player player) {
		return player == Player.X ? Player.O : Player.X;
	}
}