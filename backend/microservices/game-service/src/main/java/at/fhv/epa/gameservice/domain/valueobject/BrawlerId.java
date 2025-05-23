package at.fhv.epa.gameservice.domain.valueobject;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Objects;
import java.util.UUID;
@AllArgsConstructor
@Getter
public class BrawlerId {
    private Integer value;

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        BrawlerId brawlerId = (BrawlerId) o;
        return Objects.equals(value, brawlerId.value);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(value);
    }
}
