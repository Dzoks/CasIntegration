package rs.dzoks.client_application.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import rs.dzoks.client_application.model.DrivingCategory;

public interface DrivingCategoryRepository extends JpaRepository<DrivingCategory,Integer> {

}
