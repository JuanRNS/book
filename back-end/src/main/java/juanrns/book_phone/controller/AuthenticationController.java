package juanrns.book_phone.controller;

import jakarta.validation.Valid;
import juanrns.book_phone.domain.user.*;
import juanrns.book_phone.infra.security.TokenService;
import juanrns.book_phone.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<TokenResponseDTO> login(@RequestBody @Valid UserLogin userLogin) {
        UsernamePasswordAuthenticationToken usernamePassword = new UsernamePasswordAuthenticationToken(userLogin.email(), userLogin.password());

        Authentication authentication = authenticationManager.authenticate(usernamePassword);

        String token = tokenService.generateToken((User) authentication.getPrincipal());

        return ResponseEntity.ok(new TokenResponseDTO(token));
    }

    @PostMapping("/register")
    public ResponseEntity<UserResponseDTO> register(@RequestBody @Valid UserDTO authenticationDTO) {
        if(userService.emailExists(authenticationDTO.email())) {
            return ResponseEntity.badRequest().body(null);
        }
        User newUser = userService.createUser(authenticationDTO);

        UserResponseDTO response = new UserResponseDTO(
                newUser.getId(),
                newUser.getName(),
                newUser.getEmail(),
                newUser.getPhoneNumber()
        );
        return ResponseEntity.ok(response);
    }

}
