package at.fhv.epa.gameservice.infrastructure.adapter.inbound.websocket.dto;

import at.fhv.epa.gameservice.domain.valueobject.BoundingBox;

public record ObstacleDTO(int id,BoundingBox box) {
}
