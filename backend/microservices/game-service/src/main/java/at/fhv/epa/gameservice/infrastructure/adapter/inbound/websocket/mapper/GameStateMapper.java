package at.fhv.epa.gameservice.infrastructure.adapter.inbound.websocket.mapper;

import at.fhv.epa.gameservice.domain.model.Game;
import at.fhv.epa.gameservice.infrastructure.adapter.inbound.websocket.dto.BrawlerDTO;
import at.fhv.epa.gameservice.infrastructure.adapter.inbound.websocket.dto.GameStateDTO;
import at.fhv.epa.gameservice.infrastructure.adapter.inbound.websocket.dto.ProjectileDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class GameStateMapper {
    private final MapMapper mapMapper;
    private final BrawlerMapper brawlerMapper;
    private final ProjectileMapper projectileMapper;
    public GameStateDTO toDto(Game game){
        List<BrawlerDTO> brawlerDTOS = game.getBrawlers().stream().map(brawlerMapper::toDto).toList();
        List<ProjectileDTO> projectileDTOS = game.getProjectiles().stream().map(projectileMapper::toDto).toList();
        return new GameStateDTO(
                game.getId().getValue(),
                mapMapper.toDto(game.getGameMap()),
                brawlerDTOS,
                projectileDTOS
        );
    }
}
