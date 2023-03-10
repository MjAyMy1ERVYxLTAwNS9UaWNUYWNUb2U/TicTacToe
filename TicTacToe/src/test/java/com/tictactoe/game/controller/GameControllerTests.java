package com.tictactoe.game.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.tictactoe.game.enumeration.Player;
import com.tictactoe.game.enumeration.Position;
import com.tictactoe.game.response.GameResponse;
import com.tictactoe.game.service.GameService;
import com.tictcatoe.game.exception.InvalidPositionException;
import com.tictcatoe.game.exception.InvalidTurnException;
import com.tictcatoe.game.exception.PositionOccupiedException;

@RunWith(SpringRunner.class)
@WebMvcTest
class GameControllerTests {

	@Autowired
	private MockMvc mockMvc;

	@Mock
	private GameService gameService;

	@Test
	void playGameAPIShouldReturnOkStatus() throws Exception {
		when(gameService.playGame(Player.X, Position.ONE.getValue()))
		.thenReturn(new GameResponse("GAME_IN_PROGRESS", Player.O, Player.X));

		mockMvc.perform(post("/tictactoe/play").contentType(MediaType.APPLICATION_JSON)
				.content("{ \"player\": \"X\", \"position\": 1 }")).andExpect(status().isOk());
	}

	@Test
	public void playGameAPIShouldShowForbiddenHttpStatusWhenInValidMove() throws Exception {
		when(gameService.playGame(Player.O, Position.TWO.getValue()))
		.thenThrow(new InvalidTurnException("Player X should move first"));

		mockMvc.perform(post("/tictactoe/play").contentType(MediaType.APPLICATION_JSON)
				.content("{ \"player\": \"O\", \"position\": 2 }")).andExpect(status().isForbidden());
		}

	@Test
	public void playGameAPIShouldShowForbiddenHttpStatusWhenPositionOccupiedExceptionIsThrown() throws Exception {
		when(gameService.playGame(Player.X, Position.FIVE.getValue()))
				.thenThrow(new PositionOccupiedException("Position %s is already occupied"));

		mockMvc.perform(post("/tictactoe/play").contentType(MediaType.APPLICATION_JSON)
				.content("{ \"player\": \"X\", \"position\": 5 }")).andExpect(status().isForbidden());
	}
	
	@Test
	public void playGameAPIShouldShowForbiddenHttpStatusWhenInvalidPositionExceptionIsThrown() throws Exception {
		when(gameService.playGame(Player.X, Position.DEFAULT.getValue()))
				.thenThrow(new InvalidPositionException("Position %s is already occupied"));

		mockMvc.perform(post("/tictactoe/play").contentType(MediaType.APPLICATION_JSON)
				.content("{ \"player\": \"X\", \"position\": 0 }")).andExpect(status().isForbidden());
	}

	@Test
	public void resetGameHandlerAPIFound() throws Exception {
		mockMvc.perform(get("/tictactoe/resetgame")).andExpect(status().isOk());
	}
}