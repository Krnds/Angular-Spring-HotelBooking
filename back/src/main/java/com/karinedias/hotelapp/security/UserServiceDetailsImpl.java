package com.karinedias.hotelapp.security;

import com.karinedias.hotelapp.entity.Admin;
import com.karinedias.hotelapp.repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserServiceDetailsImpl implements UserDetailsService {

    @Autowired
    private AdminRepository adminRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Admin user = adminRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("No admin with username " + username + " was found.");
        } else {
            return new UserDetailsImpl(user);
        }
    }
}
