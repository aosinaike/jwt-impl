package com.oze.staff;

import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public interface StaffService {
    public Staff addStaffProfile(CreateStaffRequestDTO requestDTO);
    public Staff updateProfile(UpdateStaffRequestDTO requestDTO);
}

