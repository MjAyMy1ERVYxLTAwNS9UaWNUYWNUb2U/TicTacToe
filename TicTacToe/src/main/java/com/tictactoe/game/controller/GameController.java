
package com.tictactoe.game.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tictactoe.game.enumeration.Player;
import com.tictactoe.game.response.GameResponse;
import com.tictactoe.game.response.PlayerAndPositionRequest;
import com.tictactoe.game.service.GameService;

@RestController
@RequestMapping("/tictactoe")
public class GameController {

	@Autowired
	private GameService gameService;

	public GameController(GameService gameService) {
		this.gameService = gameService;
	}

	@PostMapping(value = "/play")
	public ResponseEntity<GameResponse> playGameHandler(
			@RequestBody PlayerAndPositionRequest playerAndPositionRequest) {
		return ResponseEntity.status(HttpStatus.OK).body(
				gameService.playGame(playerAndPositionRequest.getPlayer(), playerAndPositionRequest.getPosition()));
	}

	@GetMapping(value = "/resetgame")
	public ResponseEntity<String> resetGameHandler() {
		return ResponseEntity.status(HttpStatus.OK).body(gameService.resetGame());
	}

}