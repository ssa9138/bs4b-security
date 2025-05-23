package at.fhv.epa.gameservice.infrastructure.adapter.inbound.websocket;

import at.fhv.epa.gameservice.infrastructure.adapter.inbound.websocket.dto.PlayerMoveDTO;
import at.fhv.epa.gameservice.application.port.input.GameService;
import at.fhv.epa.gameservice.infrastructure.adapter.inbound.websocket.dto.PlayerShootDTO;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;

@Controller
public class GameWebSocketController {
    private final GameService gameService;
    private int counter = 0;

    public GameWebSocketController(GameService gameService) {
        this.gameService = gameService;
    }

    @MessageMapping("/move")
    public void handleGameMove(PlayerMoveDTO playermovedto){
        gameService.processMove(playermovedto);
    }

    @MessageMapping("/shoot")
    public void handleGameShoot(PlayerShootDTO playerShootDTO){
        gameService.processShoot(playerShootDTO);
    }

    
}
