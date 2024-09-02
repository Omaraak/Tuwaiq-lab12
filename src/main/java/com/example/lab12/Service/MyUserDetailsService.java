package com.example.lab12.Service;

import com.example.lab12.Api.ApiException;
import com.example.lab12.Model.User;
import com.example.lab12.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MyUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findUserByUsername(username);
        if (user == null) {
            try {
                throw new ApiException("User username or password are wrong");
            } catch (ApiException e) {
                throw new RuntimeException(e);
            }
        }
        return user;
    }
}
