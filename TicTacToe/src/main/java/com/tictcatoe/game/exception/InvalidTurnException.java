package com.tictcatoe.game.exception;

public class InvalidTurnException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public InvalidTurnException(String message) {
		super(message);
	}
}