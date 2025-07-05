package com.everton.cash_Control.services.ServiceImpl;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailsService implements UserDetailsService {
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if(username.equals("everton")) {
            return User.withUsername("everton")
                    .password("{noop}Everton2025")
                    .roles("USER")
                    .build();
        } else {

            throw new UsernameNotFoundException("user not found");
        }

    }
}
