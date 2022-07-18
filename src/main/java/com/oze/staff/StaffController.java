package com.oze.staff;

import com.oze.util.ResponseHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class StaffController {

    @Autowired
    StaffService staffService;

    @PostMapping(path = "/staff/new")
    public ResponseEntity<Object> createStaffProfile(@RequestBody CreateStaffRequestDTO requestDTO){
        if(!isValidRequest(requestDTO.getName())){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        Staff staffResp = staffService.addStaffProfile(requestDTO);
        if(staffResp == null){
            return ResponseHandler.generateResponse("Unable to create staff profile",
                    HttpStatus.OK, staffResp, null);
        }
        return ResponseHandler.generateResponse("Staff profile successfully created",
                HttpStatus.CREATED, staffResp, null);
    }
    @PostMapping("/staff/update")
    public ResponseEntity<Object> updateStaffProfile(@RequestBody UpdateStaffRequestDTO requestDTO){
        if(!isValidRequest(requestDTO.getName())){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        Staff staffResp = staffService.updateProfile(requestDTO);
        if(staffResp == null){
            return ResponseHandler.generateResponse("Staff profile not found!",
                    HttpStatus.OK, staffResp, null);
        }
        return ResponseHandler.generateResponse("Successfully added data!",
                HttpStatus.OK, staffResp, null);
    }

    private static boolean isValidRequest(String request) {
        return request.trim().length() > 0;
    }
}
