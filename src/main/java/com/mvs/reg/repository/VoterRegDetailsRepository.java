package com.mvs.reg.repository;

import com.mvs.reg.entity.VoterRegDetails;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface VoterRegDetailsRepository extends JpaRepository<VoterRegDetails , String> {
    Optional<VoterRegDetails> findByVoterId(String id);
}
