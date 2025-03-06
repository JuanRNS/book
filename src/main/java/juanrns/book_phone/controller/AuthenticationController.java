package juanrns.book_phone.controller;

import jakarta.validation.Valid;
import juanrns.book_phone.DTO.AuthenticationDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping(value = "/auth")
@RestController
public class AuthenticationController {



    @PostMapping(value = "/login")
    public ResponseEntity<Void> login(@RequestBody @Valid AuthenticationDTO authenticationDTO) {
        return null;
    }
}
