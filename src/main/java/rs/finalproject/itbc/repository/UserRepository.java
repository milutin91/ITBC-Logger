package rs.finalproject.itbc.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import rs.finalproject.itbc.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;


@Repository
public interface UserRepository extends JpaRepository<User, UUID> {
    boolean existsUserByUsername(String username);
    boolean existsUserByEmail(String email);

//    @Query(value = "SELECT * FROM users WHERE username =:username and password =:password", nativeQuery = true)
//    List<User> findUserByUsernameAndPassword(@Param("username") String username,
//                                             @Param("password") String password);

    List<User> findUserByUsernameAndPassword(String username,
                                             String password);

    @Query(value = "SELECT * FROM users WHERE username =:username", nativeQuery = true)
    List<User> findByUsername(@Param("username") String username);
}
