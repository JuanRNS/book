package juanrns.book_phone.services;

import juanrns.book_phone.domain.user.UserDTO;
import juanrns.book_phone.domain.user.User;
import juanrns.book_phone.domain.user.enums.UserType;
import juanrns.book_phone.repository.UserRepository;
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

        return userRepository.save(newUser);
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

    public void deleteUser(User user,Long userId) {
        if(!user.getUserType().equals(UserType.ADMIN)) return;
        userRepository.deleteById(userId);
    }
}
