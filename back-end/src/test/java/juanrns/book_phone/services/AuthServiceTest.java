package juanrns.book_phone.services;

import juanrns.book_phone.domain.contact.Contact;
import juanrns.book_phone.domain.user.User;
import juanrns.book_phone.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static juanrns.book_phone.domain.user.enums.UserType.ADMIN;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AuthServiceTest {
    @Mock
    private UserRepository userRepository;
    @InjectMocks
    private AuthService authService;

    @Test
    void loadUserByUsernameSuccess() {
        List<Contact> contacts = new ArrayList<>();
        User user = new User(1L,"admin","admin@teste.com","admin","83999993333",ADMIN, LocalDateTime.now(), LocalDateTime.now(),contacts);

        when(userRepository.findByEmail(user.getEmail())).thenReturn(Optional.of(user));

        UserDetails result = authService.loadUserByUsername(user.getEmail());

        assertNotNull(result);
        assertEquals(user.getEmail(), result.getUsername());
    }

    @Test
    void loadUserByUsernameFail() {
        when(userRepository.findByEmail("admin@teste.com")).thenReturn(Optional.empty());
        UsernameNotFoundException exception = assertThrows(UsernameNotFoundException.class, () -> authService.loadUserByUsername("admin@teste.com"));
        assertEquals("Usuário não encontrado", exception.getMessage());
    }
}