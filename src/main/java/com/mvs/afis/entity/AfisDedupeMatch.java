package com.mvs.afis.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
@Table(
        name = "afis_dedupe_match",
        schema = "public",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "uk_afis_probe_gallery",
                        columnNames = {"probe_id", "gallery_id"}
                )
        },
        indexes = {
                @Index(
                        name = "idx_afis_match_gallery",
                        columnList = "gallery_id"
                ),
                @Index(
                        name = "idx_afis_match_probe",
                        columnList = "probe_id"
                )
        }
)
public class AfisDedupeMatch {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "probe_id", nullable = false, length = 100)
    private String probeId;

    @Column(name = "gallery_id", nullable = false, length = 100)
    private String galleryId;

    @Column(name = "matched_biometric", length = 50)
    private String matchedBiometric;

    @Column(name = "match_score")
    private Double matchScore;

    @Column(name = "threshold")
    private Double threshold;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "operator_id")
    private String operatorId;

    @Column(name = "operator_status")
    private String operatorStatus;

    @Column(name = "operator_comment")
    private String operatorComment;

    @Column(name = "process_code")
    private String processCode;
}