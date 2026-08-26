package com.mvs.service;

import com.mvs.afis.entity.AfisDedupeMatch;
import com.mvs.afis.entity.AfisDedupeResult;
import com.mvs.afis.repository.AfisDedupeMatchRepository;
import com.mvs.afis.repository.AfisDedupeResultRepository;
import com.mvs.dto.ProcessReq;
import com.mvs.dto.VoterRegistrationDto;
import com.mvs.reg.entity.BiometricDetails;
import com.mvs.reg.entity.VoterRegDetails;
import com.mvs.reg.repository.BiometricDetailsRepository;
import com.mvs.reg.repository.VoterRegDetailsRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import tools.jackson.databind.ObjectMapper;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RegService {

    private final BiometricDetailsRepository biometricDetailsRepository;
    private final VoterRegDetailsRepository voterRegDetailsRepository;
    private final AfisDedupeResultRepository afisDedupeResultRepository;
    private final AfisDedupeMatchRepository afisDedupeMatchRepository;

    public ResponseEntity<VoterRegistrationDto> getVoterRegDetails(String id) {

        VoterRegDetails voterRegDetails = voterRegDetailsRepository.findByVoterId(id).orElseThrow(
                ()-> new EntityNotFoundException(String.format("VoterRegDetails ID: %s", id))
        );

        byte[] face = biometricDetailsRepository.getFaceById(voterRegDetails.getRegistrationId());

        VoterRegistrationDto dto;

        ObjectMapper objectMapper = new ObjectMapper();

        try {

            dto = objectMapper.readValue(
                    voterRegDetails.getDemographicData(),
                    VoterRegistrationDto.class
            );

        } catch (Exception e) {

            throw new IllegalStateException(
                    "Unable to parse demographic data",
                    e
            );
        }

        dto.setFace(face);

        return ResponseEntity.ok(dto);

    }

    public ResponseEntity<List<AfisDedupeMatch>> getAllAfisDedupeMatch() {
        return ResponseEntity.ok(afisDedupeMatchRepository.getAll());
    }

    public ResponseEntity<String> processSubmit(ProcessReq req) {
        AfisDedupeMatch afisDedupeMatch = afisDedupeMatchRepository.findByProbeIdAndGalleryId(req.getProbId() , req.getCandidateId()).orElseThrow(
                ()-> new EntityNotFoundException("Data Not Found")
        );

        afisDedupeMatch.setOperatorId(req.getOperatorId());
        afisDedupeMatch.setOperatorStatus(req.getOperatorStatus());
        afisDedupeMatch.setOperatorComment(req.getOperatorComment());
        afisDedupeMatch.setProcessCode("1");

        afisDedupeMatchRepository.save(afisDedupeMatch);

        return ResponseEntity.ok("Submitted Successfully");

    }
}
