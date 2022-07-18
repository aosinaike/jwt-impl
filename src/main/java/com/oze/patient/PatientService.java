package com.oze.patient;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;


public interface PatientService {

    public Page<PatientDTO> fetchPatientByAge(int age, Pageable pageDetails);
    public Page<PatientDTO> fetchPatientByLastVisitDate(LocalDateTime dateTime, Pageable pageable);
    public Integer deletePatientsByDateRange(LocalDateTime startDateTime, LocalDateTime endDateTime);
}
