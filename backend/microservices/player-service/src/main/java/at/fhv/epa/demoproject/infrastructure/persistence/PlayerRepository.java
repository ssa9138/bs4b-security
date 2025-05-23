package at.fhv.epa.demoproject.infrastructure.persistence;

import at.fhv.epa.demoproject.domain.model.Player;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface PlayerRepository extends JpaRepository<PlayerEntity, UUID> {
    Optional<PlayerEntity> findByUsername(String username);

}
