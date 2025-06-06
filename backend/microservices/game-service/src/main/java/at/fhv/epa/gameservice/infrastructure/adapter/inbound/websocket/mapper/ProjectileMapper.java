package at.fhv.epa.gameservice.infrastructure.adapter.inbound.websocket.mapper;

import at.fhv.epa.gameservice.domain.model.Projectile;
import at.fhv.epa.gameservice.domain.valueobject.BoundingBox;
import at.fhv.epa.gameservice.domain.valueobject.Movement;
import at.fhv.epa.gameservice.infrastructure.adapter.inbound.websocket.dto.ProjectileDTO;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class ProjectileMapper {
    public ProjectileDTO toDto(Projectile projectile){
        Movement movement = projectile.getVelocity();
        return new ProjectileDTO(
                projectile.getId(),
                projectile.getShooter().getValue(),
                projectile.getBoundingBox(),
                movement.dx(),
                movement.dy()
        );
    }
}
