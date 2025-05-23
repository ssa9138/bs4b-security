package at.fhv.epa.gameservice.infrastructure.adapter.inbound.websocket;

import at.fhv.epa.gameservice.infrastructure.adapter.inbound.websocket.dto.GameStateDTO;
import at.fhv.epa.gameservice.application.port.input.GameService;
import at.fhv.epa.gameservice.domain.valueobject.GameId;
import at.fhv.epa.gameservice.infrastructure.adapter.inbound.websocket.mapper.GameStateMapper;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Controller;
import org.springframework.web.socket.messaging.SessionSubscribeEvent;

@Controller
public class GameStateSubscriptionListener {
    private final SimpMessagingTemplate messagingTemplate;
    private final GameService gameService;

    private static final String GAME_STATE_TOPIC_PREFIX = "/topic/gameState/";

    public GameStateSubscriptionListener(SimpMessagingTemplate messagingTemplate, GameService gameService) {
        this.messagingTemplate = messagingTemplate;
        this.gameService = gameService;
    }
    @EventListener
    public void handleSessionSubscribeEvent(SessionSubscribeEvent event) {
        StompHeaderAccessor sha = StompHeaderAccessor.wrap(event.getMessage());
        String destination = sha.getDestination();
        if (destination != null && destination.startsWith(GAME_STATE_TOPIC_PREFIX)) {
            String gameId = destination.substring(GAME_STATE_TOPIC_PREFIX.length());

            GameStateDTO dto = gameService.getGameStateById(GameId.fromString(gameId));
            System.out.println("Sending game state: " + destination);
            messagingTemplate.convertAndSend(destination, dto);
        }
    }

}
