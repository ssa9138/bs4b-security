package at.fhv.epa.demoproject.service;

import at.fhv.epa.demoproject.domain.model.MapData;
import at.fhv.epa.demoproject.persistence.MapDataRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MapDataService {
    private final MapDataRepository repository;

    public MapDataService(MapDataRepository repository) {
        this.repository = repository;
    }

    public List<MapData> findAll() {
        return repository.findAll();
    }

    public Optional<MapData> findById(String id) {
        return repository.findById(id);
    }

    public MapData save(MapData data) {
        return repository.save(data);
    }

    public void delete(String id) {
        repository.deleteById(id);
    }
}
