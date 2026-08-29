package com.mvs.reg.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.time.LocalDateTime;

@Entity
@Table(
    name = "biometric_details",
    schema = "public"
)
@Data
public class BiometricDetails {

    @Id
    @Column(name = "registration_id", nullable = false, length = 255)
    private String registrationId;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @JdbcTypeCode(SqlTypes.BINARY)
    @Column(name = "face" , columnDefinition = "bytea")
    private byte[] face;

    @JdbcTypeCode(SqlTypes.BINARY)
    @Column(name = "left_index_finger" , columnDefinition = "bytea")
    private byte[] leftIndexFinger;

    @JdbcTypeCode(SqlTypes.BINARY)
    @Column(name = "left_iris" , columnDefinition = "bytea")
    private byte[] leftIris;

    @JdbcTypeCode(SqlTypes.BINARY)
    @Column(name = "left_little_finger" , columnDefinition = "bytea")
    private byte[] leftLittleFinger;

    @JdbcTypeCode(SqlTypes.BINARY)
    @Column(name = "left_middle_finger" , columnDefinition = "bytea")
    private byte[] leftMiddleFinger;

    @JdbcTypeCode(SqlTypes.BINARY)
    @Column(name = "left_ring_finger" , columnDefinition = "bytea")
    private byte[] leftRingFinger;

    @JdbcTypeCode(SqlTypes.BINARY)
    @Column(name = "left_thumb" , columnDefinition = "bytea")
    private byte[] leftThumb;

    @JdbcTypeCode(SqlTypes.BINARY)
    @Column(name = "right_index_finger" , columnDefinition = "bytea")
    private byte[] rightIndexFinger;

    @JdbcTypeCode(SqlTypes.BINARY)
    @Column(name = "right_iris" , columnDefinition = "bytea")
    private byte[] rightIris;

    @JdbcTypeCode(SqlTypes.BINARY)
    @Column(name = "right_little_finger" , columnDefinition = "bytea")
    private byte[] rightLittleFinger;

    @JdbcTypeCode(SqlTypes.BINARY)
    @Column(name = "right_middle_finger" , columnDefinition = "bytea")
    private byte[] rightMiddleFinger;

    @JdbcTypeCode(SqlTypes.BINARY)
    @Column(name = "right_ring_finger" , columnDefinition = "bytea")
    private byte[] rightRingFinger;

    @JdbcTypeCode(SqlTypes.BINARY)
    @Column(name = "right_thumb" , columnDefinition = "bytea")
    private byte[] rightThumb;

    @Column(name = "voter_id")
    private String voterId;

    @Column(name = "status")
    private String status;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

}