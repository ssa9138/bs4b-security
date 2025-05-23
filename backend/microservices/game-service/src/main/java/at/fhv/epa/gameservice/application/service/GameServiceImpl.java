package at.fhv.epa.gameservice.application.service;

import at.fhv.epa.gameservice.application.exception.RessourceNotFoundException;
import at.fhv.epa.gameservice.application.port.input.GameService;
import at.fhv.epa.gameservice.application.store.ActiveGamesStore;
import at.fhv.epa.gameservice.domain.model.Brawler;
import at.fhv.epa.gameservice.domain.model.Game;
import at.fhv.epa.gameservice.domain.model.Map;
import at.fhv.epa.gameservice.domain.model.Weapon;
import at.fhv.epa.gameservice.domain.service.GameEngine;
import at.fhv.epa.gameservice.domain.shared.SystemDomainClock;
import at.fhv.epa.gameservice.domain.valueobject.*;
import at.fhv.epa.gameservice.infrastructure.adapter.inbound.rest.dto.CreateGameDTO;
import at.fhv.epa.gameservice.infrastructure.adapter.inbound.rest.dto.GameDTO;
import at.fhv.epa.gameservice.infrastructure.adapter.inbound.websocket.dto.GameStateDTO;
import at.fhv.epa.gameservice.infrastructure.adapter.inbound.websocket.dto.PlayerMoveDTO;
import at.fhv.epa.gameservice.infrastructure.adapter.inbound.websocket.dto.PlayerShootDTO;
import at.fhv.epa.gameservice.infrastructure.adapter.inbound.websocket.mapper.GameStateMapper;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class GameServiceImpl implements GameService {
    private final ActiveGamesStore activeGamesStore;
    private final SimpMessagingTemplate messagingTemplate;
    private final GameEngine gameEngine;
    private final GameStateMapper gameStateMapper;

    public GameServiceImpl(ActiveGamesStore activeGamesStore, SimpMessagingTemplate messagingTemplate, GameEngine gameEngine, GameStateMapper gameStateMapper) {
        this.activeGamesStore = activeGamesStore;
        this.messagingTemplate = messagingTemplate;
        this.gameEngine = gameEngine;
        this.gameStateMapper = gameStateMapper;
    }

    @Override
    public GameDTO createNewGame(CreateGameDTO createGameDTO) {
        GameId newGameId = new GameId(UUID.randomUUID());
        Game newGame = new Game(
              newGameId,
              initBrawler(createGameDTO.getBrawlerIds()),
              new Map(2000, 2000, initObstacles())
        );
        activeGamesStore.addGame(newGame);
        return new GameDTO(newGameId.getValue());
    }

    private List<Brawler> initBrawler(List<Integer> brawlerIds) {
        List<Brawler> brawlers = new ArrayList<>();
        for (Integer brawlerId : brawlerIds) {
            Brawler brawler = new Brawler(
                    new BrawlerId(brawlerId),
                    new BoundingBox(100, 100, 32, 64),
                    10f,
                    new Weapon(1000, Duration.ZERO,800f,8f),
                    new SystemDomainClock()
            );
            brawlers.add(brawler);
        }
        return brawlers;
    }

    @Override
    public GameStateDTO getGameStateById(GameId gameId) {
        Game game = activeGamesStore.getGame(gameId);
        if(game == null) {
            throw new RessourceNotFoundException("Game not found");
        }
        return gameStateMapper.toDto(game);
    }

    @Override
    public void processMove(PlayerMoveDTO playerMoveDTO){
        GameId gameId = new GameId(playerMoveDTO.gameId());
        Game game = activeGamesStore.getGame(gameId);
        if(game == null) {
            throw new RessourceNotFoundException("Game not found");
        }
        Brawler brawler = game.getBrawlerById(new BrawlerId(playerMoveDTO.brawlerId()));
        if(brawler == null) {
            throw new RessourceNotFoundException("Brawler not found");
        }

        gameEngine.moveBrawler(game,brawler, playerMoveDTO.movement());

        GameStateDTO gameStateDTO = gameStateMapper.toDto(game);
        messagingTemplate.convertAndSend("/topic/gameState/"+gameId.getValue(), gameStateDTO);
    }

    @Override
    public void processShoot(PlayerShootDTO playerShootDTO) {
        GameId gameId = new GameId(playerShootDTO.gameId());
        Game game = activeGamesStore.getGame(gameId);

        Brawler brawler = game.getBrawlerById(new BrawlerId(playerShootDTO.brawlerId()));
        Movement movement = new Movement(playerShootDTO.dx(), playerShootDTO.dy());
        gameEngine.shootBrawler(game,brawler,movement);

        GameStateDTO gameStateDTO = gameStateMapper.toDto(game);
        messagingTemplate.convertAndSend("/topic/gameState/"+gameId.getValue(),gameStateDTO);
    }

    private List<Obstacle> initObstacles() {
        List<Obstacle> obstacles = new ArrayList<>();
        obstacles.add(new Obstacle(1,new BoundingBox(250, 100, 64, 64)));
        obstacles.add(new Obstacle(2,new BoundingBox(500, 250, 64, 64)));
        obstacles.add(new Obstacle(3,new BoundingBox(750, 500, 128, 64)));
        obstacles.add(new Obstacle(4,new BoundingBox(1000, 1000, 32, 64)));
        obstacles.add(new Obstacle(5,new BoundingBox(750, 1500, 32, 128)));
        obstacles.add(new Obstacle(6,new BoundingBox(1500, 1500, 32, 64)));
        obstacles.add(new Obstacle(7,new BoundingBox(1500, 200, 64, 64)));
        obstacles.add(new Obstacle(8,new BoundingBox(100, 1700, 64, 64)));
        obstacles.add(new Obstacle(9,new BoundingBox(400, 400, 128, 128)));
        obstacles.add(new Obstacle(10,new BoundingBox(1800, 1800, 128, 128)));
        obstacles.add(new Obstacle(11,new BoundingBox(200, 1200, 128, 128)));

        return obstacles;
    }



}
