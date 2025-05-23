package at.fhv.epa.demoproject.infrastructure.adapter.inbound.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;

@Data
@AllArgsConstructor
public class PlayerDTO {
    private UUID id;
    private String username;
}