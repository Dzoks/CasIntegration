package rs.dzoks.admin_application.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import rs.dzoks.admin_application.model.DocumentHasDrivingCategory;
import rs.dzoks.admin_application.model.DocumentHasDrivingCategoryPK;

public interface DocumentHasDrivingCategoryRepository extends JpaRepository<DocumentHasDrivingCategory, DocumentHasDrivingCategoryPK> {
}
