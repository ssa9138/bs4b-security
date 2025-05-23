package at.fhv.epa.demoproject.domain.model;

import at.fhv.epa.demoproject.domain.valueobject.BoundingBox;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "maps")
public class MapData {

    @Id
    private String id;

    private String name;
    private int width;
    private int height;
    private List<BoundingBox> obstacles;
    private String backgroundImage;

    public MapData() {
    }

    public MapData(String id, String name, int width, int height, List<BoundingBox> obstacles, String backgroundImage) {
        this.id = id;
        this.name = name;
        this.width = width;
        this.height = height;
        this.obstacles = obstacles;
        this.backgroundImage = backgroundImage;
    }

    public List<BoundingBox> getObstacles() {
        return obstacles;
    }

    public void setObstacles(List<BoundingBox> obstacles) {
        this.obstacles = obstacles;
    }

    public String getBackgroundImage() {
        return backgroundImage;
    }

    public void setBackgroundImage(String backgroundImage) {
        this.backgroundImage = backgroundImage;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }
}
