package at.fhv.epa.gameservice.infrastructure.adapter.inbound.websocket.dto;

import at.fhv.epa.gameservice.domain.valueobject.BoundingBox;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class BrawlerDTO {
    private int id;
    private BoundingBox box;
}
