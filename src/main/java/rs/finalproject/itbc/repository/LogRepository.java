package rs.finalproject.itbc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import rs.finalproject.itbc.model.Log;
import rs.finalproject.itbc.model.User;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface LogRepository extends JpaRepository<Log, Integer> {

    @Query(value = "SELECT message, logtype, createdDate FROM logs WHERE token =:token and createdDate >:dateFrom", nativeQuery = true)
    List<?> dateFrom(@Param("token") String token, @Param("dateFrom") LocalDateTime dateFrom);
}
