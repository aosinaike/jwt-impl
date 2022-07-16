package com.oze.staff;

import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class StaffServiceImpl implements StaffService{

    public StaffRepository staffRepository;

    public StaffServiceImpl(StaffRepository staffRepository){
        this.staffRepository = staffRepository;
    }

    @Override
    public StaffModel addStaffProfile(StaffModel staffModel) {
        staffModel.setUuid(UUID.randomUUID());
        return staffRepository.addStaff(staffModel);
    }

    @Override
    public StaffModel updateProfile(StaffModel staffProfile) {
        return staffRepository.updateStaff(staffProfile);
    }

    @Override
    public StaffModel getProfileByUuid(UUID staffUuid) {
        return staffRepository.getStaffModelByUuid(staffUuid);
    }
}
