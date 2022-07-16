package com.oze.staff;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.UUID;

@Repository
public interface StaffRepository extends JpaRepository<StaffModel, Integer> {

    public StaffModel addStaff(StaffModel staffModel);
    public StaffModel updateStaff(StaffModel staff);
    public StaffModel getStaffModelByUuid(UUID staffUuid);
}
