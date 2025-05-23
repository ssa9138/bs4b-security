package at.fhv.epa.gameservice.domain.valueobject;

import java.time.Instant;

public record ProjectileData(
        BrawlerId shooter,
        BoundingBox boundingBox,
        Movement movement,
        Instant timestamp
) {
}
