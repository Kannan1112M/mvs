package com.mvs.reg.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Table(
    name = "voter_reg_details",
    schema = "public"
)
@Data
public class VoterRegDetails {

    @Id
    @Column(name = "registration_id", nullable = false, length = 255)
    private String registrationId;

    @Column(name = "voter_id", nullable = false, length = 50)
    private String voterId;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "demographic_data", columnDefinition = "TEXT")
    private String demographicData;

    @Column(name = "documents_data", columnDefinition = "TEXT")
    private String documentsData;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

}