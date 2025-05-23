package at.fhv.epa.gameservice.domain.service;

import at.fhv.epa.gameservice.domain.event.BrawlerShotEvent;
import at.fhv.epa.gameservice.domain.model.Brawler;
import at.fhv.epa.gameservice.domain.model.Game;
import at.fhv.epa.gameservice.domain.model.Projectile;
import at.fhv.epa.gameservice.domain.valueobject.Movement;
import at.fhv.epa.gameservice.domain.valueobject.ProjectileData;
import org.springframework.stereotype.Service;

import java.util.Iterator;

@Service
public class GameEngine {
    private static final float PROJECTILE_TTL = 3.0f;

    public void updateGame(Game game, float dt){
        updateProjectiles(game,dt);
    }

    public void moveBrawler(Game game,Brawler brawler, Movement movement) {
        brawler.move(movement.dx(), movement.dy(), game.getGameMap());
    }

    public void shootBrawler(Game game, Brawler brawler, Movement dir){
        BrawlerShotEvent evt = brawler.shoot(dir);
        ProjectileData pd = evt.getProjectileData();

        Projectile projectile = new Projectile(pd, PROJECTILE_TTL);
        game.addProjectile(projectile);

    }

    private void updateProjectiles(Game game, float dt){
        Iterator<Projectile> it = game.getProjectiles().iterator();
        while(it.hasNext()){
            Projectile projectile = it.next();
            projectile.advance(dt);
            if(projectile.lifetimeExceeded()) {
                it.remove();
            }
        }
    }
}
