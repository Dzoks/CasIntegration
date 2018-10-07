package rs.dzoks.client_application.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import rs.dzoks.client_application.model.User;

import java.sql.Timestamp;

public interface UserRepository extends JpaRepository<User, Integer>
{
    User getFirstByJmbg(String jmbg);
    User getFirstByUsernameAndTokenAndTokenExpirationTimeAfter(String username, String token, Timestamp expirationTime);
    User getFirstByUsername(String username);
}
