package com.mvs.dto;

import lombok.Data;

@Data
public class VoterRegistrationDto {

    private Boolean addressCopy;

    private Integer calculatedAge;

    private String dobDay;
    private String dobMonth;
    private String dobYear;

    private String firstName;
    private String gender;
    private String lastName;
    private String middleName;

    private String permanentAddressLine1;
    private String permanentBarangay;
    private String permanentCity;
    private String permanentCountry;
    private String permanentProvince;
    private String permanentZipcode;

    private String pobCity;
    private String pobCountry;
    private String pobProvince;

    private String presentAddressLine1;
    private String presentBarangay;
    private String presentCity;
    private String presentCountry;
    private String presentPrecinct;
    private String presentProvince;
    private String presentZipcode;

    private String registrationType;
    private String residenceStatus;
    private String suffix;

    private byte[] face;

}