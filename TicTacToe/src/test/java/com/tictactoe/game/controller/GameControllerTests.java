package com.tictactoe.game.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.tictactoe.game.enumeration.Player;
import com.tictactoe.game.service.GameService;
import com.tictcatoe.game.exception.InvalidTurnException;

@RunWith(SpringRunner.class)
@WebMvcTest
class GameControllerTests {

	@Autowired
	private MockMvc mockMvc;

	@Mock
	private GameService gameService;

	@Test
	void playGameAPIShouldReturnOkStatus() throws Exception {
		when(gameService.playGame(Player.X)).thenReturn("Player X moved first");

		mockMvc.perform(post("/tictactoe/play/{player}", Player.X)).andExpect(status().isOk());
	}

	@Test
	public void playGameAPIShouldShowForbiddenHttpStatusWhenInValidMove() throws Exception {
		when(gameService.playGame(Player.O)).thenThrow(new InvalidTurnException("Player X should move first"));

		mockMvc.perform(post("/tictactoe/play/{player}", Player.O)).andExpect(status().isForbidden());
	}

}