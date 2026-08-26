package com.mvs.dto;

import lombok.Data;

@Data
public class ProcessReq {
    private String probId;
    private String candidateId;
    private String operatorId;
    private String operatorStatus;
    private String operatorComment;
}
