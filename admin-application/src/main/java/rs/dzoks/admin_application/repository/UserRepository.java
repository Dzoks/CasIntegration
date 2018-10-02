package rs.dzoks.admin_application.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import rs.dzoks.admin_application.model.User;

public interface UserRepository extends JpaRepository<User, Integer>
{

    User getFirstByUsername(String username);
}
