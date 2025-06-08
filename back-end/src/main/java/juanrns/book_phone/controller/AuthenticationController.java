package juanrns.book_phone.controller;

import jakarta.validation.Valid;
import juanrns.book_phone.domain.user.UserDTO;
import juanrns.book_phone.domain.user.UserLogin;
import juanrns.book_phone.domain.user.UserResponseDTO;
import juanrns.book_phone.domain.user.User;
import juanrns.book_phone.infra.security.TokenService;
import juanrns.book_phone.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping(value = "/auth")
@RestController
public class AuthenticationController {

    private final AuthenticationManager authenticationManager;

    private final TokenService tokenService;

    private final UserService userService;

    public AuthenticationController(AuthenticationManager authenticationManager, TokenService tokenService,  UserService userService) {
        this.authenticationManager = authenticationManager;
        this.tokenService = tokenService;
        this.userService = userService;
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody @Valid UserLogin userLogin) {
        var usernamePassword = new UsernamePasswordAuthenticationToken(userLogin.email(), userLogin.password());

        var authentication = authenticationManager.authenticate(usernamePassword);

        var token = tokenService.generateToken((User) authentication.getPrincipal());

        return ResponseEntity.ok(token);
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody @Valid UserDTO authenticationDTO) {
        if(userService.emailExists(authenticationDTO.email())) {
            return ResponseEntity.badRequest().body(null);
        }
        User newUser = userService.CreateUser(authenticationDTO);

        UserResponseDTO response = new UserResponseDTO(
                newUser.getId(),
                newUser.getName(),
                newUser.getEmail(),
                newUser.getPhoneNumber()
        );
        return ResponseEntity.ok(response);
    }

    @GetMapping("/search")
    public ResponseEntity<List<User>> getCurrentUser() {
        return ResponseEntity.ok(userService.getAllUsers());
    }
}
