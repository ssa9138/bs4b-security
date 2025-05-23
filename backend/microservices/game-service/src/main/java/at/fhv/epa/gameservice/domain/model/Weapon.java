package at.fhv.epa.gameservice.domain.model;

import java.time.Duration;
import java.time.Instant;

public class Weapon {
    private final int maxAmmo;
    private final Duration cooldown;
    private final float projectileSpeed;
    private final float projectileSize;

    private int ammo;
    private Instant nextAvailable;

    public Weapon(int maxAmmo, Duration cooldown, float projectileSpeed, float projectileSize) {
        this.maxAmmo = maxAmmo;
        this.cooldown = cooldown;
        this.projectileSpeed = projectileSpeed;
        this.projectileSize = projectileSize;

        this.ammo = maxAmmo;
        this.nextAvailable = Instant.EPOCH;
    }

    public boolean canFireAt(Instant timeStamp) {
        return ammo > 0 && !timeStamp.isBefore(nextAvailable);
    }

    public void trigger(Instant timeStamp){
        if(!canFireAt(timeStamp)) {
            throw new IllegalStateException("Weapon ist reloading or out of ammo");
        }
        ammo--;
        nextAvailable = timeStamp.plus(cooldown);

    }

    public float getProjectileSpeed() {
        return projectileSpeed;
    }

    public float getProjectileSize() {
        return projectileSize;
    }

    public int getRemainingAmmo() {
        return ammo;
    }
}
