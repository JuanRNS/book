package juanrns.book_phone.controller;


import jakarta.validation.Valid;
import juanrns.book_phone.DTO.ContactDTO;
import juanrns.book_phone.DTO.ContactResponseDTO;
import juanrns.book_phone.entity.Contact;
import juanrns.book_phone.entity.User;
import juanrns.book_phone.services.ContactService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequestMapping(value = "/contact")
@RestController
public class ContactController {

    private final ContactService contactService;
    public ContactController(ContactService contactService) {
        this.contactService = contactService;
    }

    @PostMapping("/create")
    public ResponseEntity<ContactResponseDTO> createContact(
            @RequestBody @Valid ContactDTO contactDTO,
            @AuthenticationPrincipal User user) {

        Contact contact = contactService.createContact(contactDTO, user);

        ContactResponseDTO response = new ContactResponseDTO(
                contact.getId(),
                contact.getName(),
                contact.getPhone(),
                contact.getEmail(),
                contact.getAddress(),
                contact.getFavorite(),
                contact.getCreated(),
                contact.getModified()
        );

        return ResponseEntity.ok(response);
    }

    @GetMapping("/search")
    public ResponseEntity<List<ContactResponseDTO>> getContacts() {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return ResponseEntity.ok(contactService.getAllContacts(user.getId()));
    }
}
