package com.restaurant.Server.Service;

import com.restaurant.Server.Repository.StaffRepository;
import com.restaurant.Server.model.Role;
import com.restaurant.Server.model.Staff;
import org.omg.CORBA.UserException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@Primary
public class AuthenticationService implements UserDetailsService {


    private final StaffRepository staffRepository;

    @Autowired
    public AuthenticationService(StaffRepository staffRepository) {
        this.staffRepository = staffRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String pesel) throws UsernameNotFoundException {
        try {
            if(pesel == null ||  pesel.isEmpty()){
                throw new UsernameNotFoundException("User not found");
            }
        } catch (Exception e) {
            throw new UsernameNotFoundException("User not found");
        }

        return toUserDetails(staffRepository.findByPesel(pesel)
                .orElseThrow(() -> new UsernameNotFoundException("User not found")));

    }

    private UserDetails toUserDetails(Staff staffObject) {
        return org.springframework.security.core.userdetails.User.withUsername(staffObject.getPesel())
                .password(staffObject.getPassword())
                .authorities(getAuthorities(staffObject)).build();
    }

    private List<GrantedAuthority> getAuthorities(Staff staff) {
        List<GrantedAuthority> authorities = new ArrayList<>();
        for (Role role : staff.getRoles()) {
            GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(role.getRole());
            authorities.add(grantedAuthority);
        }
        return authorities;
    }
}