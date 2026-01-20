package yps.systems.ai.repository;

import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;
import yps.systems.ai.model.Tariff;

@Repository
public interface ITariffRepository extends Neo4jRepository<Tariff, String> {
}
