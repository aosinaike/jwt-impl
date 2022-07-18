package com.oze.patient;

import com.oze.util.ResponseHandler;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

@Controller
public class PatientController {

    @Autowired
    PatientService patientService;
    @Autowired
    DownloadService downloadService;

    @PostMapping("/patient/all")
    public ResponseEntity<Object> fetchAllPatient(@RequestBody FetchPatientRequest patientRequest,
                                                  @RequestParam int page, @RequestParam int size){
        int defaultPageSize = 0;
        if (size == 0){
            defaultPageSize = 20;
        }else{
            defaultPageSize = size;
        }
        Pageable pageDetails = PageRequest.of(page, defaultPageSize);
        Page<PatientDTO> patientDetails = patientService.fetchPatientByAge(patientRequest.getAge(), pageDetails);
        return ResponseHandler.generateResponse("Patient records fetched!",
                HttpStatus.OK, patientDetails, null);
    }

    @PostMapping("/patient/remove")
    public ResponseEntity<Object> removeAllPatient(@RequestBody FetchPatientRequest patientRequest){
        Integer resp = patientService.deletePatientsByDateRange(patientRequest.getEndDateTime(),patientRequest.getStartDateTime());
        return ResponseHandler.generateResponse(resp+" Patient records removed successfully!",
                HttpStatus.OK, null, null);
    }

    @PostMapping("/patient/download")
    public ResponseEntity<Object> downloadPatientRecord(@RequestBody FetchPatientRequest patientRequest, @RequestParam int page, @RequestParam int size){
        int defaultPageSize = 0;
        if (size == 0){
            defaultPageSize = 20;
        }else{
            defaultPageSize = size;
        }
        Pageable pageDetails = PageRequest.of(page, defaultPageSize);
        Page<PatientDTO> patientDetails = patientService.fetchPatientByAge(patientRequest.getAge(), pageDetails);
        List<PatientDTO> patientDTOList = new ArrayList<>();
        patientDetails.iterator().forEachRemaining(patientDTOList::add);
        final InputStreamResource resource = new InputStreamResource(downloadService.load(patientDTOList));
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "patientrecord")
                .contentType(MediaType.parseMediaType("text/csv"))
                .body(resource);
    }
}
