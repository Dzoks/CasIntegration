package rs.dzoks.service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import rs.dzoks.service.model.User;

import java.sql.Timestamp;

public interface UserRepository extends JpaRepository<User, Integer>
{
    User getFirstByUsernameAndTokenAndTokenExpirationTimeAfter(String username, String token, Timestamp expirationTime);
    User getFirstByUsername(String username);
    User getFirstByJmbg(String jmbg);
}
