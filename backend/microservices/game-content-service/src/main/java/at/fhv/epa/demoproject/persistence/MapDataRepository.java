package at.fhv.epa.demoproject.persistence;

import at.fhv.epa.demoproject.domain.model.MapData;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface MapDataRepository extends MongoRepository<MapData, String> {
}
