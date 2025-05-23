package at.fhv.epa.demoproject.application.service;

import at.fhv.epa.demoproject.application.exception.PlayerNotFoundException;
import at.fhv.epa.demoproject.application.port.input.PlayerService;
import at.fhv.epa.demoproject.domain.model.Player;
import at.fhv.epa.demoproject.infrastructure.adapter.inbound.rest.dto.CreatePlayerDTO;
import at.fhv.epa.demoproject.infrastructure.adapter.inbound.rest.dto.PlayerDTO;
import at.fhv.epa.demoproject.infrastructure.adapter.inbound.rest.mapper.PlayerMapper;
import at.fhv.epa.demoproject.infrastructure.persistence.PlayerEntity;
import at.fhv.epa.demoproject.infrastructure.persistence.PlayerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PlayerServiceImpl implements PlayerService {

    private final PlayerRepository playerRepository;
    private final PlayerMapper playerMapper;

    @Override
    public PlayerDTO createNewPlayer(CreatePlayerDTO createPlayerDTO) {
        if (createPlayerDTO.getUsername() == null || createPlayerDTO.getUsername().isBlank()) {
            throw new IllegalArgumentException("Player name must not be empty.");
        }

        Player player = playerMapper.fromCreatePlayerDTO(createPlayerDTO);
        PlayerEntity savedEntity = playerRepository.save(playerMapper.toEntity(player));
        return playerMapper.toDto(playerMapper.toDomain(savedEntity));
    }

    @Override
    public List<PlayerDTO> getAllPlayers() {
        return playerRepository.findAll().stream()
                .map(playerMapper::toDomain)
                .map(playerMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public PlayerDTO getPlayerById(UUID id) {
        PlayerEntity entity = playerRepository.findById(id)
                .orElseThrow(() -> new PlayerNotFoundException(id));
        return playerMapper.toDto(playerMapper.toDomain(entity));
    }

    @Override
    public PlayerDTO updatePlayer(UUID id, CreatePlayerDTO createPlayerDTO) {
        PlayerEntity entity = playerRepository.findById(id)
                .orElseThrow(() -> new PlayerNotFoundException(id));

        entity.setUsername(createPlayerDTO.getUsername());

        PlayerEntity updatedEntity = playerRepository.save(entity);
        return playerMapper.toDto(playerMapper.toDomain(updatedEntity));
    }

    @Override
    public void deletePlayerById(UUID id) {
        if (!playerRepository.existsById(id)) {
            throw new PlayerNotFoundException(id);
        }
        playerRepository.deleteById(id);
    }

    @Override
    public Optional<Player> login(String username) {
        Optional<PlayerEntity> playerOpt = playerRepository.findByUsername(username);

        if (playerOpt.isPresent()) {
            return Optional.of(playerMapper.toDomain(playerOpt.get()));
        } else {
            CreatePlayerDTO dto = new CreatePlayerDTO();
            dto.setUsername(username);

            Player player = playerMapper.fromCreatePlayerDTO(dto);
            PlayerEntity saved = playerRepository.save(playerMapper.toEntity(player));
            return Optional.of(playerMapper.toDomain(saved));
        }
    }
}