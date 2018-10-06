package rs.dzoks.service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import rs.dzoks.service.model.Document;

import java.sql.Timestamp;
import java.util.List;

public interface DocumentRepository extends JpaRepository<Document,Integer> {

    List<Document> getAllByDateOfIssue(Timestamp data);
    List<Document>  getAllByUserId(Integer userId);
}
