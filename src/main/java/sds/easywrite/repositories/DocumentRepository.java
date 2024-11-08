package sds.easywrite.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sds.easywrite.domain.Document;

import java.util.Optional;

@Repository
public interface DocumentRepository extends JpaRepository<Document, Integer> {
    Optional<Document> findOneByRefElasticId(String refId);
}
