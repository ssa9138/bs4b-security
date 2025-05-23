package at.fhv.epa.gameservice.application.store;

import at.fhv.epa.gameservice.domain.model.Game;
import at.fhv.epa.gameservice.domain.valueobject.GameId;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.BiConsumer;

@Component
public class ActiveGamesStore {
    private final Map<GameId, Game> activeGames = new ConcurrentHashMap<>();

    public void addGame(Game game) {
        activeGames.put(game.getId(), game);
    }
    public Game getGame(GameId gameId){
        return activeGames.get(gameId);
    }
    public void removeGame(GameId gameId){
        activeGames.remove(gameId);
    }

    public Collection<Game> getAllGames()     { return activeGames.values(); }

    public void forEach(BiConsumer<GameId, Game> action) {
        activeGames.forEach(action);
    }
}
