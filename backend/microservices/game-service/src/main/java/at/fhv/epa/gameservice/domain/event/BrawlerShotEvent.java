package at.fhv.epa.gameservice.domain.event;

import at.fhv.epa.gameservice.domain.valueobject.ProjectileData;

public final class BrawlerShotEvent {
    private final ProjectileData projectileData;
    public BrawlerShotEvent(ProjectileData projectileData) {
        this.projectileData = projectileData;
    }

    public ProjectileData getProjectileData() {
        return projectileData;
    }
}
