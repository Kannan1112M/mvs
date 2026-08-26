package com.mvs.controller;

import com.mvs.afis.entity.AfisDedupeMatch;
import com.mvs.dto.ProcessReq;
import com.mvs.dto.VoterRegistrationDto;
import com.mvs.service.RegService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@CrossOrigin("*")
public class RegController {

    private final RegService regService;

    @GetMapping("/getDetail/{id}")
    public ResponseEntity<VoterRegistrationDto> getVoterRegDetails(@PathVariable("id") String id){
        return regService.getVoterRegDetails(id);
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<AfisDedupeMatch>> getAllAfisDedupResult(){
        return regService.getAllAfisDedupeMatch();
    }

    @PostMapping("/process-submit")
    public ResponseEntity<String> processSubmit(@RequestBody ProcessReq req){
        return regService.processSubmit(req);
    }
}
