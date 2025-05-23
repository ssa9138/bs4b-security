package at.fhv.epa.demoproject.domain.model;

import at.fhv.epa.demoproject.domain.valueobject.PlayerId;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class Player {
    private final PlayerId id;
    private String username;

    public void update(String name) {
        this.username = name;

    }
}