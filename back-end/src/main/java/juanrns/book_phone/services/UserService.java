package juanrns.book_phone.services;

import juanrns.book_phone.DTO.UserDTO;
import juanrns.book_phone.entity.User;
import juanrns.book_phone.repositories.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User CreateUser(UserDTO user) {
        String password = new BCryptPasswordEncoder().encode(user.password());
        User newUser = new User(user.name(),user.email(),password,user.phoneNumber());
        userRepository.save(newUser);
        return newUser;
    }
    public boolean emailExists(String email) {
        return userRepository.findByEmail(email).isPresent();
    }

    public Optional<User> findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
}
