package juanrns.book_phone.controller;


import jakarta.validation.Valid;
import juanrns.book_phone.domain.contact.ContactDTO;
import juanrns.book_phone.domain.contact.ContactResponseDTO;
import juanrns.book_phone.domain.user.User;
import juanrns.book_phone.services.ContactService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping(value = "/contact")
@RestController
public class ContactController {

    private final ContactService contactService;
    public ContactController(ContactService contactService) {
        this.contactService = contactService;
    }

    @PostMapping("/create")
    public ResponseEntity<ContactResponseDTO> createContact(
            @RequestBody @Valid ContactDTO contactDTO) {

        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        ContactResponseDTO contact = contactService.createContact(contactDTO, user);

        return ResponseEntity.ok(contact);
    }

    @GetMapping("/search")
    public ResponseEntity<Page<ContactResponseDTO>> getContacts(@PageableDefault(size = 10, page = 0) Pageable pageable) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return ResponseEntity.ok(contactService.getAllContacts(user.getId(), pageable));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteContact(@PathVariable String id) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        contactService.deleteContact(id, user);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ContactResponseDTO> updateContact(@PathVariable String id, @RequestBody @Valid ContactDTO contactDTO) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        ContactResponseDTO contact = contactService.updateContact(id, contactDTO, user);
        return ResponseEntity.ok(contact);
    }

    @GetMapping("/search/contacts/filter")
    public ResponseEntity<List<ContactResponseDTO>> searchContacts(@RequestParam String name) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        return ResponseEntity.ok(contactService.findByNameStartingWith(user, name));
    }
}
