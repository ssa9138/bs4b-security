package at.fhv.epa.gameservice.infrastructure.adapter.inbound.websocket.mapper;

import at.fhv.epa.gameservice.domain.model.Map;
import at.fhv.epa.gameservice.infrastructure.adapter.inbound.websocket.dto.MapDTO;
import at.fhv.epa.gameservice.infrastructure.adapter.inbound.websocket.dto.ObstacleDTO;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MapMapper {
    public MapDTO toDto(Map map) {
        List<ObstacleDTO> obstacleDTOS = map.getObstacles().stream().map(obstacle -> new ObstacleDTO(obstacle.getId(),obstacle.getBoundingBox())).toList();
        return new MapDTO(map.getHeight(), map.getWidth(), obstacleDTOS);
    }
}
