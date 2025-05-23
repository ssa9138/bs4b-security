package at.fhv.epa.gameservice.domain.valueobject;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.UUID;

@AllArgsConstructor
@Getter
public class MapId {
    private UUID value;
}
