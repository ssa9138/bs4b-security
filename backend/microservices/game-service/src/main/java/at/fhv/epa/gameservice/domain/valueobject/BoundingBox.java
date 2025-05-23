package at.fhv.epa.gameservice.domain.valueobject;

import java.util.Objects;

public final class BoundingBox {
    private final float x;
    private final float y;
    private final float width;
    private final float height;

    public BoundingBox(float x, float y, float width, float height) {
        if(height <= 0 || width <= 0) {
            throw new IllegalArgumentException("Width and height must be greater than 0");
        }
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public BoundingBox translate(float dx, float dy){
        return new BoundingBox(this.x + dx, this.y + dy, this.width, this.height);
    }

    public boolean intersects(BoundingBox other){
        return (this.x < other.x + other.width) &&
                (this.x + this.width > other.x) &&
                (this.y < other.y + other.height) &&
                (this.y + this.height > other.y);
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public float getWidth() {
        return width;
    }

    public float getHeight() {
        return height;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BoundingBox that = (BoundingBox) o;
        return Float.compare(that.x, x) == 0 && Float.compare(that.y, y) == 0 && Float.compare(that.width, width) == 0 && Float.compare(that.height, height) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y, width, height);
    }

    @Override
    public String toString() {
        return "BoundingBox{" +
                "x=" + x +
                ", y=" + y +
                ", width=" + width +
                ", height=" + height +
                '}';
    }
}
