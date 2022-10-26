package rs.finalproject.itbc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import rs.finalproject.itbc.model.Log;
@Repository
public interface LogRepository extends JpaRepository<Log, Integer> {
}
