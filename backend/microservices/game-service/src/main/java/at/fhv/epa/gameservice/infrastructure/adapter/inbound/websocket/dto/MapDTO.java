package at.fhv.epa.gameservice.infrastructure.adapter.inbound.websocket.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class MapDTO {
    private float height;
    private float width;
    private List<ObstacleDTO> obstacles;
}
