package at.fhv.epa.gameservice.application.port.input;

import at.fhv.epa.gameservice.infrastructure.adapter.inbound.rest.dto.CreateGameDTO;
import at.fhv.epa.gameservice.infrastructure.adapter.inbound.rest.dto.GameDTO;
import at.fhv.epa.gameservice.infrastructure.adapter.inbound.websocket.dto.GameStateDTO;
import at.fhv.epa.gameservice.infrastructure.adapter.inbound.websocket.dto.PlayerMoveDTO;
import at.fhv.epa.gameservice.domain.valueobject.GameId;
import at.fhv.epa.gameservice.infrastructure.adapter.inbound.websocket.dto.PlayerShootDTO;

public interface GameService {
    GameDTO createNewGame(CreateGameDTO createGameDTO);
    GameStateDTO getGameStateById(GameId gameId);
    void processMove(PlayerMoveDTO playermovedto);
    void processShoot(PlayerShootDTO playerShootDTO);
}
