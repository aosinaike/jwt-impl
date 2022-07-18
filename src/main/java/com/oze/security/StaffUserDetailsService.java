package com.oze.security;

import com.oze.staff.Staff;
import com.oze.staff.StaffRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.EnvironmentAware;
import org.springframework.core.env.Environment;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service("staffUserDetail")
public class StaffUserDetailsService implements UserDetailsService {

    private StaffRepository staffRepository;
    private UserDetails user;

    public StaffUserDetailsService(StaffRepository staffRepository) {
        this.staffRepository = staffRepository;
    }

    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        Staff staff = staffRepository.getStaffModelByUuid(s);
        if (staff == null){
            throw new UsernameNotFoundException(s);
        }
        CustomUserPrincipal userPrincipal = new CustomUserPrincipal(staff);
        return userPrincipal;
    }
}
