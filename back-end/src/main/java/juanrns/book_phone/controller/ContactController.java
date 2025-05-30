package juanrns.book_phone.controller;


import juanrns.book_phone.DTO.ContactDTO;
import juanrns.book_phone.DTO.ContactResponseDTO;
import juanrns.book_phone.entity.Contact;
import juanrns.book_phone.entity.User;
import juanrns.book_phone.services.ContactService;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<ContactResponseDTO> addContact(@RequestBody ContactDTO contact) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        ContactResponseDTO newContact = contactService.CreateContact(contact, user);
        return ResponseEntity.ok(newContact);
    }

    @GetMapping("/search")
    public ResponseEntity<List<ContactResponseDTO>> getContacts() {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return ResponseEntity.ok(contactService.getAllContacts(user.getId()));
    }
}
