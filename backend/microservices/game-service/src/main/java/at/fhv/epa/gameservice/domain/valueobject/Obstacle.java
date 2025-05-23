package at.fhv.epa.gameservice.domain.valueobject;

import java.util.Objects;

public class Obstacle {
    private int id;
    private final BoundingBox boundingBox;


    public Obstacle(int id,BoundingBox boundingBox) {
        this.id = id;
        this.boundingBox = boundingBox;
    }

    public BoundingBox getBoundingBox() {
        return boundingBox;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Obstacle obstacle = (Obstacle) o;
        return Objects.equals(boundingBox, obstacle.boundingBox);
    }

    @Override
    public int hashCode() {
        return Objects.hash(boundingBox);
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Obstacle{" +
                "boundingBox=" + boundingBox +
                '}';
    }
}
