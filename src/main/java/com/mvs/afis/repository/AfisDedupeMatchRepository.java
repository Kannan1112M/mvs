package com.mvs.afis.repository;

import com.mvs.afis.entity.AfisDedupeMatch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface AfisDedupeMatchRepository extends JpaRepository<AfisDedupeMatch, Long> {

    @Query("select a from AfisDedupeMatch a where a.processCode='0'")
    List<AfisDedupeMatch> getAll();

    Optional<AfisDedupeMatch> findByProbeIdAndGalleryId(String probId , String candidateId);

}
