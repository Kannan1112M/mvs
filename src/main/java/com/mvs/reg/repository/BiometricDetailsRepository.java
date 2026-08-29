package com.mvs.reg.repository;

import com.mvs.reg.entity.BiometricDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface BiometricDetailsRepository extends JpaRepository<BiometricDetails , String> {

    @Query("select b.face from BiometricDetails b where b.registrationId=:id")
    byte[] getFaceById(String id);

    @Query("select b from BiometricDetails b where b.voterId=:id")
    Optional<BiometricDetails> getAllBiometrics(String id);
}
