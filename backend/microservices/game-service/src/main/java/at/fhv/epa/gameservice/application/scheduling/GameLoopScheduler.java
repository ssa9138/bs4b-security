package at.fhv.epa.gameservice.application.scheduling;

import at.fhv.epa.gameservice.application.store.ActiveGamesStore;
import at.fhv.epa.gameservice.domain.model.Game;
import at.fhv.epa.gameservice.domain.service.GameEngine;
import at.fhv.epa.gameservice.infrastructure.adapter.inbound.websocket.mapper.GameStateMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class GameLoopScheduler {
    private static final float DT = 0.05f;

    private final ActiveGamesStore activeGamesStore;
    private final GameEngine gameEngine;
    private final SimpMessagingTemplate messagingTemplate;
    private final GameStateMapper mapper;

    @Scheduled(fixedRate = 50)
    public void tick(){

        activeGamesStore.forEach((gameId, game) -> {
            gameEngine.updateGame(game, DT);
            messagingTemplate.convertAndSend("/topic/gameState/"+gameId.getValue(), mapper.toDto(game));
        });
    }
}
