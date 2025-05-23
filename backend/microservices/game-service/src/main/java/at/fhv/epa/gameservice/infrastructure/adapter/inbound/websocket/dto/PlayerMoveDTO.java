package at.fhv.epa.gameservice.infrastructure.adapter.inbound.websocket.dto;

import at.fhv.epa.gameservice.domain.valueobject.Movement;

import java.util.UUID;

public record PlayerMoveDTO(UUID gameId, int brawlerId, Movement movement) {

}
