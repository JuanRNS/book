package juanrns.book_phone.services;

import juanrns.book_phone.DTO.UserDTO;
import juanrns.book_phone.entity.User;
import juanrns.book_phone.repositories.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void CreateUser(UserDTO user) {
        String password = new BCryptPasswordEncoder().encode(user.password());
        User newUser = new User(user.name(),user.email(),password,user.phoneNumber());
        userRepository.save(newUser);
    }


    public boolean getByEmail(String email) {
        if(userRepository.findByEmail(email) != null) {
            return false;
        }
        else{
            return true;
        }
    }


    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
}
