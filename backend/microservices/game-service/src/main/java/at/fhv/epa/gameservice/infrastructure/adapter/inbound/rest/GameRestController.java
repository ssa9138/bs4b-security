package at.fhv.epa.gameservice.infrastructure.adapter.inbound.rest;

import at.fhv.epa.gameservice.application.port.input.GameService;
import at.fhv.epa.gameservice.infrastructure.adapter.inbound.rest.dto.CreateGameDTO;
import at.fhv.epa.gameservice.infrastructure.adapter.inbound.rest.dto.GameDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/game")
public class GameRestController {
    private final GameService gameService;
    @CrossOrigin(origins = "*")
    @PostMapping("/create")
    public ResponseEntity<GameDTO> createGame(@RequestBody CreateGameDTO createGameDTO){
        return ResponseEntity.ok(gameService.createNewGame(createGameDTO));
    }
}
