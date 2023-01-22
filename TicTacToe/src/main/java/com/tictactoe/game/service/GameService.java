package com.tictactoe.game.service;

import org.springframework.stereotype.Service;

import com.tictactoe.game.enumeration.Player;
import com.tictactoe.game.service.impl.GameBoard;
import com.tictcatoe.game.exception.InvalidTurnException;

@Service
public class GameService {

	private final GameBoard gameBoard;

	public GameService(GameBoard gameBoard) {
		this.gameBoard = gameBoard;
	}

	public String playGame(Player player, int row, int column) {
		String message = null;
		if (player == Player.X)
			message = "Player X moved first";
		else if (player == Player.O)
			throw new InvalidTurnException("Player X should move first");
		return message;
	}
}