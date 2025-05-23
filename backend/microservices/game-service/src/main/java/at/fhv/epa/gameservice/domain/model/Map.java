package at.fhv.epa.gameservice.domain.model;

import at.fhv.epa.gameservice.domain.valueobject.BoundingBox;
import at.fhv.epa.gameservice.domain.valueobject.Obstacle;

import java.util.List;

public class Map {
    private final float height;
    private final float width;
    private List<Obstacle> obstacles;

    public Map(float height, float width, List<Obstacle> obstacles) {
        this.height = height;
        this.width = width;
        this.obstacles = obstacles;
    }

    public boolean collidesWithObstacle(BoundingBox boundingBox) {
        if (boundingBox.getX() < 0 || boundingBox.getY() < 0 ||
                (boundingBox.getX() + boundingBox.getWidth()) > width ||
                (boundingBox.getY() + boundingBox.getHeight()) > height){
            return true;
        }

        for (Obstacle o : obstacles){
            if(boundingBox.intersects(o.getBoundingBox())){
                return true;
            }
        }
        return false;
    }
    public float getHeight() {
        return height;
    }

    public float getWidth() {
        return width;
    }

    public List<Obstacle> getObstacles() {
        return obstacles;
    }
}
