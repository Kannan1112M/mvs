package com.mvs.exceptionhandler;

import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({EntityExistsException.class})
    public ResponseEntity<?> entityExistsException(EntityExistsException e){
        return new ResponseEntity<>(e.getMessage() , HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({EntityNotFoundException.class})
    public ResponseEntity<?> entityNotFoundException(EntityNotFoundException e){
        return new ResponseEntity<>(e.getMessage() , HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = {MethodArgumentNotValidException.class})
    public ResponseEntity<Map<String, String>> methodArgumentNotValidException(MethodArgumentNotValidException ex){

        HashMap<String ,String> hm= new HashMap<>();
//        hm.put(ex.getBindingResult().getFieldError().getField(), ex.getBindingResult().getFieldError().getDefaultMessage());
        hm.put("error" , "Invalid input data");
        return new ResponseEntity<>(hm,HttpStatus.BAD_REQUEST);

    }

//    @ExceptionHandler(value = {JwtException.class})
//    public ResponseEntity<?> jwtException(JwtException ex) {
//        return new ResponseEntity<>(ex.getMessage(), HttpStatus.UNAUTHORIZED);
//    }
//
//    @ExceptionHandler(value = {ExpiredJwtException.class})
//    public ResponseEntity<?> expiredJwtException(ExpiredJwtException ex) {
//        return new ResponseEntity<>("JWT Token Expired. Please Contact MSP", HttpStatus.UNAUTHORIZED);
//    }
//
//    @ExceptionHandler(value = {SignatureException.class})
//    public ResponseEntity<?> signatureException(SignatureException ex) {
//        return new ResponseEntity<>("INVALID TOKEN", HttpStatus.UNAUTHORIZED);
//    }


}
