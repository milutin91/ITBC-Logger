package rs.finalproject.itbc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import rs.finalproject.itbc.model.Log;

public interface LogRepository extends JpaRepository<Log, Integer> {
}
