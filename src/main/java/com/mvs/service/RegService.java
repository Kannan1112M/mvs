package com.mvs.service;

import com.mvs.afis.entity.AfisDedupeMatch;
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
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import tools.jackson.databind.ObjectMapper;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

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

    public ResponseEntity<byte[]> getAllBiometrics(String id)
            throws IOException {

        BiometricDetails biometric =
                biometricDetailsRepository
                        .getAllBiometrics(id)
                        .orElseThrow(() ->
                                new EntityNotFoundException(
                                        "Biometric not found: " + id
                                )
                        );

        ByteArrayOutputStream byteStream =
                new ByteArrayOutputStream();

        try (
                ZipOutputStream zip =
                        new ZipOutputStream(byteStream)
        ) {

            addToZip(zip, "left-thumb.bin",
                    biometric.getLeftThumb());

            addToZip(zip, "left-index-finger.bin",
                    biometric.getLeftIndexFinger());

            addToZip(zip, "left-middle-finger.bin",
                    biometric.getLeftMiddleFinger());

            addToZip(zip, "left-ring-finger.bin",
                    biometric.getLeftRingFinger());

            addToZip(zip, "left-little-finger.bin",
                    biometric.getLeftLittleFinger());

            addToZip(zip, "right-thumb.bin",
                    biometric.getRightThumb());

            addToZip(zip, "right-index-finger.bin",
                    biometric.getRightIndexFinger());

            addToZip(zip, "right-middle-finger.bin",
                    biometric.getRightMiddleFinger());

            addToZip(zip, "right-ring-finger.bin",
                    biometric.getRightRingFinger());

            addToZip(zip, "right-little-finger.bin",
                    biometric.getRightLittleFinger());

            addToZip(zip, "left-iris.bin",
                    biometric.getLeftIris());

            addToZip(zip, "right-iris.bin",
                    biometric.getRightIris());
        }

        return ResponseEntity.ok()
                .header(
                        HttpHeaders.CONTENT_DISPOSITION,
                        "attachment; filename=biometrics.zip"
                )
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(byteStream.toByteArray());
    }

    private void addToZip(
            ZipOutputStream zip,
            String fileName,
            byte[] data
    ) throws IOException {

        if (data == null) {
            return;
        }

        ZipEntry entry = new ZipEntry(fileName);

        zip.putNextEntry(entry);

        zip.write(data);

        zip.closeEntry();
    }
}
