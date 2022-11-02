package rs.finalproject.itbc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import rs.finalproject.itbc.model.DTO.LogSearchResultResponseDTO;
import rs.finalproject.itbc.model.Log;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface LogRepository extends JpaRepository<Log, Integer> {

    @Query("SELECT new rs.finalproject.itbc.model.DTO.LogSearchResultResponseDTO(l.message, l.logType, l.createdDate) " +
            "FROM Log l WHERE token =:token and createdDate >:dateFrom")
    List<LogSearchResultResponseDTO> searchDateFrom(@Param("token") String token, @Param("dateFrom") LocalDateTime dateFrom);

    @Query("SELECT new rs.finalproject.itbc.model.DTO.LogSearchResultResponseDTO(l.message, l.logType, l.createdDate) " +
            "FROM Log l WHERE token =:token and createdDate <:dateTo")
    List<?> searchDateTo(@Param("token") String token, @Param("dateTo") LocalDateTime dateFrom);

    @Query("SELECT new rs.finalproject.itbc.model.DTO.LogSearchResultResponseDTO(l.message, l.logType, l.createdDate) " +
            "FROM Log l WHERE token =:token and message LIKE %:searchEntry%")
    List<?> searchMessage(@Param("token") String token, @Param("searchEntry") String message);

    @Query("SELECT new rs.finalproject.itbc.model.DTO.LogSearchResultResponseDTO(l.message, l.logType, l.createdDate) " +
            "FROM Log l WHERE token =:token and logType =:logType")
    List<?> searchLogType(@Param("token") String token, @Param("logType") Integer logType);

    @Query("SELECT new rs.finalproject.itbc.model.DTO.LogSearchResultResponseDTO(l.message, l.logType, l.createdDate) " +
            "FROM Log l WHERE token =:token and createdDate BETWEEN :dateFrom and :dateTo")
    List<?> searchDateFromTo(@Param("token") String token,
                             @Param("dateFrom") LocalDateTime dateFrom,
                             @Param("dateTo") LocalDateTime dateTo);

    @Query("SELECT new rs.finalproject.itbc.model.DTO.LogSearchResultResponseDTO(l.message, l.logType, l.createdDate) " +
            "FROM Log l WHERE token =:token AND createdDate > :dateFrom " +
            "AND message LIKE %:searchEntry%")
    List<?> searchDateFromAndMessage(@Param("token") String token,
                                     @Param("dateFrom") LocalDateTime dateFrom,
                                     @Param("searchEntry") String message);

    @Query("SELECT new rs.finalproject.itbc.model.DTO.LogSearchResultResponseDTO(l.message, l.logType, l.createdDate) " +
            "FROM Log l WHERE token =:token AND createdDate > :dateFrom " +
            "AND logType = :logType")
    List<?> searchDateFromAndLogtype(@Param("token") String token,
                                     @Param("dateFrom") LocalDateTime dateFrom,
                                     @Param("logType") Integer logType);

    @Query("SELECT new rs.finalproject.itbc.model.DTO.LogSearchResultResponseDTO(l.message, l.logType, l.createdDate) " +
            "FROM Log l WHERE token =:token AND createdDate < :dateTo " +
            "AND message LIKE %:searchEntry%")
    List<?> searchDateToAndMessage(@Param("token") String token,
                                   @Param("dateTo") LocalDateTime dateTo,
                                   @Param("searchEntry") String message);

    @Query("SELECT new rs.finalproject.itbc.model.DTO.LogSearchResultResponseDTO(l.message, l.logType, l.createdDate) " +
            "FROM Log l " +
            "WHERE token =:token AND createdDate < :dateTo " +
            "AND logType =:logType")
    List<?> searchDateToAndLogtype(@Param("token") String token,
                                   @Param("dateTo") LocalDateTime dateTo,
                                   @Param("logType") Integer logType);

    @Query("SELECT new rs.finalproject.itbc.model.DTO.LogSearchResultResponseDTO(l.message, l.logType, l.createdDate) " +
            "FROM Log l " +
            "WHERE token =:token AND message LIKE %:searchEntry% " +
            "AND logType =:logType")
    List<?> searchMessageAndLogtype(@Param("token") String token,
                                    @Param("searchEntry") String message,
                                    @Param("logType") Integer logType);

    @Query("SELECT new rs.finalproject.itbc.model.DTO.LogSearchResultResponseDTO(l.message, l.logType, l.createdDate) " +
            "FROM Log l " +
            "WHERE token =:token AND createdDate BETWEEN :dateFrom and :dateTo " +
            "AND message LIKE %:searchEntry%")
    List<?> searchDateFromDateToAndMessage(@Param("token") String token,
                                           @Param("dateFrom") LocalDateTime dateFrom,
                                           @Param("dateTo") LocalDateTime dateTo,
                                           @Param("searchEntry") String message);

    @Query("SELECT new rs.finalproject.itbc.model.DTO.LogSearchResultResponseDTO(l.message, l.logType, l.createdDate) " +
            "FROM Log l " +
            "WHERE token =:token AND createdDate BETWEEN :dateFrom and :dateTo " +
            "AND logType =:logType")
    List<?> searchDateFromDateToAndLogtype(@Param("token") String token,
                                           @Param("dateFrom") LocalDateTime dateFrom,
                                           @Param("dateTo") LocalDateTime dateTo,
                                           @Param("logType") Integer logType);

    @Query("SELECT new rs.finalproject.itbc.model.DTO.LogSearchResultResponseDTO(l.message, l.logType, l.createdDate) " +
            "FROM Log l " +
            "WHERE token =:token AND createdDate >:dateFrom " +
            "AND message LIKE %:searchEntry% AND logType = :logType")
    List<?> searchDateFromMessageAndLogtype(@Param("token") String token,
                                            @Param("dateFrom") LocalDateTime dateFrom,
                                            @Param("searchEntry") String message,
                                            @Param("logType") Integer logType);

    @Query("SELECT new rs.finalproject.itbc.model.DTO.LogSearchResultResponseDTO(l.message, l.logType, l.createdDate) " +
            "FROM Log l " +
            "WHERE token =:token AND createdDate <:dateTo " +
            "AND message LIKE %:searchEntry% AND logType = :logType")
    List<?> searchDateToMessageAndLogtype(@Param("token") String token,
                                          @Param("dateTo") LocalDateTime dateTo,
                                          @Param("searchEntry") String message,
                                          @Param("logType") Integer logType);

    @Query("SELECT new rs.finalproject.itbc.model.DTO.LogSearchResultResponseDTO(l.message, l.logType, l.createdDate) " +
            "FROM Log l " +
            "WHERE token =:token AND message LIKE %:searchEntry% " +
            "AND logType = :logType " +
            "AND createdDate BETWEEN :dateFrom and :dateTo")
    List<?> searchByAllParams(@Param("token") String token,
                              @Param("dateFrom") LocalDateTime dateFrom,
                              @Param("dateTo") LocalDateTime dateTo,
                              @Param("searchEntry") String message,
                              @Param("logType") Integer logType);

    @Query("SELECT new rs.finalproject.itbc.model.DTO.LogSearchResultResponseDTO(l.message, l.logType, l.createdDate) " +
            "FROM Log l " +
            "WHERE token =:token")
    List<?> searchAllLogsByToken(@Param("token") String token);
}
