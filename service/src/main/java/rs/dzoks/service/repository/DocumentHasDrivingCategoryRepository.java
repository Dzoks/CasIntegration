package rs.dzoks.service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import rs.dzoks.service.model.DocumentHasDrivingCategory;
import rs.dzoks.service.model.DocumentHasDrivingCategoryPK;

import java.util.List;

public interface DocumentHasDrivingCategoryRepository extends JpaRepository<DocumentHasDrivingCategory, DocumentHasDrivingCategoryPK> {

    List<DocumentHasDrivingCategory> getAllByDocumentId(Integer id);
}
