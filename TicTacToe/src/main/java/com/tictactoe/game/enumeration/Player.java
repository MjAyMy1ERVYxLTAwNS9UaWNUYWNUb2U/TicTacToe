package com.tictactoe.game.enumeration;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Player {

	X('X'), 
	O('O');

	private final Character value;

}