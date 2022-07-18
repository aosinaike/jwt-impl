package com.oze.staff;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.UUID;

@Repository
public interface StaffRepository extends JpaRepository<Staff, Long> {
    public Staff getStaffModelByUuid(String staffUuid);
}
