package at.fhv.epa.demoproject.domain.valueobject;

import java.util.Objects;
import java.util.UUID;

public class PlayerId {
    private final UUID value;

    public PlayerId(UUID value) {
        this.value = value;
    }

    public UUID getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PlayerId playerId = (PlayerId) o;
        return Objects.equals(value, playerId.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}