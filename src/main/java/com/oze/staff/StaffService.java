package com.oze.staff;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public interface StaffService {
    public StaffModel addStaffProfile(StaffModel staffModel);
    public StaffModel updateProfile(StaffModel staff);
    public StaffModel getProfileByUuid(UUID staffUuid);
}

