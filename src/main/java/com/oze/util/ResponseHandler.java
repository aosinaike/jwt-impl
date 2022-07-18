package com.oze.util;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ResponseHandler {

    public static ResponseEntity<Object> generateResponse(String message, HttpStatus status, Object responseObj, List<String> errors) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("message", message);
        map.put("status", status.value());
        if (responseObj != null) {
            map.put("data", responseObj);
        }
        if (errors != null && errors.size()>0) {
            map.put("errors",errors);
        }
        return new ResponseEntity<Object>(map,status);
    }
}
