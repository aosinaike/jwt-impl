package com.oze.patient;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {

    public Page<Patient> findPatientsByAgeIsLessThanEqual(int age,Pageable pageDetails);
    public Page<Patient> findPatientsByAgeIsLessThan(int age,Pageable pageDetails);
    public Page<Patient> findPatientsByLastVisitDateIsLessThanEqual(LocalDateTime dateTime, Pageable pageDetails);
    public Integer deletePatientByLastVisitDateIsBetween(LocalDateTime endDateTime, LocalDateTime startDateTime);
}
