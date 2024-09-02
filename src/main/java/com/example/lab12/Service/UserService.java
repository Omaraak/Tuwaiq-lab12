package com.example.lab12.Service;

import com.example.lab12.Api.ApiException;
import com.example.lab12.Model.Blog;
import com.example.lab12.Model.User;
import com.example.lab12.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    public List<User> getAll(){
        return userRepository.findAll();
    }

    public void register(User user) {
        user.setRole("USER");

        String hash = new BCryptPasswordEncoder().encode(user.getPassword());
        user.setPassword(hash);

        userRepository.save(user);
    }

    public void updateUser(int id, User user) throws ApiException {
        User updatedUser = userRepository.findUserById(id);
        if(updatedUser == null) {
            throw new ApiException("Can't delete");
        }
        updatedUser.setUsername(user.getUsername());
        String hash = new BCryptPasswordEncoder().encode(user.getPassword());
        updatedUser.setPassword(hash);
        userRepository.save(updatedUser);
    }

    public void deleteUser(int userID) throws ApiException {
        User user = userRepository.findUserById(userID);
        userRepository.delete(user);
    }
}
