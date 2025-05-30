package juanrns.book_phone.controller;

import jakarta.validation.Valid;
import juanrns.book_phone.DTO.UserDTO;
import juanrns.book_phone.DTO.UserLogin;
import juanrns.book_phone.entity.User;
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
            return ResponseEntity.badRequest().body("Já existe uma conta com esse email");
        }
        User newUser = userService.CreateUser(authenticationDTO);

        return ResponseEntity.ok("Usuário cadastrado com sucesso  " +newUser);
    }

    @GetMapping("/search")
    public ResponseEntity<List<User>> getCurrentUser() {
        return ResponseEntity.ok(userService.getAllUsers());
    }
}
