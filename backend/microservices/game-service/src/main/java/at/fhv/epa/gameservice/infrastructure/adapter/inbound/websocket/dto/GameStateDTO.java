package at.fhv.epa.gameservice.infrastructure.adapter.inbound.websocket.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
public class GameStateDTO {
    private UUID gameId;
    private MapDTO map;
    private List<BrawlerDTO> brawlerDTOS;
    private List<ProjectileDTO> projectileDTOS;

}
