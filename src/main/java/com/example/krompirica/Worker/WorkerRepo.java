package com.example.krompirica.Worker;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface WorkerRepo extends JpaRepository<WorkerEntity,Integer> {
    @Query(
            "" +
                    "SELECT CASE WHEN COUNT(u) > 0 THEN " +
                    "TRUE ELSE FALSE END " +
                    "FROM WorkerEntity u " +
                    "WHERE u.username = ?1"
    )
    Boolean selectExistsUsername(String username);

    WorkerEntity findByUsername(String username);

}
