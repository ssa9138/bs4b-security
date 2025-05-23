package at.fhv.epa.gameservice.domain.model;

import at.fhv.epa.gameservice.domain.event.BrawlerShotEvent;
import at.fhv.epa.gameservice.domain.shared.DomainClock;
import at.fhv.epa.gameservice.domain.valueobject.BoundingBox;
import at.fhv.epa.gameservice.domain.valueobject.BrawlerId;
import at.fhv.epa.gameservice.domain.valueobject.Movement;
import at.fhv.epa.gameservice.domain.valueobject.ProjectileData;

import java.time.Instant;

public class Brawler {
    private BrawlerId id;
    private BoundingBox boundingBox;
    private final Weapon weapon;
    private final float speed;
    private final DomainClock clock;

    public Brawler(BrawlerId id, BoundingBox boundingBox, float speed, Weapon weapon, DomainClock clock) {
        this.id = id;
        this.boundingBox = boundingBox;
        this.speed = speed;
        this.weapon = weapon;
        this.clock = clock;
    }
    public BrawlerShotEvent shoot(Movement direction){
        Instant now = clock.now();
        if(!weapon.canFireAt(now)){
            throw new IllegalStateException("Weapon cannot fire yet.");
        }
        weapon.trigger(now);

        float dx = direction.dx();
        float dy = direction.dy();
        float len = (float) Math.hypot(dx,dy);
        if(len == 0){
            throw new IllegalStateException("Direction must not be zero");
        }

        Movement movement = new Movement(dx / len * weapon.getProjectileSpeed(), dy / len * weapon.getProjectileSpeed());
        float pSize = weapon.getProjectileSize();
        float startX = boundingBox.getX() + boundingBox.getWidth() /2f - pSize/2f;
        float startY = boundingBox.getY() + boundingBox.getHeight() /2f - pSize/2f;

        BoundingBox projBox = new BoundingBox(startX, startY,pSize,pSize);
        ProjectileData data = new ProjectileData(id,projBox,movement,now);
        return new BrawlerShotEvent(data);
    }

    public void move(float dx, float dy, Map map) {
        float length = (float) Math.sqrt(dx * dx + dy * dy);
        if (length > 0) {
            dx /= length;
            dy /= length;
        }

        dx *= this.speed;
        dy *= this.speed;

        int steps = 20;
        float stepX = dx / steps;
        float stepY = dy / steps;

        BoundingBox currentBox = this.boundingBox;

        for (int i = 0; i < steps; i++) {
            BoundingBox testBox = currentBox.translate(stepX, stepY);
            float clampedX = Math.max(testBox.getX(), 0);
            clampedX = Math.min(clampedX, map.getWidth() - testBox.getWidth());
            float clampedY = Math.max(testBox.getY(), 0);
            clampedY = Math.min(clampedY, map.getHeight() - testBox.getHeight());
            testBox = new BoundingBox(clampedX, clampedY, testBox.getWidth(), testBox.getHeight());

            if (!map.collidesWithObstacle(testBox)) {
                currentBox = testBox;
            } else {
                boolean moved = false;
                BoundingBox testBoxX = currentBox.translate(stepX, 0);
                float clampedXOnly = Math.max(testBoxX.getX(), 0);
                clampedXOnly = Math.min(clampedXOnly, map.getWidth() - testBoxX.getWidth());
                testBoxX = new BoundingBox(clampedXOnly, currentBox.getY(), testBoxX.getWidth(), testBoxX.getHeight());

                if (!map.collidesWithObstacle(testBoxX)) {
                    currentBox = testBoxX;
                    moved = true;
                }

                BoundingBox testBoxY = currentBox.translate(0, stepY);
                float clampedYOnly = Math.max(testBoxY.getY(), 0);
                clampedYOnly = Math.min(clampedYOnly, map.getHeight() - testBoxY.getHeight());
                testBoxY = new BoundingBox(currentBox.getX(), clampedYOnly, testBoxY.getWidth(), testBoxY.getHeight());

                if (!map.collidesWithObstacle(testBoxY)) {
                    currentBox = testBoxY;
                    moved = true;
                }
                if (!moved) {
                    break;
                }
            }
        }
        this.boundingBox = currentBox;
    }


    public BrawlerId getId() {
        return id;
    }

    public double getSpeed() {
        return speed;
    }

    public BoundingBox getBoundingBox() {
        return boundingBox;
    }

    @Override
    public String toString() {
        return "Brawler{" +
                "id=" + id +
                ", boundingBox=" + boundingBox +
                ", speed=" + speed +
                '}';
    }
}
