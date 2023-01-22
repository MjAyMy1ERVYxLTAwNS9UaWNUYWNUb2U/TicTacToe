package com.tictactoe.game.service;

import org.springframework.stereotype.Service;

@Service
public class GameService {

	public String playGame(char player) {
		return "Player X moved first";
	}
}