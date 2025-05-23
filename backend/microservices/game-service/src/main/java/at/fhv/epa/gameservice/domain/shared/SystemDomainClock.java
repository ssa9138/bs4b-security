package at.fhv.epa.gameservice.domain.shared;

import java.time.Instant;

public class SystemDomainClock implements DomainClock{
    @Override
    public Instant now() {
        return Instant.now();
    }
}
