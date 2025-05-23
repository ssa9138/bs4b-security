package at.fhv.epa.gameservice.domain.valueobject;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Objects;
import java.util.UUID;

@AllArgsConstructor
@Getter
public class GameId {
    private UUID value;

    public static GameId fromString(String idStr) {
        try {
            UUID uuid = UUID.fromString(idStr);
            return new GameId(uuid);
        } catch (IllegalArgumentException e) {
            throw new RuntimeException("Invalid GameId: " + idStr, e);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GameId gameId = (GameId) o;
        return Objects.equals(value, gameId.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
