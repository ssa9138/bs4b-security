package at.fhv.epa.demoproject.service;

import at.fhv.epa.demoproject.domain.model.BrawlerData;
import at.fhv.epa.demoproject.persistence.BrawlerDataRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BrawlerDataService {
    private final BrawlerDataRepository repository;

    public BrawlerDataService(BrawlerDataRepository repository) {
        this.repository = repository;
    }

    public List<BrawlerData> findAll() {
        return repository.findAll();
    }

    public Optional<BrawlerData> findById(String id) {
        return repository.findById(id);
    }

    public BrawlerData save(BrawlerData data) {
        return repository.save(data);
    }

    public void delete(String id) {
        repository.deleteById(id);
    }
}
