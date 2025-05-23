package at.fhv.epa.gameservice.infrastructure.adapter.inbound.websocket.mapper;

import at.fhv.epa.gameservice.domain.model.Brawler;
import at.fhv.epa.gameservice.infrastructure.adapter.inbound.websocket.dto.BrawlerDTO;
import at.fhv.epa.gameservice.infrastructure.adapter.inbound.websocket.dto.PositionDTO;
import org.springframework.stereotype.Component;

@Component
public class BrawlerMapper {
    public BrawlerDTO toDto(Brawler brawler) {
        return new BrawlerDTO(brawler.getId().getValue(),brawler.getBoundingBox());
    }
}
