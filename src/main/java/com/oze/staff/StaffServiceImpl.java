package com.oze.staff;

import org.springframework.stereotype.Service;

import java.time.Clock;
import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class StaffServiceImpl implements StaffService{

    public StaffRepository staffRepository;

    public StaffServiceImpl(StaffRepository staffRepository){
        this.staffRepository = staffRepository;
    }

    @Override
    public Staff addStaffProfile(CreateStaffRequestDTO requestDTO) {
        Staff staff = new Staff();
        staff.setUuid(UUID.randomUUID().toString());
        staff.setName(requestDTO.getName());
        staff.setRegistration_date(LocalDateTime.now());
        return staffRepository.save(staff);
    }

    @Override
    public Staff updateProfile(UpdateStaffRequestDTO requestDTO) {
        Staff staff = staffRepository.getStaffModelByUuid(requestDTO.getUuid());
        if(staff == null){
            return null;
        }
        staff.setName(requestDTO.getName());
        return staffRepository.save(staff);
    }
}
