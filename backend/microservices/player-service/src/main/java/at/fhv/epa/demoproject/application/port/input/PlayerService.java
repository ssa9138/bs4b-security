package at.fhv.epa.demoproject.application.port.input;

import at.fhv.epa.demoproject.domain.model.Player;
import at.fhv.epa.demoproject.infrastructure.adapter.inbound.rest.dto.CreatePlayerDTO;
import at.fhv.epa.demoproject.infrastructure.adapter.inbound.rest.dto.PlayerDTO;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface PlayerService {
    PlayerDTO createNewPlayer(CreatePlayerDTO createPlayerDTO);
    List<PlayerDTO> getAllPlayers();
    PlayerDTO getPlayerById(UUID id);
    void deletePlayerById(UUID id);
    PlayerDTO updatePlayer(UUID id, CreatePlayerDTO createPlayerDTO);
    Optional<Player> login(String username);
}
