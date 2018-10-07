package rs.dzoks.admin_application.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import rs.dzoks.admin_application.model.DrivingCategory;

public interface DrivingCategoryRepository extends JpaRepository<DrivingCategory,Integer> {

}
