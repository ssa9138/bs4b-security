package at.fhv.epa.demoproject.persistence;

import at.fhv.epa.demoproject.domain.model.BrawlerData;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface BrawlerDataRepository extends MongoRepository<BrawlerData, String> {
}
