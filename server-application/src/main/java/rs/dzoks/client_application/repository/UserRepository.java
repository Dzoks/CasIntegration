package rs.dzoks.client_application.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import rs.dzoks.client_application.model.User;

public interface UserRepository extends JpaRepository<User, Integer>
{

    User getFirstByUsername(String username);
}
