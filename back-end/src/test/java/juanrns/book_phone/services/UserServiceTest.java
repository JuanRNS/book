package juanrns.book_phone.services;

import juanrns.book_phone.domain.contact.Contact;
import juanrns.book_phone.domain.user.User;
import juanrns.book_phone.domain.user.UserDTO;
import juanrns.book_phone.domain.user.enums.UserType;
import juanrns.book_phone.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static juanrns.book_phone.domain.user.enums.UserType.ADMIN;
import static org.mockito.ArgumentMatchers.any;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;


    @Test
    void createUser() {
        UserDTO user = new UserDTO("admin","admin@teste.com","admin","ADMIN");
        User userCreated = new User(user.name(),user.email(),user.phoneNumber(),user.phoneNumber());

        when(userRepository.save(any(User.class))).thenReturn(userCreated);

        User result = userService.createUser(user);

        assertNotNull(result);
        assertEquals(userCreated.getName(), result.getName());
        assertEquals(userCreated.getEmail(), result.getEmail());
        assertEquals(userCreated.getPassword(), result.getPassword());
        assertEquals(userCreated.getUserType(), result.getUserType());

    }

    @Test
    void emailExists() {
        String email= "admin@teste.com";
        when(userRepository.findByEmail(email)).thenReturn(Optional.of(new User("admin","admin@teste.com","admin","ADMIN")));

        boolean result = userService.emailExists(email);
        assertTrue(result);

    }

    @Test
    void getAllUsers() {
        User user = new User("admin","admin@teste.com","admin","ADMIN");
        when(userRepository.findAll()).thenReturn(List.of(user));

        List<User> users = userService.getAllUsers();
        assertNotNull(users);
    }

    @Test
    void deleteUser() {
        List<Contact> contacts = new ArrayList<>();

        User user = new User(1L,"admin","admin@teste.com","admin","83999993333",ADMIN,LocalDateTime.now(), LocalDateTime.now(),contacts);

        doNothing().when(userRepository).deleteById(user.getId());

        userService.deleteUser(user,user.getId());

    }
}