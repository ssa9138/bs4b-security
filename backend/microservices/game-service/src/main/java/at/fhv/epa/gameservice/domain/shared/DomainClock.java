package at.fhv.epa.gameservice.domain.shared;

import java.time.Instant;

public interface DomainClock {
    Instant now();
}
