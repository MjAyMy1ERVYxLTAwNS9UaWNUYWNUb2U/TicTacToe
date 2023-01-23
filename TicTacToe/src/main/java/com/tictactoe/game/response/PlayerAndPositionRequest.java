package com.tictactoe.game.response;

import com.tictactoe.game.enumeration.Player;

import lombok.Getter;

@Getter
public class PlayerAndPositionRequest {

	private Player player;
	private int position;
}