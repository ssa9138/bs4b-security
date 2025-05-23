package at.fhv.epa.demoproject.infrastructure.adapter.inbound.rest.mapper;

import at.fhv.epa.demoproject.domain.model.Player;
import at.fhv.epa.demoproject.domain.valueobject.PlayerId;
import at.fhv.epa.demoproject.infrastructure.adapter.inbound.rest.dto.CreatePlayerDTO;
import at.fhv.epa.demoproject.infrastructure.adapter.inbound.rest.dto.PlayerDTO;
import at.fhv.epa.demoproject.infrastructure.persistence.PlayerEntity;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class PlayerMapper {

    public Player toDomain(PlayerEntity entity) {
        return new Player(new PlayerId(entity.getId()), entity.getUsername());
    }

    public PlayerEntity toEntity(Player player) {
        return new PlayerEntity(player.getId().getValue(), player.getUsername());
    }

    public PlayerDTO toDto(Player player) {
        return new PlayerDTO(player.getId().getValue(), player.getUsername());
    }

    public Player fromCreatePlayerDTO(CreatePlayerDTO dto) {
        return new Player(new PlayerId(UUID.randomUUID()), dto.getUsername());
    }
}