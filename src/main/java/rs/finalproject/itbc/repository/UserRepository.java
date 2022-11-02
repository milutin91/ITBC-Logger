package rs.finalproject.itbc.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import rs.finalproject.itbc.model.DTO.ClientInfoResponseDTO;
import rs.finalproject.itbc.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import rs.finalproject.itbc.model.enums.UserRole;

import java.util.*;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {
    boolean existsUserByUsername(String username);
    boolean existsUserByEmail(String email);

    List<User> findUserByUsernameAndPassword(String username,
                                             String password);

    @Query(value = "SELECT * FROM users WHERE email =:email and password =:password", nativeQuery = true)
    List<User> findUserByEmailAndPassword(@Param("email") String email,
                                             @Param("password") String password);

    @Query(value = "SELECT * FROM users WHERE email =:email", nativeQuery = true)
    List<User> findByEmail(@Param("email") String username);

    @Query(value = "SELECT userRole FROM users WHERE email =:email", nativeQuery = true)
    UserRole findUserRoleByEmail(@Param("email") String username);

    @Query(value = "SELECT userRole FROM users WHERE username =:username", nativeQuery = true)
    UserRole findUserRoleByUsername(@Param("username") String username);

    @Query(value = "SELECT * FROM users WHERE username =:username", nativeQuery = true)
    List<User> findByUsername(@Param("username") String username);

    @Query("SELECT new rs.finalproject.itbc.model.DTO.ClientInfoResponseDTO(u.userID, u.username, u.email, " +
                                            "(SELECT COUNT(*) FROM Log l WHERE l.token = u.userID) AS logCount) " +
            "FROM User u " +
            "WHERE u.userRole <> :userRole")
        List<ClientInfoResponseDTO> findAllClients(@Param("userRole") UserRole userRole);

    @Transactional
    @Modifying
    @Query("UPDATE User SET password = :password WHERE userID = :userID")
    void updateClientPassword(@Param("password") String password, @Param("userID") UUID id);

    Map<String, String> tokens = new HashMap<>();
    Map<String, String> adminTokens = new HashMap<>();
}
