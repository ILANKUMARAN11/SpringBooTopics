package com.tcs.infy.security;

import com.tcs.infy.entity.User;
import com.tcs.infy.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class JwtUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username).orElse(null);

        if (user!=null && user.getUsername()!=null && user.getUsername().length()>0) {
            UserPrincipal userPrincipal = new UserPrincipal(user);
            return userPrincipal;
        } else {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }
    }

}