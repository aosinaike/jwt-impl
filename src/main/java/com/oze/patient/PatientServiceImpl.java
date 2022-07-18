package com.oze.patient;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class PatientServiceImpl implements PatientService{

    PatientRepository repository;
    private ModelMapper modelMapper;

    public PatientServiceImpl(PatientRepository repository,ModelMapper modelMapper) {
        this.repository = repository;
        this.modelMapper = modelMapper;
    }


    @Override
    public Page<PatientDTO> fetchPatientByLastVisitDate(LocalDateTime dateTime, Pageable pageDetails) {
        Page<Patient> patientPage = repository.findPatientsByLastVisitDateIsLessThanEqual(dateTime,pageDetails);
        List<PatientDTO> dtOs = convertEntitiesToDTOs(patientPage.getContent());
        long t = patientPage.getTotalElements();
        Page<PatientDTO> pageImpl = new PageImpl<PatientDTO>(dtOs, pageDetails, t);
        return pageImpl;
    }


    @Override
    public Page<PatientDTO> fetchPatientByAge(int age, Pageable pageDetails) {
        Page<Patient> patientPage = repository.findPatientsByAgeIsLessThanEqual(age,pageDetails);
        List<PatientDTO> dtOs = convertEntitiesToDTOs(patientPage.getContent());
        long t = patientPage.getTotalElements();
        Page<PatientDTO> pageImpl = new PageImpl<PatientDTO>(dtOs, pageDetails, t);
        return pageImpl;
    }

    @Override
    @Transactional
    public Integer deletePatientsByDateRange(LocalDateTime startDateTime, LocalDateTime endDateTime) {
        return repository.deletePatientByLastVisitDateIsBetween(endDateTime, startDateTime);
    }


    public List<PatientDTO> convertEntitiesToDTOs(Iterable<Patient> patients) {
        List<PatientDTO> PatientDTOList = new ArrayList<>();
        for (Patient patient : patients) {
            PatientDTO PatientDTO = convertEntityToDTO(patient);
            PatientDTOList.add(PatientDTO);
        }
        return PatientDTOList;
    }

    public PatientDTO convertEntityToDTO(Patient patient) {
        PatientDTO PatientDTO = modelMapper.map(patient, PatientDTO.class);
        return PatientDTO;
    }

}
