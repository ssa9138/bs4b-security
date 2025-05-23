package at.fhv.epa.gameservice.domain.model;

import at.fhv.epa.gameservice.domain.valueobject.BrawlerId;
import at.fhv.epa.gameservice.domain.valueobject.GameId;
import at.fhv.epa.gameservice.domain.valueobject.ProjectileData;

import java.util.ArrayList;
import java.util.List;

public class Game {
    private GameId id;
    private List<Brawler> brawlers;
    private Map gameMap;

    private final List<Projectile> projectiles = new ArrayList<>();


    public Game(GameId id, List<Brawler> brawlers, Map gameMap) {
        this.id = id;
        this.brawlers = brawlers;
        this.gameMap = gameMap;
    }

    public Brawler getBrawlerById(BrawlerId id) {
        return this.brawlers.stream().filter(brawler -> brawler.getId().equals(id)).findFirst().orElse(null);
    }

    public void addProjectile(Projectile p){
        projectiles.add(p);
    }

    public List<Projectile> getProjectiles() {
        return projectiles;
    }

    public GameId getId() {
        return id;
    }

    public List<Brawler> getBrawlers() {
        return brawlers;
    }

    public Map getGameMap() {
        return gameMap;
    }
}
