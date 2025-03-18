package com.example.krompirica.Line;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.sound.sampled.Line;
import java.util.List;

public interface LineRepo extends JpaRepository<LineEntity,Integer> {
    @Query("select l from LineEntity l where l.cityIdFrom.id=:cityIdFrom and l.cityIdTo.id=:cityIdTo")
    List<LineEntity> getLineByCityFromTo(@Param("cityIdFrom") Integer cityIdFrom, @Param("cityIdTo") Integer cityIdTo);
}
