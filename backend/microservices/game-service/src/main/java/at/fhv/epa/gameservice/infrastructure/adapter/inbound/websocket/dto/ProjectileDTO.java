package at.fhv.epa.gameservice.infrastructure.adapter.inbound.websocket.dto;

import at.fhv.epa.gameservice.domain.valueobject.BoundingBox;

import java.util.UUID;

public record ProjectileDTO(
        UUID id,
        int shooterId,
        BoundingBox box,
        float dx,
        float dy
) {
}
