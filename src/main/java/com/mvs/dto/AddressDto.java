package com.mvs.dto;

import lombok.Data;

@Data
public class AddressDto {

    private String presentStreet;
    private String presentProvince;
    private String presentCity;
    private String presentBarangay;
    private String presentZipCode;
    private Boolean sameAsPresent;
    private String permanentStreet;
    private String permanentProvince;
    private String permanentCity;
    private String permanentBarangay;
    private String permanentZipCode;

}