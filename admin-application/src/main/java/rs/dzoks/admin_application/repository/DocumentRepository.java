package rs.dzoks.admin_application.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import rs.dzoks.admin_application.model.Document;

public interface DocumentRepository extends JpaRepository<Document,Integer> {
}
