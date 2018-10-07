package rs.dzoks.client_application.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import rs.dzoks.client_application.model.DocumentHasDrivingCategory;
import rs.dzoks.client_application.model.DocumentHasDrivingCategoryPK;

import java.util.List;

public interface DocumentHasDrivingCategoryRepository extends JpaRepository<DocumentHasDrivingCategory, DocumentHasDrivingCategoryPK> {

    List<DocumentHasDrivingCategory> getAllByDocumentId(Integer id);
}
