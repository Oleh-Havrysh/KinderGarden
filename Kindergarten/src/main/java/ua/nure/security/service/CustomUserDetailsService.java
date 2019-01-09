package ua.nure.security.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ua.nure.model.Human;
import ua.nure.security.UserPrincipal;
import ua.nure.service.HumanService;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private HumanService humanService;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        Human user = humanService.findByEmail(s);
        return UserPrincipal.create(user);
    }

    public UserDetails loadUserById(String id) {
        Human user = humanService.findById(id);
        return UserPrincipal.create(user);
    }
}
