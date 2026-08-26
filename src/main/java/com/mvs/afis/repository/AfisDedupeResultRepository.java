package com.mvs.afis.repository;

import com.mvs.afis.entity.AfisDedupeResult;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AfisDedupeResultRepository extends JpaRepository<AfisDedupeResult , String> {

    @Query("select a from AfisDedupeResult a where a.processCode='0'")
    List<AfisDedupeResult> getAll();
}
