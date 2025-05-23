package at.fhv.epa.gameservice.domain.model;

import at.fhv.epa.gameservice.domain.valueobject.BoundingBox;
import at.fhv.epa.gameservice.domain.valueobject.BrawlerId;
import at.fhv.epa.gameservice.domain.valueobject.Movement;
import at.fhv.epa.gameservice.domain.valueobject.ProjectileData;

import java.util.UUID;

public class Projectile {
    private UUID id;
    private final BrawlerId shooter;
    private BoundingBox boundingBox;
    private Movement velocity;
    private float timeToLive;

    public Projectile(ProjectileData projectileData, float ttlSeconds) {
        this.id = UUID.randomUUID();
        this.shooter = projectileData.shooter();
        this.boundingBox = projectileData.boundingBox();
        this.velocity = projectileData.movement();
        this.timeToLive = ttlSeconds;
    }

    public void advance(float dt) {
        this.boundingBox = boundingBox.translate(velocity.dx() * dt, velocity.dy() * dt);
        timeToLive -= dt;
    }

    public UUID getId() {
        return id;
    }

    public BrawlerId getShooter() {
        return shooter;
    }

    public BoundingBox getBoundingBox() {
        return boundingBox;
    }

    public Movement getVelocity() {
        return velocity;
    }

    public boolean lifetimeExceeded(){
        return timeToLive <= 0f;
    }
}
