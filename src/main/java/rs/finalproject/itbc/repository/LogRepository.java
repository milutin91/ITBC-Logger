package rs.finalproject.itbc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import rs.finalproject.itbc.model.Log;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface LogRepository extends JpaRepository<Log, Integer> {

    @Query(value = "SELECT message, logtype, createdDate FROM logs " +
                    "WHERE token =:token and createdDate >:dateFrom", nativeQuery = true)
    List<?> searchDateFrom(@Param("token") String token, @Param("dateFrom") LocalDateTime dateFrom);

    @Query(value = "SELECT message, logtype, createdDate FROM logs " +
                    "WHERE token =:token and createdDate <:dateFrom", nativeQuery = true)
    List<?> searchDateTo(@Param("token") String token, @Param("dateFrom") LocalDateTime dateFrom);

    @Query(value = "SELECT message, logtype, createdDate FROM logs " +
                    "WHERE token =:token and message LIKE %:searchEntry%", nativeQuery = true)
    List<?> searchMessage(@Param("token") String token, @Param("searchEntry") String message);

    @Query(value = "SELECT message, logtype, createdDate FROM logs " +
                    "WHERE token =:token and logtype =:logtype", nativeQuery = true)
    List<?> searchLogType(@Param("token") String token, @Param("logtype") Integer logType);

    @Query(value = "SELECT message, logtype, createdDate FROM logs " +
                    "WHERE token =:token and createdDate BETWEEN :dateFrom and :dateTo", nativeQuery = true)
    List<?> searchDateFromTo(@Param("token") String token,
                             @Param("dateFrom") LocalDateTime dateFrom,
                             @Param("dateTo") LocalDateTime dateTo);

    @Query(value = "SELECT message, logtype, createdDate FROM logs " +
            "WHERE token =:token AND createdDate > :dateFrom " +
            "AND message LIKE %:searchEntry%", nativeQuery = true)
    List<?> searchDateFromAndMessage(@Param("token") String token,
                                     @Param("dateFrom") LocalDateTime dateFrom,
                                     @Param("searchEntry") String message);

    @Query(value = "SELECT message, logtype, createdDate FROM logs " +
            "WHERE token =:token AND createdDate > :dateFrom " +
            "AND logtype = :logtype", nativeQuery = true)
    List<?> searchDateFromAndLogtype(@Param("token") String token,
                                     @Param("dateFrom") LocalDateTime dateFrom,
                                     @Param("logtype") Integer logType);

    @Query(value = "SELECT message, logtype, createdDate FROM logs " +
            "WHERE token =:token AND createdDate < :dateTo " +
            "AND message LIKE %:searchEntry%", nativeQuery = true)
    List<?> searchDateToAndMessage(@Param("token") String token,
                                   @Param("dateTo") LocalDateTime dateTo,
                                   @Param("searchEntry") String message);

    @Query(value = "SELECT message, logtype, createdDate FROM logs " +
            "WHERE token =:token AND createdDate < :dateTo " +
            "AND logtype =:logtype", nativeQuery = true)
    List<?> searchDateToAndLogtype(@Param("token") String token,
                                   @Param("dateTo") LocalDateTime dateTo,
                                   @Param("logtype") Integer logType);

    @Query(value = "SELECT message, logtype, createdDate FROM logs " +
            "WHERE token =:token AND message LIKE %:searchEntry% " +
            "AND logtype =:logtype", nativeQuery = true)
    List<?> searchMessageAndLogtype(@Param("token") String token,
                                    @Param("searchEntry") String message,
                                    @Param("logtype") Integer logType);

    @Query(value = "SELECT message, logtype, createdDate FROM logs " +
            "WHERE token =:token AND createdDate BETWEEN :dateFrom and :dateTo " +
            "AND message LIKE %:searchEntry%", nativeQuery = true)
    List<?> searchDateFromDateToAndMessage(@Param("token") String token,
                                           @Param("dateFrom") LocalDateTime dateFrom,
                                           @Param("dateTo") LocalDateTime dateTo,
                                           @Param("searchEntry") String message);

    @Query(value = "SELECT message, logtype, createdDate FROM logs " +
            "WHERE token =:token AND createdDate BETWEEN :dateFrom and :dateTo " +
            "AND logtype =:logtype", nativeQuery = true)
    List<?> searchDateFromDateToAndLogtype(@Param("token") String token,
                                           @Param("dateFrom") LocalDateTime dateFrom,
                                           @Param("dateTo") LocalDateTime dateTo,
                                           @Param("logtype") Integer logType);

    @Query(value = "SELECT message, logtype, createdDate FROM logs " +
            "WHERE token =:token AND createdDate >:dateFrom " +
            "AND message LIKE %:searchEntry% AND logtype = :logtype", nativeQuery = true)
    List<?> searchDateFromMessageAndLogtype(@Param("token") String token,
                                            @Param("dateFrom") LocalDateTime dateFrom,
                                            @Param("searchEntry") String message,
                                            @Param("logtype") Integer logType);

    @Query(value = "SELECT message, logtype, createdDate FROM logs " +
            "WHERE token =:token AND createdDate <:dateTo " +
            "AND message LIKE %:searchEntry% AND logtype = :logtype", nativeQuery = true)
    List<?> searchDateToMessageAndLogtype(@Param("token") String token,
                                          @Param("dateTo") LocalDateTime dateTo,
                                          @Param("searchEntry") String message,
                                          @Param("logtype") Integer logType);

    @Query(value = "SELECT message, logtype, createdDate FROM logs " +
            "WHERE token =:token AND message LIKE %:searchEntry% " +
            "AND logtype = :logtype " +
            "AND createdDate BETWEEN :dateFrom and :dateTo", nativeQuery = true)
    List<?> searchByAllParams(@Param("token") String token,
                              @Param("dateFrom") LocalDateTime dateFrom,
                              @Param("dateTo") LocalDateTime dateTo,
                              @Param("searchEntry") String message,
                              @Param("logtype") Integer logType);
}
