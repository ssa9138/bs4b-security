package at.fhv.epa.gameservice.infrastructure.adapter.inbound.rest.dto;

import lombok.Data;

import java.util.List;

@Data
public class CreateGameDTO {
    private List<Integer> brawlerIds;
}
