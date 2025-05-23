package at.fhv.epa.gameservice.infrastructure.adapter.inbound.websocket.dto;

import java.util.UUID;

public record PlayerShootDTO(
        UUID gameId,
        int brawlerId,
        float dx,
        float dy

) {


}
