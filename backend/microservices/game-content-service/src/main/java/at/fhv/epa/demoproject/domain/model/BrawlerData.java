package at.fhv.epa.demoproject.domain.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "brawlers")
public class BrawlerData {

    @Id
    private String id;

    private String name;
    private String weapon;
    private int range;
    private int damage;
    private int speed;
    private int healthPoints;

    public BrawlerData() {
    }

    public BrawlerData(String id, String name, String weapon, int range, int damage, int speed, int healthPoints) {
        this.id = id;
        this.name = name;
        this.weapon = weapon;
        this.range = range;
        this.damage = damage;
        this.speed = speed;
        this.healthPoints = healthPoints;
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

    public String getWeapon() {
        return weapon;
    }

    public void setWeapon(String weapon) {
        this.weapon = weapon;
    }

    public int getRange() {
        return range;
    }

    public void setRange(int range) {
        this.range = range;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getHealthPoints() {
        return healthPoints;
    }

    public void setHealthPoints(int healthPoints) {
        this.healthPoints = healthPoints;
    }
}
