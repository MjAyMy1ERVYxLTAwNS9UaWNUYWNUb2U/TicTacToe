package com.tictactoe.game.response;

import com.tictactoe.game.enumeration.Player;

import lombok.Getter;

@Getter
public class GameResponse {

	private String status;
	private Player nextPlayer;
	private Player currentPlayer;

	public GameResponse(String status, Player nextPlayer, Player currentPlayer) {
		this.status = status;
		this.nextPlayer = nextPlayer;
		this.currentPlayer = currentPlayer;
	}
}