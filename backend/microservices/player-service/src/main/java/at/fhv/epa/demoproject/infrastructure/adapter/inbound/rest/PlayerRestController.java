package at.fhv.epa.demoproject.infrastructure.adapter.inbound.rest;

import at.fhv.epa.demoproject.application.port.input.PlayerService;
import at.fhv.epa.demoproject.domain.model.Player;
import at.fhv.epa.demoproject.infrastructure.adapter.inbound.rest.dto.CreatePlayerDTO;
import at.fhv.epa.demoproject.infrastructure.adapter.inbound.rest.dto.LoginDTO;
import at.fhv.epa.demoproject.infrastructure.adapter.inbound.rest.dto.PlayerDTO;
import at.fhv.epa.demoproject.infrastructure.adapter.inbound.rest.mapper.PlayerMapper;
import at.fhv.epa.demoproject.infrastructure.persistence.PlayerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/players")
public class PlayerRestController {

    private final PlayerService playerService;
    private final PlayerRepository playerRepository;
    private final PlayerMapper playerMapper;
    @PostMapping("/create")
    public ResponseEntity<PlayerDTO> createPlayer(@RequestBody CreatePlayerDTO createPlayerDTO) {
        return ResponseEntity.ok(playerService.createNewPlayer(createPlayerDTO));
    }

    @GetMapping("/all")
    public ResponseEntity<List<PlayerDTO>> getAllPlayers() {
        return ResponseEntity.ok(playerService.getAllPlayers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PlayerDTO> getPlayerById(@PathVariable UUID id) {
        return ResponseEntity.ok(playerService.getPlayerById(id));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<PlayerDTO> updatePlayer(@PathVariable UUID id, @RequestBody CreatePlayerDTO createPlayerDTO) {
        return ResponseEntity.ok(playerService.updatePlayer(id, createPlayerDTO));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deletePlayer(@PathVariable UUID id) {
        playerService.deletePlayerById(id);
        return ResponseEntity.noContent().build();
    }

    @CrossOrigin(origins = "*")
    @PostMapping("/login")
    public ResponseEntity<PlayerDTO> login(@RequestBody LoginDTO loginDTO) {
        return playerService.login(loginDTO.getUsername())
                .map(playerMapper::toDto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.internalServerError().build());
    }
}