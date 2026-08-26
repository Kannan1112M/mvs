package com.mvs.afis.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
@Table(
        name = "afis_dedupe_result",
        schema = "public"
)
public class AfisDedupeResult {

    @Id
    @Column(name = "id", nullable = false, length = 255)
    private String id;

    @Column(name = "match_result", length = 255)
    private String matchResult;

    @Column(name = "matched_ids", columnDefinition = "TEXT")
    private String matchedIds;

    @Column(name = "operator_id")
    private String operatorId;

    @Column(name = "operator_status")
    private String operatorStatus;

    @Column(name = "operator_comment")
    private String operatorComment;

    @Column(name = "process_code")
    private String processCode;

    @Column(name = "crd_time")
    private LocalDateTime crdTime;
}