package com.example.lab12.Controller;

import com.example.lab12.Api.ApiException;
import com.example.lab12.Model.User;
import com.example.lab12.Service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/getAll")
    public ResponseEntity getAll(){
        return ResponseEntity.status(200).body(userService.getAll());
    }

    @PostMapping("/add")
    public ResponseEntity register(@RequestBody User user) {
        userService.register(user);
        return ResponseEntity.status(200).body("User registered successfully");
    }

    @PutMapping("/update/{id}")
    public ResponseEntity update(@PathVariable Integer id, @RequestBody User user) throws ApiException, ApiException {
        userService.updateUser(id, user);
        return ResponseEntity.status(200).body("User updated successfully");
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity delete(@PathVariable Integer id) throws ApiException {
        userService.deleteUser(id);
        return ResponseEntity.status(200).body("User deleted successfully");
    }
}
