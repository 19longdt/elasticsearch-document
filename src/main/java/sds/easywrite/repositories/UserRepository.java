package sds.easywrite.repositories;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import sds.easywrite.domain.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    @Query(value = "SELECT * FROM kyc_user u where u.username = CONVERT(VARCHAR(4000), ?1) and u.status = 1", nativeQuery = true)
    Optional<User> findOneByUsernameActive(String username);

    Integer countByUsernameAndStatus(String username, Boolean status);
}
