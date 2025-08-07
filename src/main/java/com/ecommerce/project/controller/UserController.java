package com.ecommerce.project.controller;
import com.ecommerce.project.model.User;
import com.ecommerce.project.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@RestController
@RequestMapping("/api/users")
@CrossOrigin
public class UserController {
    private final UserRepository repo;
    private final PasswordEncoder passwordEncoder;
    public UserController(UserRepository repo, PasswordEncoder passwordEncoder) {
        this.repo = repo;
        this.passwordEncoder = passwordEncoder;
    }
    @GetMapping
    public List<User> getAllUsers() {
        return repo.findAll();
    }
    @PostMapping
    public User createUser(@RequestBody User u) {
        u.setPassword(passwordEncoder.encode(u.getPassword()));
        return repo.save(u);
    }
    @GetMapping("/{id}")
    public User getUserById(@PathVariable Long id) {
        return repo.findById(id).orElse(null);
    }
    @PutMapping("/{id}")
    public User updateUser(@PathVariable Long id, @RequestBody User u) {
        return repo.findById(id).map(user -> {
            user.setUsername(u.getUsername());
            user.setEmail(u.getEmail());
            if (u.getPassword() != null && !u.getPassword().isEmpty()) {
                user.setPassword(passwordEncoder.encode(u.getPassword()));
            }
            return repo.save(user);
        }).orElse(null);
    }
    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) {
        repo.deleteById(id);
    }
}
